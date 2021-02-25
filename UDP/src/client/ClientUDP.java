package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientUDP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanS = new Scanner(System.in);
		
		NetClient client = new NetClient();
		
		boolean run = true;
		
		while(run == true){
			
			String indata = scanS.next();
			
			if(indata.compareTo("exit") == 0){
				run = false;
			}
			else{
				client.send(indata);
			}
					
		}

	}

}

class NetClient {

	InetAddress IPAddress;
	DatagramSocket clientSocket;
	DatagramPacket receivePacket;
	DatagramPacket sendPacket;
	byte[] sendData = new byte[1024];
	byte[] receiveData = new byte[1024];

	NetClient() {
		try {
			IPAddress = InetAddress.getByName("localhost");
			clientSocket = new DatagramSocket();
		} catch (UnknownHostException | SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void send(String s) {
		sendData = s.getBytes();
		sendPacket = new DatagramPacket(sendData,
				sendData.length, IPAddress, 8000);
		System.out.println(sendPacket);
		
		try {
			clientSocket.send(sendPacket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}






