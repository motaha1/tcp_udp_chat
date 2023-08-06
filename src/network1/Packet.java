package network1;

import java.nio.*;

/* Written By Dr. Raed Alqadi to get you started with Assignment 3
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author raed
 */
public class Packet {

    static final int MAX_DATA_LENGTH = 20;// Change this to 1024 or 512
    /*
      * Similar to structure,I will make them public but that is a Very Bad idea
      * These should be made private and use Accessors, Successors
      * These are not actual Packet fields, but will be similar
     */
    public char charValue = 0; 
    public int intValue = 0; 
    public float floatValue = 0; 
    public int dataLength = 0; 

    public ByteBuffer buffer = null;
    byte[] data = new byte[MAX_DATA_LENGTH];
    static final int HEADER_LENGTH = (int) (2 + 4 + 4 + 4);// Constat, addjust to your case

    // You can add constructor to allocate the same as the data length instead of max
    public Packet() {
        init('A', 0, 0);
    }

    public Packet(char cv, int iv, float fv) {
        this.init(cv, iv, fv);
    }

    private void init(char cv, int iv, float fv) {
        charValue = cv; // 2 bytes = b.getChar(0); // index 0, 2 bytes
        intValue = iv; //4 bytes  b.getInt(0 + 2 + 4);// stored at index 2
        floatValue = fv; // 4 bytes = ; //4byte b.getFloat(0 + 2 + 4);//offset 6
        dataLength = 0; //4 bytes; = b.getInt(0 + 2 + 4+4);
        buffer = null;

    }
    // You can read a chunck of data from a file and add it by using this functio
    public boolean addData(byte[] data, int n) {// n is the number of bytes to add
        // n should be more the data.length
        dataLength = n;
        if (n > MAX_DATA_LENGTH) {
            System.out.println("Data too big");
            return false;
        }
        for (int i = 0; i < n; i++) {
            this.data[i] = data[i];
        }
        return true;
    }

    public ByteBuffer toByteBuffer() {
        buffer = ByteBuffer.allocate(HEADER_LENGTH + MAX_DATA_LENGTH);
        buffer.clear();
        buffer.putChar(charValue); // 2 bytes;
        buffer.putInt(intValue);//4 bytes

        buffer.putFloat(floatValue);//4 bytes

        buffer.putInt(dataLength);  // Save this, it is important to save it
        buffer.put(data, 0, dataLength);//copy ony the vailable bytes
        return buffer;

    }

    public void printPacketAsArray() {
        if (buffer != null) {
            Packet.printBufferHex(buffer, this.getPacketLength());
        } else {
            System.out.println("Buffer is , Execute to Buffer");
        }

    }

    static void printBufferHex(ByteBuffer b, int limit) {
        //int limit = b.limit();
        String S = "[";
        for (int i = 0; i < limit; i++) {
            S += String.format("%02X", b.array()[i] & 0x00ff);
            if (i != (limit - 1)) {
                S += ", ";
            }
        }
        S += "]\n";
        S += "Position: " + b.position()
                + "\nLimit: " + b.limit();
        System.out.println(S);
    }

    public void printPacket() {
        String S = "";
        S += ("Packet Cotents= { ");
        S += ("\n\tcharValue: " + this.charValue);
        S += ("\n\tintValue: " + this.intValue);
        S += ("\n\tfloatValue: " + this.floatValue);
        S += ("\n\tdataLength: " + this.dataLength);

        int limit = dataLength;
        S += "\n\tdata =[\n\t";
        for (int i = 0; i < limit; i++) {
            S += String.format("%02X", (byte) data[i] & 0x00ff);
            if (i != (limit - 1)) {
                S += ", ";
            }
        }
        S += "]";
        S += "\n}\n";
        System.out.println(S); // instead of this use next 
        // return S; // use this to return a String to print it in GUI
        // change function type to String
    }

    public int getPacketLength() {
        return HEADER_LENGTH + dataLength;
    }

    public void extractPacketfromByteBuffer(ByteBuffer buf) {
        try {
            char cV = buf.getChar(0); // index 0, 2 bytes
            int iV = buf.getInt(0 + 2 + 4);// stored at index 2
            float fV = buf.getFloat(0 + 2 + 4);//offset 6
            int dataLen = buf.getInt(0 + 2 + 4 + 4);
            //  System.out.println(">> BaLength:= "+dataLen);
            byte[] ba = new byte[dataLen];
            for (int i = 0; i < dataLen; i++) {
                byte bt = (byte) buf.get(0 + 2 + 4 + 4 + 4 + i);
                ba[i] = bt;
            }
            this.charValue = cV;
            this.data = ba;
            this.dataLength = dataLen;
            this.floatValue = fV;
            this.intValue = iV;
            //    this.printPacket(); // to show it works 

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
