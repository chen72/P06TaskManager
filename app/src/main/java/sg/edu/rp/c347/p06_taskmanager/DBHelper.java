package sg.edu.rp.c347.p06_taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "task.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TASK = "task";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TASK_NAME = "task_name";
    private static final String COLUMN_TASK_DESC = "description";
    private static final String COLUMN_TASK_TIME = "time";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSongTableSql = "CREATE TABLE " + TABLE_TASK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TASK_NAME + " TEXT,"
                + COLUMN_TASK_DESC + " TEXT,"
                + COLUMN_TASK_TIME + " INTEGER ) ";
        db.execSQL(createSongTableSql);
        Log.i("info", "created tables");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        onCreate(db);
    }


    public long insertTask(String name, String desc,int time) {
        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for the db operation
        ContentValues values = new ContentValues();
        //title
        values.put(COLUMN_TASK_NAME, name);
        //singers
        values.put(COLUMN_TASK_DESC,desc);
        values.put(COLUMN_TASK_TIME,time);
        long result = db.insert("task",null,values);
        db.close();
        return result;
    }

    public ArrayList<Task> getAllTask() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        // "SELECT id, note_content, stars FROM note"
        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_TASK_NAME + ","
                + COLUMN_TASK_DESC + ","
                + COLUMN_TASK_TIME
                + " FROM " + TABLE_TASK;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String desc = cursor.getString(2);
                int time = cursor.getInt(3);
                Task task = new Task(id, name, desc,time);
                tasks.add(task);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return tasks;
    }



    public int deleteTask(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_TASK, condition, args);
        db.close();
        return result;
    }



}
