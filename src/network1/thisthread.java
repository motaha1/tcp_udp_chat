package network1;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP2020
 */
public class thisthread implements Runnable {
    boolean loop = true; 
    ServerSocket welcomesocket;
    Socket connectionSocket; 
    ArrayList <InetSocketAddress> user = new ArrayList <InetSocketAddress>(); 
    // DefaultListModel model = new DefaultListModel(); 
    InetSocketAddress msg; 
    tcpServer server;
    ptop cc; 
   
  
    public void setPortServer(int localport) throws SocketException, IOException

    {
         welcomesocket = new ServerSocket(localport);
         server.jLabel6.setText(""+localport);
                
    }
    
    
     public void start()
	{
		Thread t = new Thread(this);
		t.start();
	}
     
     public void stop () throws IOException
     {
         loop = false; 
         welcomesocket.close();
     }
     
    
     
     @Override
	public void run()
	{
             try {
            Thread.sleep(0);

            while (loop)
            {
                    this.connectionSocket= welcomesocket.accept();
                    msg = new InetSocketAddress (this.connectionSocket.getInetAddress().getHostAddress(),this.connectionSocket.getPort());
                    user.add(msg); 
                     System.out.print(this.connectionSocket.getInetAddress().getHostAddress()+","+this.connectionSocket.getPort()+"\n");
                       String s = this.connectionSocket.getInetAddress().getHostAddress()+","+this.connectionSocket.getPort();
             
                    //  model.addElement(s);
                   
                    // cc.online.setModel(model);
                     cc.model.addElement(s);
                     // cc.model.addElement(s);
                      server.online1.append(s);
                
            }
            
            }
            catch (Exception e)
            {
                
            }
        }
        
        
        
   
    
}
