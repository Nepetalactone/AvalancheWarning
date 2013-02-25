package fh.itb.avalanchewarning;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	File storageDir;
	LinkedList<File> photoList;
	Socket socket;
	String timeStamp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		storageDir = new File(Environment.getExternalStoragePublicDirectory(
         		Environment.DIRECTORY_PICTURES), 
         		"AvalanchePhotos"
				);	
		
		photoList = new LinkedList<File>();
		
		//refreshPhotoList();
		
		Date date = new Date();
		
		GregorianCalendar calendar = new GregorianCalendar(date.getYear(), date.getMonth(), date.getDay());
		int i = calendar.get(Calendar.DAY_OF_WEEK);
		
		TextView date1 = (TextView)findViewById(R.id.txtDate1); 
		TextView date2 = (TextView)findViewById(R.id.txtDate2);
		
		if(i == 2){
			date1.setText(R.string.main_forecast_Monday);
			date2.setText(R.string.main_forecast_Tuesday);
	    } else if (i==3){
	    	date1.setText(R.string.main_forecast_Tuesday);
			date2.setText(R.string.main_forecast_Wednesday);
	    } else if (i==4){
	    	date1.setText(R.string.main_forecast_Wednesday);
			date2.setText(R.string.main_forecast_Thursday);
	    } else if (i==5){
	    	date1.setText(R.string.main_forecast_Thursday);
			date2.setText(R.string.main_forecast_Friday);
	    } else if (i==6){
	    	date1.setText(R.string.main_forecast_Friday);
			date2.setText(R.string.main_forecast_Saturday);
	    } else if (i==7){
	    	date1.setText(R.string.main_forecast_Saturday);
			date2.setText(R.string.main_forecast_Sunday);
	    } else if (i==1){
	    	date1.setText(R.string.main_forecast_Sunday);
			date2.setText(R.string.main_forecast_Monday);
	    }
		
		Button btnPhoto = (Button)findViewById(R.id.btnPhoto);
		
		btnPhoto.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				/*Camera cam = null;
				
				Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
				int cameraCount = Camera.getNumberOfCameras();
				
				for ( int camIdx = 0; camIdx < cameraCount; camIdx++ ) {
			        Camera.getCameraInfo( camIdx, cameraInfo );
			        if ( cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
			            try {
			                cam = Camera.open( camIdx );
			            } catch (RuntimeException e) {
			                Log.e(cameraInfo.toString(), "Camera failed to open: " + e.getLocalizedMessage());
			            }
			        }
				} */
				
				timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
				
				//LocationManager locMan = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
				//locMan.
				
				Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				Uri savedImage = Uri.fromFile(new File("/sdcard/IMG_" + timeStamp + ".png"));
				takePictureIntent.putExtra("output", savedImage);
				startActivityForResult(takePictureIntent, 1);
				
				galleryAddPic();
			}
		});
	}
	
	private void galleryAddPic() {
	    Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
	    File folder = new File("/sdcard/");
	    if (timeStamp != null){
	    	File f = new File("/sdcard/IMG_" + timeStamp + ".png");
		    Uri contentUri = Uri.fromFile(f);
		    mediaScanIntent.setData(contentUri);
		    this.sendBroadcast(mediaScanIntent);
	    }
}
	
	private void sendPhoto(){
		File folder = new File("/sdcard/");
		
		//TODO output stream
		
		if (socket.isConnected()){
			for (File f : folder.listFiles()){
				//TODO alle bilder über output senden
			}
		}
	}
	
	private void refreshPhotoList(){
		photoList = new LinkedList<File>();
		
		for (File file : storageDir.listFiles()){
			photoList.add(file);
		}
	}

}
