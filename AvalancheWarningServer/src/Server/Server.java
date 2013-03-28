package Server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Properties;

/**
 * Diese Klasse Dient als Server. Hier werden Verbindungen gespeichert,
 * Nachrichten versendet und Verbindungen angenommen
 * 
 * @author Kno
 *
 */
public class Server {

	private LinkedList<ClientConnection> _connections;
	private ConnectionListener _listener;
	private String _currentInformations;
	private HashMap<String, String> _users;

	public Server(String info) {
		init();
		System.out.println("Server started");
		_connections = new LinkedList<ClientConnection>();
		_listener = new ConnectionListener(this, _users);
		_listener.start();
		_currentInformations = info;
	}
	/**
	 * Diese Methode initialisiert die Benutzernamen und Kennwörter
	 * der einzelnen Benutzer
	 */
	private void init() {
		try {
			Properties p = new Properties();
			p.load(new FileInputStream("users.ini"));
			String[]users = p.getProperty("user").split(" ");
			String[]passwords = p.getProperty("password").split(" ");
			_users = new HashMap<String, String>();
			for(int i = 0; i< users.length;i++)
			{
				_users.put(users[i], passwords[i]);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	/**
	 * Durch diese Methode werden Verbindungen hinzugefügt
	 * 
	 * @param s
	 */
	public void addConnection(ClientConnection s) {
		_connections.add(s);
		//sendMessage(_currentInformations, s);
		s.start();
		
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
	
	/**
	 * Diese Methode ruft nur die Methode sendToClient auf
	 * @param message
	 * @param cc
	 */
	public void sendMessage(String message, ClientConnection cc)
	{
		sendToClient(message, cc);
	}
	
	/**
	 * Dise Methode sendet eine Nachricht an einen Client.
	 * @param message
	 * @param cc
	 */
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

	/**
	 * Mit dieser Methode können die aktuellen Wetterinformationen gespeichert werden.
	 * @param informationen
	 */
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
