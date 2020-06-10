import java.net.*;
import java.io.*;

public class Client_UDP
{
	public static void main(String[] args) throws Exception
	{
		DatagramSocket socket=new DatagramSocket();
		InetAddress ip=InetAddress.getLocalHost();
		byte sendByte[]=new byte[1024];
		byte receiveByte[]=new byte[1024];
		while(true)
		{
			DataInputStream din=new DataInputStream(System.in);
			System.out.print("Client:");
			String sendStr=din.readLine();
			sendByte=sendStr.getBytes();
			DatagramPacket sendPacket=new
			DatagramPacket(sendByte,sendByte.length,ip,1800);
			socket.send(sendPacket);
			DatagramPacket receivePacket=new
			DatagramPacket(receiveByte,receiveByte.length);
			socket.receive(receivePacket);
			String receiveStr=new String(receivePacket.getData());
			receiveStr=receiveStr.trim();
			System.out.println("Server:"+receiveStr);
        }
	}
} 