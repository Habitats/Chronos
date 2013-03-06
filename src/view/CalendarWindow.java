package view;

import model.CalendarModel;
import model.ChronosModel;

public class CalendarWindow implements ChronosWindow {

	private CalendarModel model;

	public CalendarWindow(ChronosModel model) {
		setModel(model);
	}

	@Override
	public void setModel(ChronosModel model) {
		this.model = (CalendarModel) model;
	}
}
