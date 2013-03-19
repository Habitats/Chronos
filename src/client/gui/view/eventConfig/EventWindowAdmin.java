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

		addParticipantButton = new EditConfigButton("Add participant", getBigButtonDim());
		deleteParticipantButton = new EditConfigButton("Delete participant", getBigButtonDim());

		bookRoomButton = new EditConfigButton("Book room", getBigButtonDim());
		bookRoomButton.addActionListener(new BookRoomAction());
		deleteButton = new EditConfigButton("Delete", getBottomButtonDim());
		addParticipantButton.addActionListener(new AddParticipantAction());
		deleteParticipantButton.addActionListener(new DeleteParticipantAction());
		deleteButton.addActionListener(new DeleteAction());

		add(bookRoomButton, new GBC(3, 4).setSpan(2, 1).setWeight(0, 0));
		add(addParticipantButton, new GBC(3, 5).setSpan(2, 1).setWeight(0, 0));

		add(deleteButton, new GBC(1, 7));
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
