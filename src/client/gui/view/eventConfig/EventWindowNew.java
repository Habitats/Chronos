package client.gui.view.eventConfig;

import client.gui.MainFrame;
import client.model.ChronosModel;
import client.model.EventConfigModel.ViewType;

public class EventWindowNew extends EventWindowAdmin {

	public EventWindowNew(ChronosModel model, MainFrame frame) {
		super(model, frame, ViewType.NEW);
		applyButton.setText("Create");
		deleteButton.setVisible(false);
	}
}
