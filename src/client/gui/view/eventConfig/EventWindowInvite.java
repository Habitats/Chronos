package client.gui.view.eventConfig;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import chronos.Singleton;
import chronos.Person.Status;
import client.gui.GBC;
import client.gui.GBC.Align;
import client.gui.MainFrame;
import client.model.ChronosModel;
import client.model.EventConfigModel.ViewType;
import events.CalEvent.CalEventType;

public class EventWindowInvite extends EventWindow {
	private JButton acceptButton, declineButton;

	public EventWindowInvite(ChronosModel model, MainFrame frame) {
		super(model, frame, ViewType.INVITE);
		acceptButton = new JButton("Accept");
		declineButton = new JButton("Decline");

		acceptButton.addActionListener(new acceptAction());
		declineButton.addActionListener(new declineAction());

		// titleField = new JTextField();
		// descriptionField = new JTextField();
		applyButton.setVisible(false);
		eventNameField.setEnabled(false);
		eventDescriptionArea.setEnabled(false);
		startDate.setEnabled(false);
		duration.setEnabled(false);
		startTime.setEnabled(false);

		add(declineButton, new GBC(0, 9, Align.RIGHT_BOTTOM).setSpan(2, 1));
		add(acceptButton, new GBC(2, 9).setSpan(2, 1));
	}

	private class acceptAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.getCalEvent().getParticipants().put(Singleton.getInstance().getSelf().getUsername(), Singleton.getInstance().getSelf().setStatus(Status.ACCEPTED).setAlert(getAlert().isSelected()));
			model.fireNetworkEvent(model.getCalEvent().setState(CalEventType.UPDATE));
			setVisible(false);
		}
	}

	private class declineAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.getCalEvent().getParticipants().put(Singleton.getInstance().getSelf().getUsername(), Singleton.getInstance().getSelf().setStatus(Status.DECLINED));
			model.fireNetworkEvent(model.getCalEvent().setState(CalEventType.UPDATE));
			setVisible(false);
		}
	}
}
