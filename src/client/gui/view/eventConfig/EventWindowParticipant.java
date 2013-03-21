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

public class EventWindowParticipant extends EventWindow {

	private JButton declineButton;

	public EventWindowParticipant(ChronosModel model, MainFrame frame) {
		super(model, frame, ViewType.PARTICIPANT);
		getAlert().setEnabled(true);
//		getStartDate().setEnabled(true);
//		getStartTime().setEnabled(true);
//		getDuration().setEnabled(true);
		getEventNameField().setEnabled(false);
		getEventDescriptionArea().setEnabled(false);
		applyButton.setText("Update");

		declineButton = new JButton("Decline");
		declineButton.addActionListener(new declineAction());
		add(declineButton, new GBC(0, 9, Align.RIGHT_BOTTOM).setSpan(2, 1));
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
