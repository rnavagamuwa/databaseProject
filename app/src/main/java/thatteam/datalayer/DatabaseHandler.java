package thatteam.datalayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import thatteam.logic.Meeting;
import thatteam.logic.Notes;
import thatteam.logic.ToDo;

/**
 * Created by probook 4540s on 4/1/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "NOAT";

    // TODO table names
    private static final String TABLE_TODO = "todo";
    private static final String TABLE_MEETING = "meeting";
    private static final String TABLE_NOTES = "notes";
    private static final String TABLE_TIME = "time";

    // TODO Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_LONG= "long";
    private static final String KEY_LAT= "lat";
    private static final String KEY_PLACE= "place";
    private static final String KEY_ZONE= "zone";
    private static final String KEY_REMINDER = "reminder";

    //Meeting table Column names
    private static final String KEY_M = "id";
    private static final String KEY_TIMESTART = "start";
    private static final String KEY_TIMEND = "end";
    private static final String KEY_TOPIC="topic";
    private static final String KEY_PEOPLE="people";
    private static final String KEY_TODOID="todoId";

    //Notes Table Column names
    private static final String KEY_N = "id";
       //PLACE IS NOT DEFINED AGAIN SINCE IT'S ALREADY DEFINED.
    private static final String KEY_DESCRIPTION = "desc";

    //TIME Table Column names
    private static final String KEY_T="id";
    private static final String KEY_DATE="date";





    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TODO_TABLE = "CREATE TABLE " + TABLE_TODO + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE + " TEXT,"
                + KEY_PLACE + " TEXT,"+ KEY_LONG + " TEXT,"+ KEY_LAT + " TEXT," +KEY_REMINDER+" TEXT,"+KEY_ZONE+ " TEXT)";

        String CREATE_NOTES_TABLE = "CREATE TABLE " + TABLE_NOTES + "(" +
                              KEY_N +" INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_PLACE + " TEXT," +
                              KEY_DESCRIPTION + " TEXT)";

        String CREATE_MEETING_TABLE= "CREATE TABLE " + TABLE_MEETING + "(" +
                KEY_M +" INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TIMESTART + " TEXT," +
                KEY_TIMEND + " TEXT," +KEY_TOPIC+" TEXT,"+ KEY_TODOID + " TEXT,"+KEY_PEOPLE+ " TEXT, FOREIGN KEY(todoID) REFERENCES todo(id))";

        String CREATE_TIME_TABLE="CREATE TABLE " + TABLE_TIME+ "(" +
                KEY_T +" INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_DATE + " TEXT)";

        db.execSQL(CREATE_TODO_TABLE);
        db.execSQL(CREATE_MEETING_TABLE);
        db.execSQL(CREATE_NOTES_TABLE);
        db.execSQL(CREATE_TIME_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEETING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIME);

        // Create tables again
        onCreate(db);
    }



    // Adding new TODO
    public void addToDo(ToDo toDo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, toDo.getTitle());
        values.put(KEY_PLACE, toDo.getPlace());
        values.put(KEY_ZONE, toDo.getDate());
        values.put(KEY_REMINDER, toDo.getTime());
        values.put(KEY_LONG, toDo.getLon());
        values.put(KEY_LAT, toDo.getLat());


        // Inserting Row
        db.insert(TABLE_TODO, null, values);
        db.close(); // Closing database connection
    }



    // Adding new Meeting
    public void addMeeting(Meeting meeting) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TOPIC, meeting.getTopic());
        values.put(KEY_PLACE, meeting.getPlace());
        values.put(KEY_TIMESTART, meeting.getTimeStart());
        values.put(KEY_PEOPLE, meeting.getPeople());
        values.put(KEY_TIMEND, meeting.getTimeEnd());
        values.put(KEY_TODOID, meeting.getTodoId());


         // Inserting Row
        db.insert(TABLE_MEETING, null, values);
        db.close(); // Closing database connection
    }


    // Adding new Note
    public void addNote(Notes note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PLACE,note.getPlace());
        values.put(KEY_DESCRIPTION,note.getDescription());


        // Inserting Row
        db.insert(TABLE_NOTES, null, values);
        db.close(); // Closing database connection
    }


    // Getting All ToDo
    public List<ToDo> getAllToDo() {
        List<ToDo> toDoList = new ArrayList<ToDo>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TODO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ToDo toDo = new ToDo();
                toDo.setTitle(cursor.getString(0));
                toDo.setTime(cursor.getString(1));
                toDo.setDate(cursor.getString(2));
                toDo.setPlace(cursor.getString(3));
                // Adding toDo to list
                toDoList.add(toDo);
            } while (cursor.moveToNext());
        }

        // return toDo list
        return toDoList;
    }

    public List<String> getAllToDoTitle(){
        List<String> titles = new ArrayList<String>();

        String selectQuery = "SELECT "+KEY_TITLE+" FROM "+TABLE_TODO+" ORDER BY id DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String getTitle = cursor.getString(0);
                // Adding toDo to list
                titles.add(getTitle);
            } while (cursor.moveToNext());
        }

        return titles;
    }


    // Getting All Notes
    public List<Notes> getAllNotes() {
        List<Notes> notesList = new ArrayList<Notes>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NOTES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Notes notes = new Notes();
                notes.setPlace(cursor.getString(1));
                notes.setDescription(cursor.getString(2));

                // Adding notes
                notesList.add(notes);
            } while (cursor.moveToNext());
        }

        // return notesLIst
        return notesList;
    }

    // Getting All Notes
    public List<Meeting> getAllNotes(int todoId) {
        List<Meeting> meetingList = new ArrayList<Meeting>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MEETING+","+TABLE_TODO +"WHERE"+KEY_TODOID+" ="+todoId;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Meeting meeting = new Meeting();
                meeting.setTimeStart(cursor.getString(1));
                meeting.setTimeEnd(cursor.getString(2));
                meeting.setTopic(cursor.getString(3));
                meeting.setTodoId(Integer.parseInt(cursor.getString(4)));
                meeting.setPeople(cursor.getString(5));

                // Adding notes
                meetingList.add(meeting);
            } while (cursor.moveToNext());
        }

        // return notesLIst
        return meetingList;
    }




}
