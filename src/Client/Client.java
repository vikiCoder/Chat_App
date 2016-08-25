package Client;

import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import ClientUI.ClientChatWindow;

public class Client {
	
	private String name;
	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private String serverIP;
	private int port;
	private ClientChatWindow GUI;
	private Thread communication;
	
	public Client(String name, String serverIP, int port, ClientChatWindow GUI){
		this.name = name;
		this.serverIP = serverIP;
		this.port = port;
		this.GUI = GUI;
	}
	
	public void connect(){
		
		try {
			socket = new Socket(InetAddress.getByName(serverIP), port) ;
		} catch (IOException e) {
			GUI.showDialogBox("Could not connect to server. Try again.");
			GUI.restartApp();
		}
		
		if(socket != null){
			try {
				output = new ObjectOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				System.out.println("Client: Couldn't setup the output stream");
			}
			
			try {
				input = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				System.out.println("Client: Couldn't setup the input stream");
			}
			
			communication = new Thread(){
				public void run(){
					while(true)
						receiveMessage();
				}
			};
			communication.start();
		}
	}
	
	public void sendMessage(String message){
		if(!message.equals("")){
			try {
				output.writeObject(name + ": " + message + "\n");
			} catch (IOException e) {
				GUI.showDialogBox("Server disconnected. Login again.");
				GUI.restartApp();
				communication.stop();
			}
		}
	}
	
	public String receiveMessage(){
		String message = "";
		try {
			message += (String) input.readObject();
		} catch (ClassNotFoundException | IOException e) {
			GUI.showDialogBox("Server disconnected. Login again.");
			GUI.restartApp();
			communication.stop();
		}
		GUI.showMessage(message);
		return message;
	}

}
