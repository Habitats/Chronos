package client.gui.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JToggleButton;

import client.gui.MainFrame;
import client.model.ChronosModel;
import client.model.InvitationModel;


public class InvitationWindow extends ChronosWindow implements ActionListener {
	
	public InvitationWindow(ChronosModel model,MainFrame frame) {
		super(model,frame);
		setModel(model);
	}

	private InvitationModel model;
	private JTextField EventName, EventDescription;
	private JToggleButton AcceptButton, DeclineButton;
	private Date EventDate;
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setModel(ChronosModel model) {
		this.model = (InvitationModel) model;
	}
}
