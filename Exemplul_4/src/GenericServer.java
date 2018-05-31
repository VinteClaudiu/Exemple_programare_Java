/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genericserver;

/**
 *
 * @author claudiu
 */

import java.net.*;
import java.io.*;


public class GenericServer
{
    private static ServerSocket serverSocket;
    
    public static void main(String [] args) throws IOException
    {
       int port = Integer.parseInt(args[0]);
       
       serverSocket = new ServerSocket(port);
       
       DataInputStream in = null;
       DataOutputStream out = null;
   
        try
        {
           System.out.println("Asteapta conexiune client pe portul " +
           serverSocket.getLocalPort() + "...");

           Socket client = serverSocket.accept();
           System.out.println("Conectat la "
                 + client.getRemoteSocketAddress());

           in = new DataInputStream(client.getInputStream()); 
           out = new DataOutputStream(client.getOutputStream());
           String clientMsg = null;

           while (true) {
               if (!client.isConnected()) {
                   client.close();
                   System.exit(0);
               }
               clientMsg = in.readUTF();

               System.out.println("client: " + clientMsg);            
               Thread.currentThread().sleep(300);

               out.writeUTF("ECHO server: " + clientMsg);
           }
        } catch (EOFException eof) {
            System.out.println("Deconectare din partea clientului!");
        } catch (InterruptedException i)
        {
            i.printStackTrace();
        } catch(SocketTimeoutException s)
        {
           System.out.println("Socket timed out!");
        } catch(IOException e)
        {
           e.printStackTrace();
        } finally {
//            in.close();
//            out.close();
           serverSocket.close();
        }
    }
    
}