package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import roles.Registry;
import roles.Role;

/**
 * Displays the content for a game of One Night Ultimate Werewolf.
 * @author devin
 */
public class OneNightWindow {
	
	private final String HOST = "localhost"; //the host that the client tries to connect to
	private final int PORT = 9100; //the port that the client tries to connect to
	private final Socket socket; //the Socket representing the players connection to the server
	private UUID uuid; //the players UUID; should not be null after initialization

	private List<CenterCard> centerCards;
	private List<PlayerCard> players; //the players that are playing the game; the client should always be at index 0
	private List<Role> roles; //all of the roles in the game
	private Map<String, PlayerCard> stringToPlayerCard;

	private JFrame frame;
	private final int HEIGHT =  720;
	private final int WIDTH  = 1280;
	
	private JLabel label;
	
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
		players = new ArrayList<>();
		roles = new ArrayList<>();
		stringToPlayerCard = new HashMap<>();
		
		//configure the JFrame
		frame = new JFrame();
		frame.setTitle("One Night");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		label = new JLabel();
		frame.getContentPane().add(label);
	}
	
	//updates the window based on the information in this object
	private void updateWindow() {
		System.out.println("Test");
	}
	
	public void createAndShowGUI() {
				
		System.out.println("Hello");
		
		SwingUtilities.invokeLater(() -> {
			frame.setVisible(true);
		});
	
		Timer timer = new Timer(40, new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                updateWindow();
            }
		});
		timer.start();
		
		running = true;

		Scanner scan = new Scanner(System.in);
		while (running) {
			readFromServer();
			out.println(uuid + " " + scan.nextLine());
		}
		scan.close();

	}
	
	private void readFromServer() {
		String fromServer;
		try {
			while (!(fromServer = in.readLine()).equals(">>>")) {
				
				System.out.println(fromServer);
				
				//make a list of the arguments from the String from the server
				Scanner parse = new Scanner(fromServer);
				List<String> args = new ArrayList<>();
				parse.useDelimiter("=");
				parse.forEachRemaining((s) -> {
					args.add(s);
				});
				parse.close();
				
				switch (args.get(0)) {
				
				case "uuid":
					uuid = UUID.fromString(args.get(1));
					break;
					
				case "set":
					Scanner setgen = new Scanner(args.get(1));
					setgen.useDelimiter(",");
					setgen.forEachRemaining((s) -> {
						roles.add(Registry.getInstance().roleFromString(s));
					});
					setgen.close();
					break;
				
				case "role":
					Role role1 = Registry.getInstance().roleFromString(args.get(1));
					players.get(0).setRole(role1);
					break;
					
				case "players":
					Scanner playerListifier = new Scanner(args.get(1));
					playerListifier.useDelimiter(",");
					playerListifier.forEachRemaining((s) -> {
						players.add(new PlayerCard(s, null));
					});
					playerListifier.close();
					
				//handles everything that needs more complex matching	
				default:
					
					//for location information
					if (args.get(0).matches(".+-.+")) {
						CardLocation location = parsePosition(args.get(0));
						Role role2 = Registry.getInstance().roleFromString(args.get(1));
						location.setRole(role2);
					}
					
					
				}
			}
		} catch (IOException e) {
			System.err.println("Failed to read from the server.");
		}
	}
    
	//pos i s in the form of C-[index] or P-[name]
    private CardLocation parsePosition(String pos) {
    	//parse the position into an ArrayList 
    	Scanner parse = new Scanner(pos);
        parse.useDelimiter("-");
        ArrayList<String> args = new ArrayList<>();
        while (parse.hasNext()) {
        	args.add(parse.next().trim());
        }
        parse.close();
        
        //Return the card location based on the  array
        if (args.get(0).equals("C")) {
            int index = Integer.parseInt(args.get(1));
            return centerCards.get(index);
        } else {
            String name = args.get(1);
            return nameToPlayer(name);
        }
    }
    
    private PlayerCard nameToPlayer(String name) {
    	return stringToPlayerCard.get(name);
    }
    
    //TESTING
    public static void main(String[] args) throws IOException {
    	OneNightWindow window = new OneNightWindow();
    	SwingUtilities.invokeLater(() -> {
    		window.frame.setVisible(true);
    	});
    	window.readFromServer();
    	Scanner in = new Scanner(System.in);
    	window.out.println(window.uuid + " " + in.nextLine());
    	window.readFromServer();
    	String text = window.players.stream()
    			.map(a->a.getName())
    			.collect(Collectors.joining(", "));
    	System.out.println(text);
    	in.close();
    }
	
}
