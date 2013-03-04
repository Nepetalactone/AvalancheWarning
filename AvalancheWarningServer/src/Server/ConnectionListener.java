package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * Diese Klasse nimmt neue Verbindungen an und sendet diese an den Server
 * @author Kno
 *
 */
public class ConnectionListener extends Thread {

	private Server _server;
	ServerSocket _currentSocket;
	HashMap<String, String> _users;

	public ConnectionListener(Server s, HashMap<String, String> user) {
		_server = s;
		_users = user;
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
			_server.addConnection(new ClientConnection(s, _server, _users));
			run();
		} catch (Exception e) {
			System.out.println("Fehler beim Verbinden eines Clients");
			e.printStackTrace();
		}
	}
}
