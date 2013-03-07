package gui.view;

import gui.GBC;

import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.JToggleButton;

import model.ChronosModel;
import model.LoginModel;

public class LoginWindow extends ChronosWindow implements ActionListener {
	protected LoginModel model;
	protected JTextField Username, Password;
	protected String username, password;
	protected JToggleButton LoginButton, CancelButton;

	public LoginWindow(ChronosModel model) {
		setLayout(new GridBagLayout());
		Username = new JTextField();
		Password = new JTextField();
		LoginButton = new JToggleButton("Login");
		CancelButton = new JToggleButton("Cancel");
		
		Username.setColumns(20);
		Password.setColumns(20);
		
		Username.addKeyListener(new UsernameListener());
		Password.addKeyListener(new PasswordListener());
		LoginButton.addActionListener(new LoginAction());
		CancelButton.addActionListener(new CancelAction());
		
		add(new Label("Username:"), new GBC(0,0));
		add(Username, new GBC(1,0));
		add(new Label("Password:"), new GBC(0,1));
		add(Password, new GBC(1,1));
		add(LoginButton, new GBC(0,2));
		add(CancelButton, new GBC(1,2));
	
		setModel(model);
		
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
}
