package server;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;

public class ClientHandler extends Thread {
	private Server server;
	private Socket clientSocket;
	private PrintWriter output;
	private Scanner input;
	private String clientName;
	String receiverName;


	public ClientHandler(Server server, Socket clientSocket) {
		this.server = server;
		this.clientSocket = clientSocket;
	}

	public String getClientName() {
		return clientName;
	}


	public void sendMessage(String message) {
		output.println(message);
	}
	
	public void sendGlobalMessage(String message) {
		output.println(message);
		}
	
	
	


	@Override
	public void run() {
		try {
			output = new PrintWriter(clientSocket.getOutputStream(), true);

			input = new Scanner(clientSocket.getInputStream());


			if (input.hasNextLine()) {

				clientName = input.nextLine();
				System.out.println(clientName + " connected");
				


				
				
			}//if
	
			


			while (input.hasNextLine()) {
				String message = input.nextLine();
			

				
				if (message.equals("EXIT")) {
					  // Client wants to exit
                    server.removeClient(this);
                    clientSocket.close();
                    System.out.println(clientName + " disconnected");
                    break;
				 }//if
				
								

				else {


				String[] parts = message.split("#");

				receiverName = parts[0];
				String privateMessage = parts[1];
				
				
				
				if(receiverName.equals("Client Exist")) {
					
					server.clientExist(this,privateMessage);
					
				}//end else if client exist
				
				

//				
				
				
				
				if(receiverName.equals("GLOBAL CHAT")) {
					server.sendGlobalMessage(message);
					
				}//end else if for global
				
				

				
				else {
				server.sendPrivateMessage(clientName, receiverName, privateMessage);
				}
				
				}//else
				  
				
			}//while



		}//try
		 catch (IOException e) {
	            e.printStackTrace();
	        }//catch
	}//run




}//class 
