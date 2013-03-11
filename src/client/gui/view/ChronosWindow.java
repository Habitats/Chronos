package client.gui.view;

import javax.swing.JPanel;

import client.ClientController;
import client.model.ChronosModel;


abstract public class ChronosWindow extends JPanel {

	private ChronosModel model;

	abstract public void setModel(ChronosModel model);
}