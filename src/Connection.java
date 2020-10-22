import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Connection {

	private JFrame frame;
	private JPanel panel;
	
	private String username;
	private char[] password;
	
	private boolean credentialsGiven = false;
	
	public Connection() {
		
		frame = new JFrame();
		frame.setSize(new Dimension(500, 500));
		
		panel = new JPanel();
		panel.setSize(new Dimension(500, 500));
		panel.setBackground(Color.GRAY);
		
		JTextField usernameField = new JTextField();
		usernameField.setPreferredSize(new Dimension(400, 50));
		panel.add(usernameField);
		
//		JTextField passwordField = new JTextField();
//		passwordField.setPreferredSize(new Dimension(400, 50));
//		panel.add(passwordField);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(400, 50));
		passwordField.setEchoChar('*');
		panel.add(passwordField);
		
		JButton loginButton = new JButton("Login");
		loginButton.setPreferredSize(new Dimension(400, 50));
		
		//need to check to make sure both username and password are given
		loginButton.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {
																username = usernameField.getText();
																password = passwordField.getPassword();
																credentialsGiven = true;
																frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		                                                    }
		});
		
		panel.add(loginButton);
		
		frame.add(panel);
		frame.setVisible(true);
		
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public char[] getPassword() {
		return this.password;
	}
	
	public boolean getCredentialsGiven() {
		return this.credentialsGiven;
	}
	
}
