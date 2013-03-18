package client.gui.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import chronos.Person;
import client.gui.GBC;
import client.gui.MainFrame;
import client.gui.view.CalendarWindowHelper.BoxPanel;
import client.gui.view.CalendarWindowHelper.PersonCheckBox;
import client.gui.view.eventConfig.EventWindowAdmin;
import client.model.ParticipantsModel;
import client.model.ChronosModel;

public class ParticipantsWindow extends ChronosWindow implements ActionListener {
	private JTextField userSearchField;
	private JButton searchButton, applyButton, cancelButton;
	private ParticipantsModel model;
	private BoxPanel userPanel;

	public ParticipantsWindow(ChronosModel model, MainFrame frame) {
		super(model, frame);
		setModel(model);
		setVisible(false);

		setLayout(new GridBagLayout());

		userSearchField = new JTextField();
		// userList = new JList<Person>();
		searchButton = new JButton("Search");
		applyButton = new JButton("Add");
		cancelButton = new JButton("Cancel");

		// userList.setMinimumSize(new Dimension(80, 100));
		searchButton.addActionListener(new SearchAction());
		applyButton.addActionListener(new ApplyAction());
		cancelButton.addActionListener(new CancelAction());

		userPanel = new BoxPanel();

		add(userSearchField, new GBC(0, 1));
		add(searchButton, new GBC(1, 1));
		add(userPanel, new GBC(0, 2).setSpan(2, 1).setWeight(1, 1));
		add(applyButton, new GBC(0, 3).setWeight(0.5, 0));
		add(cancelButton, new GBC(1, 3).setWeight(0.5, 0));

		// TODO Auto-generated constructor stub
	}

	private class SearchAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.getUsers();
		}
	}

	private class ApplyAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			getFrame().getEventModel().setParticipants(model.getSelectedUsers());
			getFrame().getEventModel().updateViews();
			setVisible(false);
		}
	}

	private class CancelAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}

	@Override
	public void setVisible(boolean aFlag) {
		super.setVisible(aFlag);
		if (aFlag)
			model.getUsers();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setModel(ChronosModel model) {
		this.model = (ParticipantsModel) model;
		this.model.setView(this);
	}

	public PersonCheckBox addUser(Person person) {
		PersonCheckBox checkBox = new PersonCheckBox(person);
		userPanel.add(checkBox);
		return checkBox;
	}

	public Container getUsersPanel() {
		return userPanel;
	}
}
