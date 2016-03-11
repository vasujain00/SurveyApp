package com.example.surveyapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Survey3 extends Activity {
 Button b1,b2;
 GPSTracker gps;
 EditText et1,et2;
 String lati,longi,tphone,mob,timein,timeout,other,id,name,loc,zip;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_survey3);
		b1 = (Button) findViewById(R.id.but1);
		et1=(EditText)findViewById(R.id.editText8);
		et2=(EditText)findViewById(R.id.editText9);
		Bundle getUnique=getIntent().getExtras();
	      id = getUnique.getString("id");
		  name = getUnique.getString("name");
		  loc = getUnique.getString("loc");
	      zip = getUnique.getString("zip");
	      tphone=getUnique.getString("tphone");
	      mob=getUnique.getString("mob");
	      timein=getUnique.getString("timein");
	      timeout=getUnique.getString("timeout");
		b2=(Button)findViewById(R.id.button2);
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 gps = new GPSTracker(Survey3.this);

					// check if GPS enabled		
			        if(gps.canGetLocation()){
			        	    gps.updateGPSCoordinates();
			        	double latitude = gps.getLatitude();
			        	double longitude = gps.getLongitude();
			        	String lati = String.valueOf(latitude);
			        	String longi = String.valueOf(longitude);
			        	et1.setText(lati);
			        	et2.setText(longi);
			          
	 Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
			       
			        }else{
			        	// can't get location
			        	// GPS or Network is not enabled
			        	// Ask user to enable GPS/network in settings
			        	gps.showSettingsAlert();
			        }
			}
		});
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i;
				lati = et1.getText().toString();
				longi = et2.getText().toString();
				i=new Intent(Survey3.this,Survey4.class);
				 Bundle ab = new Bundle();
					
				    ab.putString("id",id);
				    ab.putString("name",name);
				    ab.putString("loc",loc);
				    ab.putString("zip",zip);
				    ab.putString("tphone",tphone);
				    ab.putString("mob",mob);
				    ab.putString("other",other);
				    ab.putString("timein",timein);
				    ab.putString("timeout",timeout);
				    ab.putString("lati", lati);
				    ab.putString("longi",longi);
				    i.putExtras(ab);
				startActivity(i);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.survey3, menu);
		return true;
	}

}
