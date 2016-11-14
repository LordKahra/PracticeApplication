package co.kahra.practice.practiceapplication.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Calendar;



public class SQLiteDatabaseHelper extends SQLiteOpenHelper {
    // DATABASE
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TestSQL.db";

    // TABLE
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TITLE = "title";

    // QUERIES
    private static final String DELETE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_USER_ID       + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL"    + ", " +
            COLUMN_NAME          + " TEXT"                                          + ", " +
            COLUMN_TITLE         + " TEXT"                                          +
            ")";

    public SQLiteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO: THIS METHOD DISCARDS PRIOR DATA
        db.execSQL(DELETE_QUERY);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /////

    public long insertUser (String name, String title) {
        // Get the database in write mode.
        SQLiteDatabase db = getWritableDatabase();

        // Create value map.
        ContentValues values = new ContentValues();
        //values.put(SQLiteDatabaseHelper.COLUMN_USER_ID, 1);
        values.put(SQLiteDatabaseHelper.COLUMN_NAME, name);
        values.put(SQLiteDatabaseHelper.COLUMN_TITLE, "" + Calendar.getInstance().getTimeInMillis());

        // Insert the new row and return the primary key value.
        return db.insert(SQLiteDatabaseHelper.TABLE_NAME, null, values);
    }

    public Cursor readUsers() {
        SQLiteDatabase db = getReadableDatabase();

        // Define columns you will use.
        String[] columns = {
                SQLiteDatabaseHelper.COLUMN_USER_ID,
                SQLiteDatabaseHelper.COLUMN_NAME,
                SQLiteDatabaseHelper.COLUMN_TITLE
        };

        // Define filter.
        //String selection = ""; using null.

        // Define selection arguments.
        // String[] selectionArgs = { "something", "something else" };
        String[] selectionArgs = { };

        // Define row groupings.
        // String groupBy = "?"; // Using null.

        // Define "having" for row groupings.
        // Using null.

        // Specify sort order.
        String sortOrder = SQLiteDatabaseHelper.COLUMN_NAME + " DESC";

        // Define limit.
        // int limit = 10; // Using null.

        Cursor c = db.query(
                SQLiteDatabaseHelper.TABLE_NAME,
                columns,
                null,
                selectionArgs,
                null,
                null,
                sortOrder,
                null
        );

        return c;
    }
}
