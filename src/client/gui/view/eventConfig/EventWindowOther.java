package client.gui.view.eventConfig;

import client.gui.MainFrame;
import client.model.ChronosModel;
import client.model.EventConfigModel.ViewType;

public class EventWindowOther extends EventWindow {

	public EventWindowOther(ChronosModel model, MainFrame frame) {
		super(model, frame, ViewType.OTHER);
	}
}
