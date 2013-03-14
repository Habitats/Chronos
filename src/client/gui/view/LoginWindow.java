package client.gui.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import client.gui.GBC;
import client.gui.GBC.Align;
import client.gui.MainFrame;
import client.model.ChronosModel;
import client.model.LoginModel;

public class LoginWindow extends ChronosWindow {
	private LoginModel model;
	private JTextField usernameField;
	private JPasswordField passwordField;
	String username, password;
	private JButton loginButton, cancelButton;
	private JLabel statusLabel;

	public LoginWindow(ChronosModel model, MainFrame frame) {
		super(model, frame);
		setModel(model);
		setLayout(new GridBagLayout());
		usernameField = new JTextField();
		passwordField = new JPasswordField();
		loginButton = new JButton("Login");
		cancelButton = new JButton("Cancel");

		usernameField.setColumns(15);
		passwordField.setColumns(15);

		loginButton.addActionListener(new LoginAction());
		cancelButton.addActionListener(new CancelAction());

		statusLabel = new JLabel(" ");
		statusLabel.setForeground(Color.red);

		add(new Label("Username:"), new GBC(0, 0, Align.FULL_WIDTH).setAnchor(GridBagConstraints.SOUTH));
		add(usernameField, new GBC(0, 1, Align.FULL_WIDTH).setSpan(2, 1).setAnchor(GridBagConstraints.NORTH));
		add(new Label("Password:"), new GBC(0, 2, Align.FULL_WIDTH).setAnchor(GridBagConstraints.SOUTH));
		add(passwordField, new GBC(0, 3, Align.FULL_WIDTH).setSpan(2, 1).setAnchor(GridBagConstraints.NORTH));
		add(statusLabel, new GBC(0, 4, Align.FULL_WIDTH));
		add(loginButton, new GBC(0, 5, Align.LEFT_BOTTOM).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.FIRST_LINE_START));
		add(cancelButton, new GBC(1, 5, Align.RIGHT_BOTTOM).setFill(GridBagConstraints.NONE).setAnchor(GridBagConstraints.FIRST_LINE_END));
	}

	private class LoginAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.login(usernameField.getText(), passwordField.getText());
		}
	}

	private class CancelAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	public JLabel getStatusLabel() {
		return statusLabel;
	}

	@Override
	public void setModel(ChronosModel model) {
		this.model = (LoginModel) model;
		this.model.setView(this);
	}
}
