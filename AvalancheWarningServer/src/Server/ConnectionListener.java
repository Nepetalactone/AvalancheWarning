package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionListener extends Thread {

	private Server _server;
	ServerSocket _currentSocket;

	public ConnectionListener(Server s) {
		_server = s;
		try {
			_currentSocket = new ServerSocket(4711);
		} catch (IOException e) {
			System.out.println("ServerSocket konnte nicht erstellt werden");
		}
	}

	public void run() {
		try {
			System.out.println("Listening port 4711");
			Socket s = _currentSocket.accept();
			System.out.println("connected");
			_server.addConnection(new ClientConnection(s, _server));
			run();
		} catch (Exception e) {
			System.out.println("Fehler beim Verbinden eines Clients");
			e.printStackTrace();
		}
	}
}
