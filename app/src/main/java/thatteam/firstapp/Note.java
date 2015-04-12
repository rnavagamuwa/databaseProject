package thatteam.firstapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import thatteam.datalayer.DatabaseHandler;
import thatteam.logic.ToDo;


public class Note extends ActionBarActivity {

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
