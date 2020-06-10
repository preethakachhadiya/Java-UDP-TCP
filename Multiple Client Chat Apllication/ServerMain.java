import java.util.concurrent.*;
import java.util.*;
import java.net.*;
import java.io.*;

public class ServerMain
{
    int port;
    ServerSocket server = null;
    Socket client = null;
    ExecutorService pool = null;
    int clientCount= 0;

    private ServerMain(int port)
    {
        this.port = port;
        pool = Executors.newFixedThreadPool(5);
    }

    private void startServer() throws IOException
    {
        server = new ServerSocket(5000);
        System.out.println("Server Started.");
        System.out.println("Any Client can stop server by sending -1.");

        while(true)
        {
            client = server.accept();
            clientCount++;
            ServerThread runnable = new ServerThread(client, clientCount, this);
            pool.execute(runnable);
        }
    }

    private static class ServerThread implements Runnable
    {
        ServerMain server = null;
        Socket client = null;
        BufferedReader cin;
        PrintStream cout;
        Scanner sc = new Scanner(System.in);
        int id;
        String s;

        public ServerThread(Socket client, int count, ServerMain server) throws IOException
        {
            this.client = client;
            this.server = server;
            this.id = count;
            System.out.println("Connection " + id + "established with client " + client);
            cin = new BufferedReader( new InputStreamReader(client.getInputStream()) );
            cout = new PrintStream(client.getOutputStream());
        }

        public void run()
        {
            int x = 1;

            try
            {
                while(true)
                {
                    s = cin.readLine();
                    System.out.println("Client {" + id + "} : " + s);
                    //System.out.println("Server");
                    //s = sc.nextLine();
                    StringBuffer sb = new StringBuffer();
                    sb.append(s);
                    sb.reverse();
                    if(s.equalsIgnoreCase("bye"))
                    {
                        cout.println("BYE!");
                        x = 0;
                        System.out.println("Connection Ended.");
                        break;
                    }
                    cout.println(sb);
                }
                cin.close();
                client.close();
                cout.close();
                
                if(x == 0)
                {
                    System.out.println("Cleaning up.");
                    System.exit(0);
                }
            }
            catch(IOException ex)
            {
                System.out.println("Error: " + ex);
            }
        }
    }

    public static void main(String arg[]) throws IOException
    {
        ServerMain serverobj = new ServerMain(5000);
        serverobj.startServer();
    }
}
