package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ChronosModel;
import model.LoginModel;

public class LoginWindow implements ChronosWindow, ActionListener {
	LoginModel model;

	public LoginWindow(ChronosModel model) {
		setModel(model);
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
