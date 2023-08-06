/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network1;




import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.zip.InflaterInputStream;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JOptionPane;

public class udpserver {


	private JFrame frame;
	static InputStream in ;
	static OutputStream out;
	static ServerSocket serverSocket;
	static JLabel lblNewLabel_3;
	static JLabel lblNewLabel_4;
	static JLabel lblNewLabel_5;
	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					udpserver window = new udpserver();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
                
                
               try {

          byte [] buffer = new byte[11];

         taha ttsocket = new taha(4000); // Note NOT using DatagramSocket

          DatagramPacket packet = new DatagramPacket(buffer,buffer.length);

          ttsocket.receive(packet);

          InetAddress client = packet.getAddress();

          System.out.println(" Received'"+new String(buffer)+"' from " +packet.getAddress().getHostAddress());

      }

      catch(Exception e){ e.printStackTrace(); }

        
                
                
                
		
 try{
 //    int SPort = 4200;
    DatagramSocket srvskt = new DatagramSocket(4000);
    byte[] data =new byte[1024];
    
    String k = "test.txt";
            
    
    String path = udpclient.tt();
    while(!path.equals(k)){
        break  ; 
        
        
    }
   
    FileOutputStream  FOS = new FileOutputStream(path);
    DatagramPacket srvpkt = new DatagramPacket(data,1024);
    
    int Packetcounter=0;//packet counter
    while(true)
       {
           srvskt.receive(srvpkt);
           Packetcounter++;
           String words = new String(srvpkt.getData());
           InetAddress ip= srvpkt.getAddress();
           int port = srvpkt.getPort();
         
           FOS.write(data);
           //out16.flush();
        

        }
 }
    catch(Exception x){
            
           JOptionPane.showMessageDialog(null, x);
           
            
            }
     
     
     
 }

    
		
        
        //}
	/**
	 * Create the application.
	 */
	public udpserver() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(128, 128, 128));
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 527, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblServer = new JLabel("server");
		lblServer.setForeground(new Color(255, 255, 255));
		lblServer.setFont(new Font("Khmer OS Content", Font.BOLD, 15));
		lblServer.setBounds(186, 10, 192, 98);
		frame.getContentPane().add(lblServer);
		
		JLabel lblNewLabel = new JLabel("Status :  ");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(34, 111, 94, 34);
		frame.getContentPane().add(lblNewLabel);
                
                JLabel lblNewLabel8 = new JLabel("port :4000 ");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(34, 111, 94, 34);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Client connection : ");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblNewLabel_1.setBounds(30, 157, 208, 39);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("port num: ");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblNewLabel_2.setBounds(30, 208, 192, 33);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Running");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_3.setForeground(new Color(255, 255, 0));
		lblNewLabel_3.setBounds(129, 120, 249, 22);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("no new Clients");
                lblNewLabel_4.setText("new Client accepted");
		lblNewLabel_4.setForeground(new Color(127, 255, 0));
		
		lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_4.setBounds(224, 157, 249, 39);
		frame.getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("4000");
		lblNewLabel_5.setFont(new Font("Dialog", Font.BOLD, 18));
		
		lblNewLabel_5.setBounds(224, 208, 221, 33);
		frame.getContentPane().add(lblNewLabel_5);
	}

}
