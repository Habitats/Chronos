package client.gui.view.eventConfig;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JList;

import chronos.Person;
import client.gui.GBC;
import client.gui.MainFrame;
import client.gui.view.EditConfigButton;
import client.model.ChronosModel;
import client.model.EventConfigModel.ViewType;

public class EventWindowAdmin extends EventWindow {

	private JList<Person> participantList;
	private EventWindow view;

	public EventWindowAdmin(ChronosModel model, MainFrame frame) {
		this(model, frame, ViewType.ADMIN);
	}

	public EventWindowAdmin(ChronosModel model, MainFrame frame, ViewType viewType) {
		super(model, frame, viewType);
		view = this;

		addParticipantButton = new EditConfigButton("Add participant");
		deleteParticipantButton = new EditConfigButton("Delete participant");

		bookRoomButton = new EditConfigButton("Book room");
		bookRoomButton.addActionListener(new BookRoomAction());
		deleteButton = new EditConfigButton("Delete");
		addParticipantButton.addActionListener(new AddParticipantAction());
		deleteParticipantButton.addActionListener(new DeleteParticipantAction());
		deleteButton.addActionListener(new DeleteAction());

		add(addParticipantButton, new GBC(4, 6).setSpan(2, 1).setWeight(0, 0));
		add(bookRoomButton, new GBC(4, 7).setSpan(2, 1).setWeight(0, 0));

		add(deleteButton, new GBC(0, 9).setSpan(2, 1));
	}

	private class BookRoomAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			getFrame().getRoomBookingWindow().setVisible(true);
		}
	}

	private class AddParticipantAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			getFrame().getAddParticipantWindow().setVisible(true);
		}
	}

	private class DeleteParticipantAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int selectedParticipant = participantList.getSelectedIndex();
			if (selectedParticipant != -1) {
				participantList.remove(selectedParticipant);
			}
		}
	}

	private class EditAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		}
	}

	private class DeleteAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			getModel().removeEvent(view);
			setVisible(false);
		}
	}
}
