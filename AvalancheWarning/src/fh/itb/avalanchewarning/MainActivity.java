package fh.itb.avalanchewarning;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
