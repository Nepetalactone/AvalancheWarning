package fh.itb.avalanchewarning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import android.os.StrictMode;

public class ListenThread extends Thread {

	private TempData data;
	private Socket socket;
	BufferedReader reader;

	public ListenThread(TempData a) {
		data = a;
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
			this.socket = new Socket("10.0.2.2", 4711);
			reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		} catch (UnknownHostException e) {
			System.out.println("UNKNOWN HOST");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		String message = "";
		while (true) {
			try {
				message = reader.readLine();
				if (!message.equals("")) {
					data.setData(message);
					message = "";
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
