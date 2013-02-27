package fh.itb.avalanchewarning;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectionWorker implements Runnable{
	
	Socket socket;
	BufferedReader reader;
	MainActivity activity;
	PrintWriter out;
	
	
	public ConnectionWorker(MainActivity activity){
		try {
			this.activity = activity;
			this.socket = new Socket("192.168.0.1", 9001);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		String message;
		while (true){
			try {
				message = reader.readLine();
				
				String[] splitMessage = message.split("**");
				//0 = wetter morgen | 1= wetter übermorgen
				String[] wetter = splitMessage[0].split("||");
				//0 = region1 leerzeichen wetterkondition | 1= region2 usw...
				String[] locations = splitMessage[1].split("||");
				
				activity.initRegionList();
				
				for (String s :locations){
					activity.addRegion(new Region(s.split("__")[0], s.split("__")[1]));
				}
				
				activity.refreshRegionSpinner();
			} catch (IOException e) { 
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void sendGPS (String GPSData){
		out.write("GPS");
		out.flush();
		out.write(GPSData);
		out.flush();
	}
	
	public void sendInfo(){
		out.write("Info");
		out.flush();
	}
	
	public void sendPhoto(File photo){
		out.write("Bild");
		out.flush();
		
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(photo);  
	        oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		  
          
	}
}
