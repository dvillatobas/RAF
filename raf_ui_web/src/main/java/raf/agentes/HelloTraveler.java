package raf.agentes;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import raf.principal.Ra;
import raf.principal.RaAddress;

/**
 * The travel agent just opens a dialog, travels to another base and prints
 * Hello World to the screen. After that it destroys itself.
 */
public class HelloTraveler extends Ra
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Just initialize the super class.
     *
     * @param name The name of the agent. This name has to be
     * unique. Normally the KaaribogaBase class provides some
     * method to generate a unique name.
     */
    public HelloTraveler(String name){
        super("Traveler_" + name);
    }

    /**
     * This is called just after the kaariboga has been created and
     * the base has set the important fields.
     */
    public void onCreate(){
        InetAddress target = null;

        try{
            JOptionPane dialog = new JOptionPane();
            String server = JOptionPane.showInputDialog (null, "Where do you want me to go today?");
            target = InetAddress.getByName(server);
        }
        catch (UnknownHostException e){
            System.err.println("Error: Could not determine host address!");
            fireDestroyRequest();
        }
        destination = new RaAddress(target, 10101, null);
        fireDispatchRequest();
    }

    /**
     * This is automically called if the agent arrives on
     * a base.
     */
    public void onArrival(){
        System.out.println("Hi! I am the traveler. ");
        fireDestroyRequest();
    }


}