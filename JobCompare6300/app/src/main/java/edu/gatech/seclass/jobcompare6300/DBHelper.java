package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper instance;
    private static final String DATABASE_NAME = "JobOffer.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "JobOffers";
    private static final String COLUMN_ID = "Id";
    private static final String COLUMN_TITLE = "Title";
    private static final String COLUMN_COMPANY = "Company";
    private static final String COLUMN_LOCATION = "Location";
    private static final String COLUMN_COSTOFLIVING = "Cost_Of_Living";
    private static final String COLUMN_SALARY = "Yearly_Salary";
    private static final String COLUMN_BONUS = "Yearly_Bonus";
    private static final String COLUMN_GYM = "Gym_Membership";
    private static final String COLUMN_LEAVETIME = "Leave_Time";
    private static final String COLUMN_401K = "\"401K_Match\"";
    private static final String COLUMN_PETINSURANCE = "Pet_Insurance";
    private static final String COLUMN_ISCURRENTJOB = "Is_Current_Job";

    public static synchronized DBHelper getInstance(Context context){
        if (instance == null){
            instance = new DBHelper(context.getApplicationContext());
        }
        return instance;
    }

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        var query = "CREATE TABLE " + TABLE_NAME +
                    " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_COMPANY + " TEXT, " +
                    COLUMN_LOCATION + " TEXT, " +
                    COLUMN_COSTOFLIVING + " INTEGER, " +
                    COLUMN_SALARY + " REAL, " +
                    COLUMN_BONUS + " REAL, " +
                    COLUMN_GYM + " REAL, " +
                    COLUMN_LEAVETIME + " INTEGER, " +
                    COLUMN_401K + " INTEGER, " +
                    COLUMN_PETINSURANCE + " REAL, " +
                    COLUMN_ISCURRENTJOB + " BOOL);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void AddJob(Job job) {
        var db = this.getWritableDatabase();
        var cv = new ContentValues();
        String[] jobDetails = job.GetJobDetails();
        cv.put(COLUMN_TITLE, jobDetails[1]);
        cv.put(COLUMN_COMPANY, jobDetails[2]);
        cv.put(COLUMN_LOCATION, jobDetails[3]);
        cv.put(COLUMN_COSTOFLIVING, jobDetails[4]);
        cv.put(COLUMN_SALARY, jobDetails[5]);
        cv.put(COLUMN_BONUS, jobDetails[6]);
        cv.put(COLUMN_GYM, jobDetails[7]);
        cv.put(COLUMN_LEAVETIME, jobDetails[8]);
        cv.put(COLUMN_401K, jobDetails[9]);
        cv.put(COLUMN_PETINSURANCE, jobDetails[10]);
        cv.put(COLUMN_ISCURRENTJOB, jobDetails[11]);
        long insert = db.insert(TABLE_NAME, null, cv);

        if (insert > 0){
            System.out.println("Success");
        }
        db.close();
    }

    public void UpdateJob(Job job){
        var db = this.getWritableDatabase();
        var cv = new ContentValues();
        String[] jobDetails = job.GetJobDetails();
        cv.put(COLUMN_TITLE, jobDetails[1]);
        cv.put(COLUMN_COMPANY, jobDetails[2]);
        cv.put(COLUMN_LOCATION, jobDetails[3]);
        cv.put(COLUMN_COSTOFLIVING, jobDetails[4]);
        cv.put(COLUMN_SALARY, jobDetails[5]);
        cv.put(COLUMN_BONUS, jobDetails[6]);
        cv.put(COLUMN_GYM, jobDetails[7]);
        cv.put(COLUMN_LEAVETIME, jobDetails[8]);
        cv.put(COLUMN_401K, jobDetails[9]);
        cv.put(COLUMN_PETINSURANCE, jobDetails[10]);
        cv.put(COLUMN_ISCURRENTJOB, jobDetails[11]);

        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = { jobDetails[0] };
        db.update(TABLE_NAME, cv, selection, selectionArgs);
        db.close();
    }

    public String[] ReadCurrentJob(){
        var db = this.getReadableDatabase();
        String selection = COLUMN_ISCURRENTJOB + " = ?";
        String[] selectionArgs = { "true" };
        Cursor cursor = db.query(
                TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        String[] result = new String[0];
        while(cursor.moveToNext()) {
            Integer columnCount = cursor.getColumnCount();
            String[] data = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                String value = cursor.getString(i);
                data[i] = value;
            }
            result = data;
        }
        if (result.length == 0){
            return null;
        }
        return result;
    }

    public List<Job> read() {
        var db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        List<Job> results = new ArrayList<>();

        while(cursor.moveToNext()) {
            Integer columnCount = cursor.getColumnCount();
            String[] data = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                String value = cursor.getString(i);
                data[i] = value;
            }
            results.add(new Job(data));
        }
        if (results.size() == 0){
            return null;
        }

        return results;
    }
}
