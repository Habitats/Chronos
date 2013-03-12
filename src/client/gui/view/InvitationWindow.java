package client.gui.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import client.gui.GBC;
import client.gui.MainFrame;
import client.model.ChronosModel;
import client.model.InvitationModel;


public class InvitationWindow extends ChronosWindow implements ActionListener {
	InvitationModel model;
	JButton acceptButton, declineButton;
	Date eventDate;
	
	public InvitationWindow(ChronosModel model,MainFrame frame) {
		
		super(model,frame);
		setModel(model);
		setLayout(new GridBagLayout());
		acceptButton = new JButton("Accept");
		declineButton = new JButton("Decline");
		
		acceptButton.addActionListener(new acceptAction());
		declineButton.addActionListener(new declineAction());
		
		add(new Label("EventName"), new GBC(0,0).setSpan(2,1));
		add(new Label("EventDescription"), new GBC(0,1).setSpan(2,1));
		add(acceptButton, new GBC(0,2));
		add(declineButton, new GBC(1,2));
	}
	
	public class acceptAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	public class declineAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void setModel(ChronosModel model) {
		this.model = (InvitationModel) model;
	}
}
