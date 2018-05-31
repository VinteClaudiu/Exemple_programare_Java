/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genericclient;

/**
 *
 * @author claudiu
 */

import java.net.*;
import java.io.*;

public class GenericClient
{
    public static void main(String[] args)
    {
        if (args.length != 2) {
            System.err.println("Utilizare: java GenericClient "
                    + "<host name>  <port number>");
            System.exit(1);
        }

        String serverName = args[0];
        int port = Integer.parseInt(args[1]);
      
        try
        {
            System.out.println("Conectare la " + serverName
                             + " pe portul " + port);
            Socket client = new Socket(serverName, port);
            System.out.println("Conectat la "
                      + client.getRemoteSocketAddress());
            
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
         
            // Read from command line
            BufferedReader commandLine = new java.io.BufferedReader(new InputStreamReader(System.in));

            // Loop until the word "exit" is typed
            while(true)
            {
                String s = commandLine.readLine();
                if (s.equalsIgnoreCase("exit"))
                {
                    in.close();
                    out.close();
                    client.close(); // close down connection
                    System.exit(0); // exit program
                } else {
                    out.writeUTF(s + " (" + client.getLocalSocketAddress() + ")" );
                }
                
                System.out.println(in.readUTF());
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
}