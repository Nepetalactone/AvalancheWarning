package fh.itb.avalanchewarning;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import android.os.StrictMode;

public class ListenThread {

	private TempData data;
	private Socket socket;

	public ListenThread(TempData a) {
		data = a;
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
			.permitAll().build();
			StrictMode.setThreadPolicy(policy);
			this.socket = new Socket("10.0.2.2", 4711);

		} catch (UnknownHostException e) {
			System.out.println("UNKNOWN HOST");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getForecast(){
		String forecast = "";
		PrintWriter out;
		
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			
			out.write("Info");
			out.flush();
			//out.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			
			try{
				forecast = reader.readLine();
				if (!forecast.equals("")){
					data.setData(forecast);
					forecast = "";
				}
			} catch (IOException e){
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public boolean sendPhoto(File photo){
		PrintWriter out;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			out.write("Bild");
			out.flush();
			out.close();

			File myFile = new File(photo.getAbsolutePath());
			byte[] mybytearray = new byte[(int) myFile.length()];
			FileInputStream fis = new FileInputStream(myFile);
			BufferedInputStream bis = new BufferedInputStream(fis);
			bis.read(mybytearray, 0, mybytearray.length);
			OutputStream os = socket.getOutputStream();
			System.out.println("Sending...");
			os.write(mybytearray, 0, mybytearray.length);
			os.flush();
			os.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void sendGPS(String GPSData){
		PrintWriter out;
		
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			out.write("GPS");
			out.flush();
			out.write(GPSData);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
