package ServerUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import Server.Server;

public class ServerChatWindow extends JFrame {

	private JPanel contentPane;
	private JTextArea history;
	private JLabel port;
	private JLabel totalUsers;
	
	private Server server;

	public ServerChatWindow(String serverName, String serverPort) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		setTitle("Server: " + serverName);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		history = new JTextArea();
		history.setEditable(false);
		history.setBounds(10, 10, 443, 290);
		
		JScrollPane scrollPane = new JScrollPane(history);
		scrollPane.setBounds(13, 39, 468, 313);
		contentPane.add(scrollPane);
		
		JLabel lblPort = new JLabel("Port :");
		lblPort.setBounds(375, 11, 33, 14);
		contentPane.add(lblPort);
				
		server = new Server(serverName, Integer.parseInt(serverPort), this);
		server.connect();
		
		port = new JLabel();
		port.setBounds(409, 11, 62, 14);
		port.setText(server.getPort()+"");
		contentPane.add(port);
		
		JLabel lblConnectedUsers = new JLabel("Connected users:");
		lblConnectedUsers.setBounds(18, 12, 102, 14);
		contentPane.add(lblConnectedUsers);
		
		totalUsers = new JLabel();
		totalUsers.setBounds(126, 12, 46, 14);
		totalUsers.setText(server.getTotalConnected()+"");
		contentPane.add(totalUsers);
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
	
	public void updateTotalUsers(int total){
		SwingUtilities.invokeLater(
			new Runnable(){
				public void run(){
					totalUsers.setText(server.getTotalConnected()+"");
				}
			}
		);
	}
	
	public void showDialogBox(String message){
		JOptionPane.showMessageDialog(null, message);
	}
	
	public void restartApp(){
		StartServer createServer = new StartServer();
		createServer.show();
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
