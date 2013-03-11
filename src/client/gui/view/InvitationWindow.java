package client.gui.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JToggleButton;

import client.model.ChronosModel;

import model.InvitationModel;

public class InvitationWindow extends ChronosWindow implements ActionListener {
	InvitationModel model;
	JTextField EventName, EventDescription;
	JToggleButton AcceptButton, DeclineButton;
	Date EventDate;
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setModel(ChronosModel model) {
		// TODO Auto-generated method stub
		
	}
	
	

}
