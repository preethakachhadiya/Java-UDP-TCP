import java.util.*;
import java.io.*;
import java.net.*;

class Client
{
    public static void main(String arg[]) throws Exception{
        Socket s = new Socket("localhost", 5000);
        if(s.isConnected())
            System.out.println("Connected to server.");
        FileOutputStream fout = new FileOutputStream("Received.txt");
        DataInputStream din = new DataInputStream(s.getInputStream());
        int r;
        while( (r=din.read()) != -1 )
            fout.write( (char)r );
        s.close();
    }
}