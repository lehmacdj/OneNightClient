package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import roles.Registry;
import roles.Role;

/**
 * Displays the content for a game of One Night Ultimate Werewolf.
 * @author devin
 */
public class OneNightWindow {


	//MARK: The Frame Code
	private JFrame frame;
	private final int HEIGHT =  1000;
	private final int WIDTH  = 1200;

	private PlayArea playArea;
	private Box setList;
	
	
	private boolean running; // true while the window is running
	
	/**
	 * The output stream that sends information to the server.
	 */
	public final PrintWriter out;
	
	/**
	 * The input stream that reads information from the server.
	 */
	public final BufferedReader in;
	
	/**
	 * Creates a new window and initializes it with information from the server.
	 */
	public OneNightWindow() throws IOException {
		//init the input and output streams
		socket = new Socket(HOST, PORT);
		out = new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		
		//initialize some fields
		centerCards = new ArrayList<>();
		for (int i = 0; i < 3; i++) centerCards.add(null);
		roles = new ArrayList<>();
		players = new HashMap<>();
		
		readFromServer();
		
		//make a list to store the button state
		signal = Collections.synchronizedList(new ArrayList<>(playerCount));
		
		//configure the JFrame
		frame = new JFrame();
		frame.setTitle("One Night");
		
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		playArea = new PlayArea(playerCount, roles, HEIGHT, signal);
		frame.add(playArea, BorderLayout.WEST);
		
		setList = Box.createVerticalBox();
		setList.add(new JLabel("<html><strong>Role Cards in Use</strong></html>"));
        roles.stream().forEach(r -> {
            assert r != null;
            assert r.getName() != null;
            JLabel label = new JLabel();
            label.setText(r.getName());
            label.setPreferredSize(new Dimension(100, 15));
            setList.add(label);
        });
        setList.add(Box.createVerticalGlue());
		
		frame.add(setList, BorderLayout.EAST);
		
		//TODO add a status bar at the bottom explaining what the player should do with a few options
		// as well
		// ie:
		// - a button to clear current move
		// - a button to confirm move
	}

	//updates the window based on the information in this object
	private void updateWindow() {
		for (Map.Entry<String, Player> e : players.entrySet()) {
			Card panel = playArea.getPlayer(e.getValue().getIndex());
			panel.setPlayerName(e.getKey());
			panel.setRole(e.getValue().getRole() + "");
		}
		for (int i = 0; i < centerCards.size(); i++) {
			Card panel = playArea.getCenter(i);
			panel.setRole(centerCards.get(i) + "");
		}
	}
	
	public void createAndShowGUI() {

		SwingUtilities.invokeLater(() -> frame.setVisible(true));
		
		// Begin the response parser
		new Thread(new ResponseParser(signal));
		
		//get the name from the client
		String raw = JOptionPane.showInputDialog("Input your name");
		if (!raw.contains(" "))
			name = raw;
		while (name == null) {
			raw = JOptionPane.showInputDialog("Please try again. Names cannot contain spaces.");
			if (!raw.contains(" "))
				name = raw;
		}
		out.println(uuid + " ready " + name);

		Timer timer = new Timer(100, ae -> updateWindow());
		timer.start();
		
		running = true;

		Scanner ui = new Scanner(System.in);
		while (running) {
			readFromServer();
			out.println(uuid + " " + ui.nextLine());
		}
		ui.close();
	}

	//MARK: Data Source
	
	private final String HOST = "localhost"; //the host that the client tries to connect to
	private final int PORT = 9100; //the port that the client tries to connect to
	private final Socket socket; //the Socket representing the players connection to the server
	private UUID uuid; //the players UUID; should not be null after initialization
	private String name;
	private int playerCount;
	private List<Role> centerCards;
	private List<Role> roles; //all of the roles in the game
	private Map<String, Player> players; // the players.  The key is the name, the value is the role
	private List<Integer> signal; // The list that relays the signal to the ResponseParser
	
	private void readFromServer() {
		try {
			String fromServer = in.readLine();
			while (!fromServer.equals(">>>")) {
				
				System.out.println(fromServer);
				
				//make a list of the arguments from the String from the server
				Scanner parse = new Scanner(fromServer);
				List<String> args = new ArrayList<>();
				parse.useDelimiter("=");
				parse.forEachRemaining((s) -> {
					args.add(s.trim());
				});
				parse.close();
				
				String keyword = args.get(0);
				
				if (keyword.equals("uuid")) {
					uuid = UUID.fromString(args.get(1));
				} else if (keyword.equals("set")) {
					Scanner make = new Scanner(args.get(1));
					make.useDelimiter(",");
					make.forEachRemaining((s) -> {
						roles.add(Registry.getInstance().roleFromString(s.trim()));
					});
					make.close();
				} else if (keyword.equals("count")) {
					playerCount = Integer.parseInt(args.get(1));
				} else if (keyword.equals("name")) {
					name = args.get(1);
				} else if (keyword.equals("role")) {
					Role role = Registry.getInstance().roleFromString(args.get(1));
					players.get(name).setRole(role);
				} else if (keyword.equals("players")) {
					Scanner names = new Scanner(args.get(1));
					names.useDelimiter(",");
					while (names.hasNext()) {
						String n = names.next();
						assert n != null;
						players.put(n.trim(), new Player());
					}
					names.close();
				} else if (keyword.matches(".+-.+")) {
					Role role = Registry.getInstance().roleFromString(args.get(1));
					setRole(keyword, role);
				}
				
				fromServer = in.readLine();
			}
		} catch (IOException e) {
			System.err.println("Failed to read from the server.");
		}
	}
    	
	private void setRole(String pos, Role role) {
		//get the elements of the position into an ArrayList
		Scanner parse = new Scanner(pos);
		parse.useDelimiter("-");
		List<String> args = new ArrayList<>();
		parse.forEachRemaining((s) -> {
			args.add(s.trim());
		});
		parse.close();
		
		//modify the role at the correct position
		if (args.get(0).equals("C")) {
			centerCards.set(Integer.parseInt(args.get(1)), role);
		} else {
			assert args.get(0).equals("P");
			players.get(args.get(1)).setRole(role);
		}
	}
	
}
