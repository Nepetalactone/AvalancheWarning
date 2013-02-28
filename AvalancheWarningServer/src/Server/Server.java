package Server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

public class Server {

	private LinkedList<ClientConnection> _connections;
	private ConnectionListener _listener;
	private String _currentInformations;

	public Server(String info) {
		System.out.println("Server started");
		_connections = new LinkedList<ClientConnection>();
		_listener = new ConnectionListener(this);
		_listener.start();
		_currentInformations = info;
	}

	/**
	 * Durch diese Methode werden Verbindungen hinzugefügt
	 * 
	 * @param s
	 */
	public void addConnection(ClientConnection s) {
		_connections.add(s);
		s.start();
		sendMessage(_currentInformations , s);
	}

	/**
	 * Diese Methode löscht inaktive Verbindungen
	 */
	public void killConnections(ClientConnection cc) {
				_connections.remove(cc);
	}

	/**
	 * Dise Methode sendet eine Nachricht an alle Clients
	 */
	public void sendMessageToAll() {

		for (ClientConnection cc : _connections) {
		sendToClient(_currentInformations, cc);
		}
	}
	
	public void sendMessage(String message, ClientConnection cc)
	{
		sendToClient(message, cc);
	}
	
	private void sendToClient(String message, ClientConnection cc)
	{
		OutputStream raus;
		try {
			raus = cc.getSocket().getOutputStream();
			PrintStream ps = new PrintStream(raus, true);
			ps.println(message);
			System.out.println(message);
		} catch (IOException e) {
			System.out.println("Fehler beim senden der Nachricht");
			System.out.println("töte verbindung!");
			killConnections(cc);
			
		}
	}
	public void setInformationen(String informationen)
	{
		_currentInformations = informationen;
		sendMessageToAll();
	}
	public String getCurrentInformations()
	{
		return _currentInformations;
	}
}
