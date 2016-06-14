package raf.agentes;

import raf.principal.Ra;

/**
 * The Reproducer agent is able to load other agents.
 */
public class Reproducer extends Ra
{
    /**
     * Number of childs the agent will create.
     */
    public int nChilds = 3;

    /**
     * Just initialize the super class.
     *
     * @param name The name of the agent. This name has to be
     * unique. Normally the KaaribogaBase class provides some
     * method to generate a unique name.
     */
    public Reproducer(String name){
        super("Reproducer_" + name);
    }

    /**
     * After an initial sleep period (grow up) the agent gives birth to new childs.
     */
    public void run(){
        try{
            System.out.println ("Hurray, I am born.");
            Thread.currentThread().sleep(500);
            for (int i = 0; i < nChilds; i++){       
  	        Reproducer agent = new Reproducer(agency.generateName());
  	        agent.nChilds = nChilds - 1;
                agency.addRaOnCreation (agent, null);
            }
        }
        catch(java.lang.InterruptedException e){}

        System.out.println ("Agent is dies after " + nChilds + " childs.");
        fireDestroyRequest();
    }
}