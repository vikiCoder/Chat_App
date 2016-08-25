package ServerUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class StartServer extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField port;
	private JButton btnLogin;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartServer createServer = new StartServer();
					createServer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StartServer() {
		setResizable(false);
		setTitle("Create Server");
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
				port.grabFocus();
			}
		});
		name.setColumns(10);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(106, 95, 31, 14);
		contentPane.add(lblPort);
		
		port = new JTextField();
		port.setToolTipText("Ex. 8191");
		port.setBounds(79, 113, 86, 20);
		contentPane.add(port);
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
		port.setColumns(10);
		
		btnLogin = new JButton("Start");
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
		ServerChatWindow chatwindow = new ServerChatWindow(name.getText(), port.getText());
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
