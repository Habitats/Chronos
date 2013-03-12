package client.gui.view;


import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import client.gui.GBC;
import client.gui.MainFrame;
import client.model.ChronosModel;
import client.model.LoginModel;


public class LoginWindow extends ChronosWindow implements ActionListener {
	LoginModel model;
	JTextField usernameField;
	JPasswordField passwordField;
	String username, password;
	JButton loginButton, cancelButton;

	public LoginWindow(ChronosModel model,MainFrame frame) {
	//public LoginWindow() {
		super(model, frame);
		setLayout(new GridBagLayout());
		usernameField = new JTextField();
		passwordField = new JPasswordField();
		loginButton = new JButton("Login");
		cancelButton = new JButton("Cancel");
		
		usernameField.setColumns(20);
		passwordField.setColumns(20);
		
		usernameField.addKeyListener(new UsernameListener());
		passwordField.addKeyListener(new PasswordListener());
		loginButton.addActionListener(new LoginAction());
		cancelButton.addActionListener(new CancelAction());
		
		add(new Label("Username:"), new GBC(0,0));
		add(usernameField, new GBC(1,0));
		add(new Label("Password:"), new GBC(0,1));
		add(passwordField, new GBC(1,1));
		add(loginButton, new GBC(0,2));
		add(cancelButton, new GBC(1,2));
	
		//setModel(model);
	}
	
	public class UsernameListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub	
		}
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
		}	
	}
	public class PasswordListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
		}
	}

	public class LoginAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	public class CancelAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	
	@Override
	public void setModel(ChronosModel model) {
		this.model = (LoginModel) model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.login("bob", "password");
	}
	/*
	public static void main(String[] args) {
		JFrame frame = new JFrame("Login");
		frame.add(new LoginWindow());
		frame.pack();
		frame.setVisible(true);
	}
	*/
}
