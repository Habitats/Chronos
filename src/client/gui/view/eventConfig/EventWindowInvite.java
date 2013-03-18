package client.gui.view.eventConfig;

import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JTextField;

import chronos.Singleton;
import chronos.Person.Status;
import client.gui.GBC;
import client.gui.GBC.Align;
import client.gui.MainFrame;
import client.gui.view.ChronosWindow;
import client.model.ChronosModel;
import client.model.EventConfigModel;
import client.model.EventConfigModel.ViewType;
import client.model.InvitationModel;
import events.CalEvent.CalEventType;

public class EventWindowInvite extends EventWindow {
	private JButton acceptButton, declineButton;
	private Date eventDate;

	public EventWindowInvite(ChronosModel model, MainFrame frame) {
		super(model, frame, ViewType.INVITE);
		acceptButton = new JButton("Accept");
		declineButton = new JButton("Decline");

		acceptButton.addActionListener(new acceptAction());
		declineButton.addActionListener(new declineAction());

		// titleField = new JTextField();
		// descriptionField = new JTextField();
		applyButton.setVisible(false);
		eventNameField.setEditable(false);
		eventDescriptionArea.setEditable(false);
		dateField.setEditable(false);
		duration.setEditable(false);
		startTime.setEditable(false);

		add(acceptButton, new GBC(0, 7).setSpan(2, 1));
		add(declineButton, new GBC(2, 7, Align.RIGHT_BOTTOM));
	}

	private class acceptAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.getCalEvent().getParticipants().put(Singleton.getInstance().getSelf().getUsername(), Singleton.getInstance().getSelf().setStatus(Status.REJECTED));
			model.fireNetworkEvent(model.getCalEvent().setState(CalEventType.UPDATE));
		}
	}

	private class declineAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.getCalEvent().getParticipants().put(Singleton.getInstance().getSelf().getUsername(), Singleton.getInstance().getSelf().setStatus(Status.ACCEPTED));
			model.fireNetworkEvent(model.getCalEvent().setState(CalEventType.UPDATE));
		}
	}
}
