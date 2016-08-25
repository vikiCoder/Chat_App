package Server;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;
import java.util.ArrayList;

import ServerUI.ServerChatWindow;

public class Server {

	
	private String name;
	private ServerSocket serverSocket;
	private int port;
	private ArrayList<Connection> clients;
	private ServerChatWindow GUI;
	private Server thisServer;
	
	public Server(String name, int port, ServerChatWindow GUI){
		this.name = name;
		this.port = port;
		this.GUI = GUI;
		clients = new ArrayList<Connection>();
		
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			GUI.showDialogBox("The port is being used by other application. Try another port.");
			GUI.restartApp();
		}
		
		thisServer = this;
	}
	
	public void connect(){
		if(serverSocket!=null){
			Thread newConnection = new Thread(){
				public void run(){
					while(true){
						Socket socket = null;
						try {
							socket = serverSocket.accept();
						} catch (IOException e) {
							continue;
						}
						if(socket != null)
							clients.add(new Connection(socket, thisServer));
						GUI.updateTotalUsers(clients.size());
					}
				}
			};
			newConnection.start();
		}
	}
	
	public void receiveMessage(String message){
		GUI.showMessage(message);
		for(Connection x : clients)
			x.sendMessage(message);
	}
	
	void disconnect(Connection connectionToRemove){
		clients.remove(connectionToRemove);
		GUI.updateTotalUsers(clients.size());
	}
	
	public int getTotalConnected(){
		return clients.size();
	}
	
	public int getPort(){
		if(serverSocket != null)
			return serverSocket.getLocalPort();
		else 
			return -1;
	}
}
