package raf.web.ra;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Component;

import raf.principal.AgencyEvent;
import raf.principal.AgencyListener;
import raf.principal.ClassManager;
import raf.principal.Ra;
import raf.principal.RaAddress;
import raf.principal.RaAgency;
import raf.principal.RaClassLoader;

@Component
public class RaComponent implements AgencyListener{
	
	private String strConfigFile = "target" + File.separator + "classes" + File.separator + "raf" + File.separator
            + "config"
            + File.separator
            + "movil.config";
	
	/**
     * Maneja los byte codes de las clases cargadas.
     */
    private ClassManager classManager;


	/**
	 * La agencia que maneja todos los agentes.
	 */
	private RaAgency raAgency;

    /**
     * Direccion del servidor que registra todos los servidores de agentes del dominio.
     */
    private RaAddress raServer;

    /**
     * N� puerto para las conexiones, por defecto 10101.
     */
    private int port;
    
    private List<String> listaClases;
    
    public ByteArrayOutputStream log;
	
    
    private List<String> listaAgentes;
    
    public void addListaAgentes(String agente){
    	listaAgentes.add(agente);
    }
    
    public void removeListaAgentes(String agente){
    	listaAgentes.remove(agente);
    }
    
	public List<String> getListaAgentes() {
		return listaAgentes;
	}

	public void setListaAgentes(List<String> listaAgentes) {
		this.listaAgentes = listaAgentes;
	}

	public RaComponent() {
		super();
		
		long byteCodeDelay;
        String strRaServer = null;
        int raPort;
        Properties props = new Properties ();

        // lee las propiedades desde el fichero
        try {
            FileInputStream in = new FileInputStream (strConfigFile);
            props.load (in);
            in.close();
        }
        catch (FileNotFoundException e){
            System.err.println ("GraLauncher: No se puede abrir el fichero de configuraci�n!");
        }
        catch (IOException e){
            System.err.println ("GRaLauncher: Ha fallado la lectura del fichero!");
        }

        try {
            port = Integer.parseInt(props.getProperty("port", "10101"));
        }
        catch (NumberFormatException e){
            port = 10101;
        }
        try {
            byteCodeDelay = Long.parseLong(props.getProperty("byteCodeDelay", "100000"));
        }
        catch (NumberFormatException e){
            byteCodeDelay = 100000;
        }
        try {
            strRaServer = props.getProperty("raServer");
            raPort = Integer.parseInt(props.getProperty("raPort", "10102"));
        }
        catch (NumberFormatException e){
            raPort = 10102;
        }

        try{
            if (strRaServer == null){
                raServer = null;
            }
            else{
                InetAddress server = InetAddress.getByName (strRaServer);
                raServer = new RaAddress (server, raPort, null);
            }
        }
        catch (UnknownHostException e){
            System.out.println("! GRaLauncher: raServer no valido." + e);
            raServer = null;
        }

        System.out.println ("puerto: " + port);

        // lanza una nuva agencia
        classManager = new ClassManager (byteCodeDelay, props.getProperty("agentsPath"));

        raAgency = new RaAgency (this, classManager);
        raAgency.addAgencyListener (this);
        
		File agentsPath = new File ("target" + File.separator + "classes" + File.separator + "raf" + File.separator + "agentes" + File.separator );
		listaClases =  Arrays.asList(agentsPath.list());
        
//        setVisible(false);
//
//       	// crea el menu principal
//       	menuBar = new JMenuBar();
//       	setJMenuBar (menuBar);
//
//       	// menu fichero
//       	menu = new JMenu ("Fichero");
//       	menuItem = new JMenuItem ("Cargar...");
//       	menuItem.setActionCommand ("Cargar...");
//       	menuItem.addActionListener (this);
//       	menu.add (menuItem);
//       	menuBar.add (menu);
//
//       	// Editar menu
//       	menu = new JMenu ("Editar");
//       	menuItem = new JMenuItem ("Enviar A...");
//       	menuItem.setActionCommand ("editSendTo");
//       	menuItem.addActionListener (this);
//       	menu.add (menuItem);
//       	menuItem = new JMenuItem ("Eliminar");
//       	menuItem.setActionCommand ("editDestroy");
//       	menuItem.addActionListener (this);
//       	menu.add (menuItem);
//       	menuBar.add (menu);
//
//      
//
//      
//        // contenidos del Frame 
//        getContentPane().add (panel);
//        panel.setLayout (new GridLayout(1,1));
//        panel.setPreferredSize(new java.awt.Dimension(500, 300));
//
//        listModel = new DefaultListModel<String>();
//        list = new JList<String> (listModel);
//        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        list.addListSelectionListener(this);
//        listScroller = new JScrollPane (list);
//
//        panel.add (listScroller);
		
		log = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(log);
	    System.setOut(ps);

        startAgency();
	}
	
	
	
	public List<String> getListaClases() {
		return listaClases;
	}

	public void setListaClases(List<String> listaClases) {
		this.listaClases = listaClases;
	}

	public void cargar(String clase){   
        if (clase != null) {
            clase = clase.trim();
            if (clase.length() >0 ) {
                loadRa (clase);
            }
        }
	}
	
	public void sendTo(String s, String clase){
        if (s != null) {
            s = s.trim();
            if (s.length() >0 ) {
                editSendTo (s, clase);
            }
        }
	}
	
	public void destroy(String clase){
		editDestroy(clase);
	}
	
	
	
	
	/**
     *  Llamada para el limpiado de las conexiones de red.
     */
    public void dispose() {
        stopAgency();
    }
    
    /**
     * carga un agente desde un fichero (poner un string como parametro)
     */
    private void loadRa(String s){
      
        String name;
        

        name = "raf.agentes." + s.split("\\.")[0];
        try{
            Class<?> result;
            RaClassLoader loader = new RaClassLoader(classManager, null, null);
            result = loader.loadClass(name);
            
            if (result == null){
                System.err.println ("GRaLauncher: No se pudo cargar la clase! clase no encontrada!");
                return;
            }

            Constructor<?> cons[] = result.getConstructors();
            Object obs[] = {raAgency.generateName()};
	        Object agent =  cons[0].newInstance(obs);
	        if(agent instanceof Ra){
	        	Ra a = (Ra) agent;
	        	raAgency.addRaOnCreation (a, null);
	            listaAgentes.add(s);
	        }else{
	        	System.out.println("no va");
	        }
	        
        }
        catch (InvocationTargetException e){
            System.err.println ("! GRaLauncher: No se ha podidio cargar la clase " + e);
        }
        catch (SecurityException e){
            System.err.println ("! GRaLauncher: No se ha podido cargar la clase! " + e);
        }
        catch (ClassNotFoundException e){
            System.err.println ("! GRaLauncher: No se ha podido cargar la clase!  " + e);
        }
        catch (IllegalAccessException e){
            System.err.println ("! GRaLauncher: No se ha podido cargar la clase! " + e);
        }
        catch (InstantiationException e){
            System.err.println ("! GRaLauncher: No se ha podido cargar la clase! " + e);
        }
    }
    
    /**
     * Envia el agente seleccionado a otra agencia.
     */
    private void editSendTo(String s, String clase){
        InetAddress destination = null;
        String server = null;
        String servername = null;
        String strLoPort = null;
        int loPort = port;
        
        server = s;

        try{
            
            // split server address into servername and port and determine host address
            server.trim();
            int portDelimiter = server.indexOf (':');
            if (portDelimiter != -1) {
                servername = server.substring (0, portDelimiter);
                strLoPort  = server.substring (portDelimiter + 1);
                loPort = Integer.parseInt (strLoPort);
            }   
            else{
                servername = server;          
            }
            destination = InetAddress.getByName (servername);
            raAgency.dispatchRa (this, clase, new RaAddress (destination, loPort, null));
        }
        catch (IndexOutOfBoundsException e){
            System.err.println("! GRaLauncher.editSendTo: Formato erroneo del puerto " + e);
        }
        catch (NumberFormatException e){
            System.err.println("! GRaLauncher.editSendTo: Formato erroneo del puerto " + e);
        }
        catch (UnknownHostException e){
            System.err.println("! GRaLauncher.editSendTo: No se puede determinar la dirccion del host " + e);
        }
    }
    
    
    /**
     * Borra el agente seleccionado.
     */
    private void editDestroy(String clase){
        raAgency.destroyRa (this, clase);
        listaAgentes.remove(clase);
    }

    /**
     * Inicializa el Thread del RaAgency.
     */
    private void startAgency(){
	System.out.println ("Inicializando la Agencia");
        raAgency.startAgency (this, port, raServer);
    }

    /**
     * Para el Thread de la agencia.
     */
    private void stopAgency(){
	System.out.println ("Parando la Agencia");
        raAgency.stopAgency (this);
    }
    
    
    
    /**
     * Recuerda el agente que se selecciono de la lista.
     */
//    public synchronized void valueChanged(ListSelectionEvent e){
//        int pos = list.getSelectedIndex();
//        if (pos == -1){
//        	selectedRa = null;
//        }else{
//        	selectedRa = listModel.elementAt (pos);
//            System.out.println ("seleccionado: " + selectedRa);
//        }
//        
//    }
    
    

	@Override
	public void agencyRaCreated(AgencyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agencyRaArrived(AgencyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agencyRaLeft(AgencyEvent e) {
		
		
	}

	@Override
	public void agencyRaDestroyed(AgencyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
}
