package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;
import java.util.List;

public class Server {
	private static int port = 6789;
	private ServerSocket serverSocket;
	private List<ClientHandler> clients;
	static InetAddress  publicIpAddress;

	public static void main(String[] args) {

		 Server server = new Server();

		 



	        server.connect(port); 
		 
		 
		 


	}//main


	public void connect(int portNumber) {

		clients = new ArrayList<>();

		try {
			
//			
//			serverSocket = new ServerSocket();
//			InetSocketAddress sockAddr = new InetSocketAddress("0.0.0.0", port);
//			serverSocket.bind(sockAddr);
//			System.out.println("Server started on port " + portNumber);
			
			
			serverSocket = new ServerSocket(portNumber);
//			serverSocket=new ServerSocket(port, 0, publicIpAddress);
//			
			System.out.println("Server started on port " + portNumber);
			
			

			while (true) {
				Socket clientSocket = serverSocket.accept();

				System.out.println("New client connected");

				ClientHandler clientHandler = new ClientHandler(this, clientSocket);

				clients.add(clientHandler);
				clientHandler.start();

			}//while

		}//try
		
		catch (IOException e) {
            e.printStackTrace();
        }//catch
		
		finally {
            try {
                serverSocket.close();
            }//try
            catch (IOException e) {
                e.printStackTrace();
            }//catch
        }//finally
		
	}//connect
	
	
	public void clientExist(ClientHandler handler , String receiverName) {
		
for (ClientHandler client : clients) {
			
			if(client.getClientName().equals(receiverName)) {  
				handler.sendMessage("Client Exist response#"+"true#"+receiverName);
				
				return;
			}//if
				
		}//for

handler.sendMessage("Client Exist response#"+"false#"+receiverName);

		
	}//end clientexist
	
	public void sendGlobalMessage(String globalMessage) {
		
		for (ClientHandler client : clients) {
			client.sendMessage(globalMessage);
		}//end for
		
	}//global
	
	public void sendPrivateMessage(String senderName, String receiverName, String message) {
		
		for (ClientHandler client : clients) {
			
			if(client.getClientName().equals(receiverName)) {  
				
				client.sendMessage(senderName + "#" + message);
//				break;
			}//if
				
		}//for
		
	}//send
	
	public void removeClient(ClientHandler clientHandler) {
		clients.remove(clientHandler);
	}//remove
	
	


}
