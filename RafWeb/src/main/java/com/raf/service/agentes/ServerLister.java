package com.raf.service.agentes;

import java.util.Enumeration;

import com.raf.service.principal.Ra;
import com.raf.service.principal.RaAddress;


/**
 * Utility agent that prints out a list of all servers connected to the domain.
 * Note how easy it is to extend an existing program with agents.
 * Future versions of kaariboga will probably contain special agents that
 * are automatically integrated into the menu structure.
 */
public class ServerLister extends Ra
{
    /**
     * Just initialize the super class.
     *
     * @param name The name of the agent. This name has to be
     * unique. Normally the KaaribogaBase class provides some
     * method to generate a unique name.
     */
    public ServerLister(String name){
        super("ServerLister_" + name);
    }

    /**
     * Prints out the names of all servers connected to the domain.
     */
    public void run(){
        RaAddress address;
        Enumeration en = agency.getServers(this).elements();
        System.out.println("---------------------------------------------");
        System.out.println("Servers connected to the domain:");
        while (en.hasMoreElements()){
            address = (RaAddress) en.nextElement();
            System.out.println (address.host.toString() + ":" + Integer.toString(address.port));
        }
        System.out.println("---------------------------------------------");
        fireDestroyRequest();
    }

}
