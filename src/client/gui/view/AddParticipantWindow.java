package client.gui.view;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;

import client.gui.GBC;
import client.gui.MainFrame;
import client.model.AddParticipantModel;
import client.model.ChronosModel;

public class AddParticipantWindow extends ChronosWindow implements ActionListener {
	JTextField userSearchField;
	JList userList;
	JButton searchButton, applyButton, cancelButton;
	private AddParticipantModel model;

	public AddParticipantWindow(ChronosModel model, MainFrame frame) {
		super(model, frame);
		setModel(model);
		setVisible(false);

		setLayout(new GridBagLayout());

		userSearchField = new JTextField();
		userList = new JList<>();
		searchButton = new JButton("Search");
		applyButton = new JButton("Apply");
		cancelButton = new JButton("Cancel");

		userList.setMinimumSize(new Dimension(80, 100));
		searchButton.addActionListener(new SearchAction());
		applyButton.addActionListener(new ApplyAction());
		cancelButton.addActionListener(new CancelAction());

		add(new Label("User search:"), new GBC(0, 0));
		add(userSearchField, new GBC(0, 1));
		add(searchButton, new GBC(1, 1));
		add(userList, new GBC(0, 2).setSpan(2, 1));
		add(applyButton, new GBC(0, 3));
		add(cancelButton, new GBC(1, 3));

		// TODO Auto-generated constructor stub
	}

	public class SearchAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}

	public class ApplyAction implements ActionListener {
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setModel(ChronosModel model) {
		this.model = (AddParticipantModel) model;
		this.model.setView(this);
	}

}
