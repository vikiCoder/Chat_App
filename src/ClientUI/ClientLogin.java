package ClientUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientLogin extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField ip;
	private JTextField port;
	private JButton btnLogin;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientLogin frame = new ClientLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ClientLogin() {
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(250, 350);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(103, 32, 38, 14);
		contentPane.add(lblName);
		
		name = new JTextField();
		name.setToolTipText("viki");
		name.setBounds(56, 51, 132, 20);
		contentPane.add(name);
		name.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ip.grabFocus();
			}
		});
		name.setColumns(10);
		
		JLabel lblServerIp = new JLabel("Server IP:");
		lblServerIp.setBounds(93, 91, 57, 14);
		contentPane.add(lblServerIp);
		
		ip = new JTextField();
		ip.setToolTipText("Ex. 127.0.0.1");
		ip.setBounds(69, 109, 106, 20);
		contentPane.add(ip);
		ip.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				port.grabFocus();
			}
		});
		ip.setColumns(10);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(106, 154, 31, 14);
		contentPane.add(lblPort);
		
		port = new JTextField();
		port.setToolTipText("Ex. 8191");
		port.setBounds(79, 171, 86, 20);
		port.setColumns(10);
		port.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(isValidPort())
					showChatWindow();
				else{
					JOptionPane.showMessageDialog(null, "Please enter a valid port number. Ex.:8080");
					port.grabFocus();
				}
			}
		});
		contentPane.add(port);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(isValidPort())
					showChatWindow();
				else{
					JOptionPane.showMessageDialog(null, "Please enter a valid port number. Ex.:8080");
					port.grabFocus();
				}
			}
		});
		btnLogin.setBounds(84, 239, 75, 23);
		contentPane.add(btnLogin);
	}
	
	private void showChatWindow(){
		ClientChatWindow chatwindow = new ClientChatWindow(name.getText(), ip.getText(), port.getText());
		chatwindow.show();
		dispose();
	}
	
	private boolean isValidPort(){
		try{
			Integer.parseInt(port.getText());
			return true;
		}catch(Exception e){
			return false;
		}
	}
}
