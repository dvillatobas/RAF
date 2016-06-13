package com.raf.service.agentes;

import java.util.Enumeration;

import javax.swing.JOptionPane;

import com.raf.service.principal.Ra;
import com.raf.service.principal.RaAddress;
import com.raf.service.principal.RaMessage;

/**
 * The MessageSender agent sends a message to all agents on the base server.
 */
public class MessageSender extends Ra
{
    /**
     * Just initialize the super class.
     *
     * @param name The name of the agent. This name has to be
     * unique. Normally the KaaribogaBase class provides some
     * method to generate a unique name.
     */
    public MessageSender (String name){
        super("MessageSender_" + name);
    }

    /**
     * Sends a message to all agents on this base and destroys itself.
     */
    public void run(){

        // open dialog for message
        JOptionPane dialog = new JOptionPane();
        String content = dialog.showInputDialog (null, "Please type in a message.");

        // send Message
        // Note: if you want to send Messages to other servers you will
        // need code like this:
        // KaaribogaAddress baseAdr  = base.getBaseAddress(this);
        // KaaribogaAddress sender  = new KaaribogaAddress (baseAdr.host, baseAdr.port, getName());
        // KaaribogaAddress recipient = new KaaribogaAddress (host, port, name);
        Enumeration names = agency.getRaNames (this);
        while (names.hasMoreElements()){
            // simple constructors for address save for local use
            RaAddress sender  = new RaAddress (getName());
            RaAddress recipient = new RaAddress ((String) names.nextElement());
            RaMessage message = new RaMessage (sender, recipient, "MESSAGE", content, null);
            fireRaMessage (message);
        }

        fireDestroyRequest();
    }

}