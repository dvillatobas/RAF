package raf.web.ra;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.springframework.stereotype.Component;

import raf.raservidor.RaDomain;
import raf.raservidor.RaModel;

@Component
public class RaDomainComponent{

    /**
     * Donde esta la configuracion del servidor.
     */
    String strConfigFile = "target" + File.separator + "classes" + File.separator + "raf" 
    						+ File.separator + "config" + File.separator + "radomain.config";

    /**
     * Puerto en el que escucha.
     */
    int port;

    /**
     * El modelo del servidor que realiza el trabajo realmente.
     */
    RaModel raModel;

    /**
     * Crea un nuevo RaModel e inicia el servicio que escucha en la red.
     */
    public RaDomainComponent(){
        Properties props = new Properties ();

        // lee las propiedades del fichero
        try {
            FileInputStream in = new FileInputStream (strConfigFile);
            props.load (in);
            in.close();
        }
        catch (FileNotFoundException e){
            System.err.println ("RaDomain: No se puede abrir el fichero de propiedades!");
        }
        catch (IOException e){
            System.err.println ("RaDomain: Ha fallado la lectura del fichero!");
        }

        // inicializa las propiedades
        try {
            port = Integer.parseInt(props.getProperty("port", "10102"));
        }
        catch (NumberFormatException e){
            port = 10102;
        }

        // Lanza el servidor RaModel
        raModel = new RaModel();
        raModel.startService(port);
       
    }

   


}