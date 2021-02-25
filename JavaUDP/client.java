import java.io.*;
import java.net.*;

class client {
    public static void main(String args[]) throws Exception {
		System.out.println("starting program");
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
		System.out.println("trying to get ip");
        InetAddress IPAddress = InetAddress.getByName("130.240.5.39");
		System.out.println("got IP");
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
		while(sendData[0] != '0'){
			
			System.out.println("Send Message:");
			String sentence = inFromUser.readLine();
			sendData = sentence.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 6121);
			clientSocket.send(sendPacket);
			System.out.println("Packet sent...");

/*	        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);
			String modifiedSentence = new String(receivePacket.getData());
			System.out.println("FROM SERVER:" + modifiedSentence);*/
		}

        clientSocket.close();
    }
}