package chronos;


import javax.swing.JFrame;

import client.ClientController;
import client.gui.MainFrame;


public class JossiTestetesttteetttesstttt {
	
	
	
	public static void main(String[] args) {
		JFrame frame1 = new JFrame("MainCalView");
		ClientController controller = new ClientController();
		MainFrame panel = new MainFrame(controller);
		frame1.getContentPane().add(panel);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.pack();
		frame1.setVisible(true);
		
	}

}
