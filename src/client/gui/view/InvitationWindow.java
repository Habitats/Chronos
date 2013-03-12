package client.gui.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

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
		acceptButton = new JButton();
		declineButton = new JButton();
		
		acceptButton.addActionListener(new acceptAction());
		declineButton.addActionListener(new declineAction());
		
		
		
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
