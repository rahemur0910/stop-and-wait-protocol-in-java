import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9876;
            
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];
            
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.print("Enter message to send to server: ");
            String message = inFromUser.readLine();
            sendData = message.getBytes();
            
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);
            
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            
            String response = new String(receivePacket.getData()).trim();
            System.out.println("Received from server: " + response);
            
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
