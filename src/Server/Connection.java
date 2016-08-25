package Server;

import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Connection 
{
	private Server server;
	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	public Connection(Socket socket, Server server){
		this.server = server;
		this.socket = socket;
		
		try {
			output = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Server: Couldn't setup the output stream");
		}
		
		try {
			input = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.out.println("Server: Couldn't setup the input stream");		
		}
		
		Thread receive = new Thread(){
			public void run(){
				while(true){
					String message = receiveMessage();
						try {
							server.receiveMessage(message);
						} catch (Exception e) {
							disconnect();
							break;
						}
				}
			}
		};
		receive.start();
	}
	
	public void sendMessage(String message){
		try {
			output.writeObject(message);
		} catch (IOException e) {
			disconnect();
		}
	}
	
	public String receiveMessage(){
		String message = "";
		try {
			message += (String) input.readObject();
		} catch (ClassNotFoundException | IOException e) {}
		return message;
	}
	
	private void disconnect(){
		server.disconnect(this);
	}
}
