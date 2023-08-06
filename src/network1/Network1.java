/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network1;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import static java.util.logging.Logger.global;
import static jdk.nashorn.internal.objects.NativeRegExp.global;
;

/**
 *
 * @author DELL
 */
public class Network1 {
  
// global a_variable = 5




    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        // TODO code application logic here

   //  tcpservere o = new tcpservere(); 
    //  o.setVisible(true);
  // part1 p = new part1(); 
   //p.show();
      udpclient l = new udpclient();
       
        l.setTitle("ClientChat");
         tcpserverr.main(args);
       l.show();
        l.jButton5.hide();
         
        while(true){
l.jButton5.doClick();


       
   
        
        
      // l.jButton5ActionPerformed();
      //  l.run();
      //  Channel k = new Channel();
       //  k.bind(l.port.getText());
      //   k.start();
         
         
// l.start();
// l.run();
  
    
     
     
       
    }
    
    }}
