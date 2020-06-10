import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server
{
    public static void main(String arg[]) throws Exception 
    {
        ServerSocket ss = new ServerSocket(5000);
        Socket s = ss.accept();
        DataInputStream din = new DataInputStream(s.getInputStream());
        String str = din.readUTF();
        System.out.println("Client: " + str);
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        DataInputStream msg = new DataInputStream(System.in);

        while(true)
        {
            str = din.readUTF();
            System.out.println("Client: " + str);
            System.out.println("Server: ");
            str = msg.readLine();
            dout.writeUTF(str);
        }
    }
}

