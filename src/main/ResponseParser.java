package main;

import java.util.List;
import java.util.concurrent.SynchronousQueue;

/** Forms a response to send to the server based on the sequence of button presses. 
 * A thread created from this runnable will create responses to send back to the server*/
public class ResponseParser implements Runnable {
    
    private SynchronousQueue<String> response; //A queue for passing off the message thread safely
    private List<Integer> signal; //The signal updated by the Button Listeners
    
    /** Creates a new response parser that listens to the button presses on the gui*/
    public ResponseParser(List<Integer> list) {
        response = new SynchronousQueue<>();
        signal = list;
    }
    
    /** Starts obtaining responses from the gui and pushing them to the queue */
    public void run() {
        //TODO Implement this stuff
    }
    
    /** Gets the next response to the server.  Null is returned if the thread obtaining the 
     * response is interrupted*/
    public String getResponse() {
        try {
            return response.take();
        } catch (InterruptedException e) {// Should never happen
            System.err.println("Why did that happen?");
        } 
        // Execution should never reach here
        return null;
    }

}
