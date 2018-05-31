/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericmulticlientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;


/**
 *
 * @author claudiu
 */
public class GenericMultiClientServer {   
    public static void main(String[] args) throws IOException 
    {
 
        if (args.length != 1) {
            System.err.println("Utilizare: java GenericMultiClientServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);
        
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
             
            System.out.println("Asteapta conexiune client pe portul " +
            serverSocket.getLocalPort() + "...");

            while (true) {
                new ServerThread(serverSocket.accept()).start();
            }
            
        } catch (IOException e) {
            System.err.println("Conexiune esuata pe portul " + portNumber);
            System.exit(-1);
        }
    }
    
}


