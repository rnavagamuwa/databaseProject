package thatteam.firstapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import thatteam.datalayer.DatabaseHandler;
import thatteam.logic.ToDo;


public class Note extends ActionBarActivity {
    private LocationManager locManager;
    private LocationListener locListener;
    private Location mobileLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        //create a database connection
        final DatabaseHandler db = new DatabaseHandler(this);

        Button okButton = (Button) findViewById(R.id.buttontoDo);
        okButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ToDo todo = new ToDo();
                EditText txtBoxToDo =(EditText) findViewById(R.id.txtBoxToDo);
                EditText txtBoxWhen =(EditText) findViewById(R.id.whenTxtBox);
                EditText txtBoxReminder =(EditText) findViewById(R.id.reminderTxtBox);
                EditText txtBoxPlace =(EditText) findViewById(R.id.placeTxtBox);

                getCurrentLocation();
                if (mobileLocation != null) {
                    locManager.removeUpdates(locListener); // This needs to stop getting the location data and save the battery power.
                    todo.setLat(mobileLocation.getLatitude());
                    todo.setLon(mobileLocation.getLongitude());
                }
                //set the values in the object obtained
                todo.setTitle(txtBoxToDo.getText().toString());
                todo.setDate(txtBoxWhen.getText().toString());
                todo.setTime(txtBoxReminder.getText().toString());
                todo.setPlace(txtBoxPlace.getText().toString());

                //return error message
                if(todo.getTitle()==" "){


                }

                //save the values in the database
                 db.addToDo(todo);
                 finish();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }
    private void getCurrentLocation() {
        locManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locListener = new LocationListener() {
            @Override
            public void onStatusChanged(String provider, int status,
                                        Bundle extras) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProviderEnabled(String provider) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProviderDisabled(String provider) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onLocationChanged(Location location) {
                // TODO Auto-generated method stub
                mobileLocation = location;
            }
        };
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
