import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
    public static void main(String arg[]) throws Exception
    {
        Socket s = new Socket("localhost", 5000);
        if(s.isConnected())
        {
            System.out.println("Connected to Server.");
            DataInputStream msg = new DataInputStream(System.in);
            String str = "Start Chat.................................................................................................................................";
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            dout.writeUTF(str);
            System.out.println(str);
            DataInputStream din = new DataInputStream(s.getInputStream());

            while(true)
            {
                System.out.println("Client: ");
                str = msg.readLine();
                dout.writeUTF(str);
                str = din.readUTF();
                System.out.println("Server: " + str);
            }
        }
    }
}