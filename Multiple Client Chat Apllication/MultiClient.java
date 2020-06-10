import java.util.*;
import java.net.*;
import java.io.*;

public class MultiClient 
{
    public static void main(String arg[]) throws Exception
    {
        Socket sk = new Socket("localhost", 5000);
        BufferedReader cin = new BufferedReader ( new InputStreamReader(sk.getInputStream()) );
        PrintStream cout = new PrintStream(sk.getOutputStream());
        Scanner sc = new Scanner(System.in);
        String s;

        while(true)
        {
            System.out.println("Client: ");
            s = sc.nextLine();
            cout.println(s);
            if(s.equalsIgnoreCase("bye"))
            {
                System.out.println("Connection ended.");
                break;
            }
            s = cin.readLine();
            System.out.println("Server: " + s);
        }
        sk.close();
        cin.close();
        cout.close();
    }
}