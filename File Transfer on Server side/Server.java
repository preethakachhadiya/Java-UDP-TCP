import java.io.*;
import java.net.*;
import java.util.*;

class Server 
{
    public static void main(String arg[]) throws Exception
    {
        ServerSocket ss = new ServerSocket(5000);
        Socket s = ss.accept();
        System.out.println("Connected......................");
        FileInputStream fin = new FileInputStream("Send.txt");
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        int r;
        while( (r=fin.read()) != -1 )
            dout.write(r);
        System.out.println("File Transfer Completed!");
        s.close();
        ss.close();
    }
}