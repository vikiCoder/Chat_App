package ClientUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import Client.Client;
import ClientUI.ClientLogin;

public class ClientChatWindow extends JFrame {

	private JPanel contentPane;
	private JTextField message;
	private JTextArea history;
	private JLabel lblServer;
	private JLabel lblStatus;
	
	private Client client;

	public ClientChatWindow(String clientName, String serverIP, String serverPort) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		setTitle(clientName);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		message = new JTextField();
		message.setBounds(13, 330, 388, 20);
		message.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				client.sendMessage(message.getText());
				message.setText("");
			}
			
		});
		
		contentPane.add(message);
		message.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.setBounds(413, 329, 68, 23);
		btnSend.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				client.sendMessage(message.getText());
				message.setText("");
			}
			
		});
		contentPane.add(btnSend);
		
		history = new JTextArea();
		history.setEditable(false);
		history.setBounds(10, 10, 443, 290);
		
		JScrollPane scrollPane = new JScrollPane(history);
		scrollPane.setBounds(13, 39, 468, 279);
		contentPane.add(scrollPane);
		
		lblServer = new JLabel("Server : ");
		lblServer.setBounds(10, 11, 51, 14);
		contentPane.add(lblServer);
		
		lblStatus = new JLabel();
		lblStatus.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblStatus.setBounds(63, 8, 405, 20);
		contentPane.add(lblStatus);
		
		if(serverIP.equals(""))
				serverIP = "localhost";
		client = new Client(clientName, serverIP, Integer.parseInt(serverPort), this);
		lblStatus.setText("Connected to " + serverIP);
		client.connect();
	}
	
	public void showMessage(String message){
		SwingUtilities.invokeLater(
			new Runnable(){
				public void run(){
					history.append(message);
				}
			}
		);
	}
	
	public void showDialogBox(String message){
		JOptionPane.showMessageDialog(null, message);
	}
	
	public void restartApp(){
		ClientLogin login = new ClientLogin();
		login.show();
		exit();
	}
	
	public void exit(){
		SwingUtilities.invokeLater(
			new Runnable(){
				public void run(){
					dispose();
				}
			}
		);
	}
}
