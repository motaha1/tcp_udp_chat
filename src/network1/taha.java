/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network1;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.DatagramSocketImpl;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Properties;
import java.util.Random;

public class taha extends DatagramSocket {
    private static int VER = 005;
  
    private static long start_time = System.currentTimeMillis();

    private int packet_mtu = 1500;                    
    private int packet_droprate = 0;                 
    private int[] packet_drop = new int[0];       
    private int packet_delay_min = 10;             
    private int packet_delay_max = 10;                          
    private Random rand = new Random(System.currentTimeMillis());
    private static boolean dump = false;             

    private static long packet_counter = 1L;         

    public taha(SocketAddress paramSocketAddress) throws SocketException {
        super(paramSocketAddress);                     
        loadProperties();                            
    }

    public taha(int paramInt, InetAddress paramInetAddress) throws SocketException {
        super(paramInt, paramInetAddress);            
        loadProperties();                             
    }

    public taha(DatagramSocketImpl paramDatagramSocketImpl) throws SocketException {
        super(paramDatagramSocketImpl);              
        loadProperties();                            }

    public taha(int paramInt) throws SocketException {
        super(paramInt);                               
        loadProperties();                             
    }

    public taha() throws SocketException {
        loadProperties();                               
    }

    public int getSendBufferSize() throws SocketException {
        return packet_mtu;                              // return max packet size
    }

    public void setSendBufferSize(int paramInt) throws SocketException {
        throw new SocketException("MTU is adjusted within the net.properties file");
    }                                                 // can't set this variable with builtin (super's) method


    private void rawSend(DatagramPacket paramDatagramPacket) throws IOException {
        super.send(paramDatagramPacket);               
    }                                                 


    public void send(DatagramPacket paramDatagramPacket) throws IOException {
        synchronized (paramDatagramPacket) {                            // thead safe
            if (paramDatagramPacket.getLength() > packet_mtu) {           // if packet size too big throw error
                throw new IOException("Packet length exceeds MTU");
            }

            boolean bool=false;                                           // bool is used for drop / no drop choice (default no drop)
            int N = rand.nextInt(100)+1;                                  // rand num for use in drop calc

            bool = (packet_droprate > N);                                 // do we drop or not?

            for (int i = 0; i < packet_drop.length; i++) {                // check list for enumerated packets to drop
                if (packet_counter - 1 == packet_drop[i])                   // is it a packet we drop?
                    bool = true;                                              // yes - we are going to drop it
            }

            new senderThread(this, paramDatagramPacket, packet_counter - 1L, bool);       // create a thread to send the packet
            packet_counter += 1L;                                         // incr counter
        }
    }


    private void loadProperties()  {                                  // read config file and set variables (drop_rate, max, min delay, etc)
        try {
            Properties localProperties = new Properties();
            String PROP = null;
            localProperties.load(new FileInputStream(PROP));
            // parse list of packets to drop - if any
            String[] arrayOfString = localProperties.getProperty("packet.droprate", "0").split(",");

            if (arrayOfString.length == 1) {                              
                packet_droprate = Integer.parseInt(arrayOfString[0]);       
            }
            else {                                                       
                packet_drop = new int[arrayOfString.length];               
                for (int i = 0; i < arrayOfString.length; i++) {            
                    packet_drop[i] = Integer.parseInt(arrayOfString[i]);     
                }
            }                                                           
            packet_delay_min = Integer.parseInt(localProperties.getProperty("packet.delay.minimum", "0"));
            packet_delay_max = Integer.parseInt(localProperties.getProperty("packet.delay.maximum", "0"));
            packet_mtu = Integer.parseInt(localProperties.getProperty("packet.mtu", "1500"));
            packet_mtu = (packet_mtu <= 0 ? Integer.MAX_VALUE : packet_mtu);

            if(dump) {                                            
                System.out.println("packet_droprate:"+packet_droprate);
                System.out.println("packet_delay_min:"+packet_delay_min);
                System.out.println("packet_delay_max:"+packet_delay_max);
                System.out.println("packet_mtu:"+packet_mtu);
                System.out.print("Dropping packets: ");
                for (int i = 0; i < packet_drop.length; i++) {
                    System.out.print( "drop_packet["+i+"]:"+packet_drop[i] +", " );
                    System.out.print( packet_drop[i] +", " );
                }
                System.out.println(" ");
                dump=false;                                           
            }

        } catch (Exception e){ e.printStackTrace(); }
    }


    private class senderThread extends Thread {
        taha socket;

        DatagramPacket packet;
        long id;
        boolean drop;                                         

        senderThread(taha paramSocket, DatagramPacket paramDatagramPacket, long paramLong, boolean paramBoolean) {
            socket = paramSocket;                                      
            packet = new DatagramPacket(paramDatagramPacket.getData(),    
                    paramDatagramPacket.getLength(),  
                    paramDatagramPacket.getAddress(), 
                    paramDatagramPacket.getPort());   
            id = paramLong;
            drop = paramBoolean;
            start();                  // ok, do it - do the send
        
        }
        public void run(){
            try {                                                     
                sleep( (long) (packet_delay_min + rand.nextFloat() * (packet_delay_max - packet_delay_min)));
                try {
                    if (!drop) socket.rawSend(packet);                     
                } catch (Exception localException) {
                    localException.printStackTrace();
                }
            } catch (InterruptedException localInterruptedException) {}
        }
    }
}