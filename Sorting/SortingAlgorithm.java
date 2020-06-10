import java.net.*;
import java.io.*;
import java.util.Scanner;

public class SortingAlgorithm 
{
    static DataInputStream dis = null;
    static DataOutputStream dout = null;
    static Scanner sc = new Scanner(System.in);

    public static void server() throws IOException 
    {
        ServerSocket ss = new ServerSocket(8080);
        System.out.println("Welcome Server!");
        Socket s = ss.accept();
        dis = new DataInputStream(s.getInputStream());
        dout = new DataOutputStream(s.getOutputStream());
        String str = (String)dis.readUTF();
        int n = Integer.parseInt(str);
        System.out.println("No. of elemnets: " + n);
        int i = 0;
        int arr[] = new int[n];
        while(i < n)
        {
            str = (String)dis.readUTF();
            arr[i] = Integer.parseInt(str);
            i++;
        }
        System.out.println("Sorting using Bubble Sort Algorithm...");
        // int temp;
        arr = Sort(arr);
        for(i = 0; i < n; i++)
            dout.writeUTF(Integer.toString(arr[i]));
        System.out.println("Closing Server!");
        str = (String)dis.readUTF();
        while(str.equals("S"));
        s.close();
        ss.close();
    }

    public static void Client() throws IOException
    {
        Socket s = new Socket("localhost", 8080);
        System.out.println("Welcome in Client");
        dis = new DataInputStream(s.getInputStream());
        dout = new DataOutputStream(s.getOutputStream());
        System.out.println("Enter the number of elements to be sorted:");
        String str = sc.nextLine();
        int n = Integer.parseInt(str);
        dout.writeUTF(str);
        String arr[] = new String[n];
        System.out.println("Enter the array to be sorted:");
        for(int i = 0; i < n; i++)
        {
            arr[i] = sc.nextLine();
            dout.writeUTF(arr[i]);
        } 
        int i = 0;
        System.out.println("After Sorting:");
        while(i++ < n)
            System.out.println(i + ".  " + (String)dis.readUTF() );
        System.out.println("Closing Client");
        dout.writeUTF("Stop");
        s.close();
    }

    public static void main(String[] arg) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ans = new String();
        System.out.println("Enter Who are you (Server/Client):");
        ans = br.readLine();
        if(ans.equals("Server"))
            server();
        else
            Client();
    }

    private static int[] Sort(int[] arr)
    {
        int i, j, temp;
        for(i = 0; i < arr.length-1; i++)
        {
            for(j = 0; j < arr.length-i-1; j++)
            {
                if(arr[j] > arr[j+1])
                {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }
}