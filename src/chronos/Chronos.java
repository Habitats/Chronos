package chronos;

import client.ClientController;
import server.ServerController;

public class Chronos {
	public static void main(String[] args) {
		new Chronos().run(args);

	}

	private void run(String[] args) {
		if (args.length == 1)
			if (args[0].equals("-s"))
				startServer();
			else
				startClient();
		
		
		Singleton.getInstance().enableLog();
		startClient();
//		startServer();

	}

	private void startServer() {
		ServerController serverController = new ServerController();
		serverController.run();
	}

	private void startClient() {
		ClientController clientController = new ClientController();
		clientController.run();
	}
}
