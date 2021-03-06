package raf.agentes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JOptionPane;

import raf.principal.Ra;
import raf.principal.RaAddress;


/**
 * Pops up a hello Window on every server in the domain when
 * a domain server is installed.
 */
public class HelloDomain extends Ra
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * List of all the servers in the domain.
     */
    ArrayList<RaAddress> v;

    /**
     * Points to the next destination in v.
     */
    int i;

    /**
     * Just initialize the super class.
     *
     * @param name The name of the agent. This name has to be
     * unique. Normally the KaaribogaBase class provides some
     * method to generate a unique name.
     */
    public HelloDomain(String name){
        super("HelloDomain_" + name);
    }

    /**
     * Initializes v with all servers connected to the domain.
     */
    public void onCreate(){
       
        v = agency.getServers(this);
       
       
    }

    /**
     * Shows a window.
     */
    public void onArrival(){
        new Popup().start();
    }

    /**
     * This is automically called if the agent arrives on
     * a base.
     */
    public void run(){
        try{
            if (i < v.size()){
                destination = v.get(i);
                ++i;
                System.out.println("Try to dispatch");
                fireDispatchRequest();
            }
            else fireDestroyRequest();
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.err.println ("HelloDomain: Index out of bounds!");
            fireDestroyRequest();
        }
    }

    /**
     * Use a thread to let a window pop up.
     */
    public class Popup extends Thread implements Serializable{

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
         * Pop up window.
         */
        public void run(){
            JOptionPane dialog = new JOptionPane();
            JOptionPane.showMessageDialog (null, "Hi there!");
        }
    }

}