package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class ServerUDP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("asd");
		Scanner scanB = new Scanner(System.in);
		
		NetServer server = new NetServer();
		
		
		boolean run = true;
		
		while(run == true){
			System.out.println("->");
			if(scanB.nextInt() == 0){
				run = false;
			}
		}
		
		System.out.println("System exiting");
		scanB.close();
		System.exit(0);

		
	}

}

class NetServer implements Runnable{
	
	DatagramSocket serverSocket;
	DatagramPacket receivePacket;
	DatagramPacket sendPacket;
	byte[] sendData = new byte[1024];
	byte[] receiveData = new byte[1024];
	
	NetServer(){
		try {
			serverSocket = new DatagramSocket(8000);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		receivePacket = new DatagramPacket(receiveData, receiveData.length);
		sendPacket = new DatagramPacket(sendData, sendData.length);
		
		new Thread(this).start();
		
		
	}
	
	public void run(){
		
		while(true){
			try {
				serverSocket.receive(receivePacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//printout the incoming data
			System.out.println(new String(receivePacket.getData()));
			
			//Get ip-address and port from client
//            InetAddress IPAddress = receivePacket.getAddress();
//            int port = receivePacket.getPort();
			
			
		}
	}
	
}
