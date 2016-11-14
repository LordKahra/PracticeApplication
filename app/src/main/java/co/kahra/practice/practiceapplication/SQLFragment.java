package co.kahra.practice.practiceapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import co.kahra.practice.practiceapplication.sql.SQLiteDatabaseHelper;

public class SQLFragment extends PracticeFragment {
    SQLiteDatabaseHelper sqlDBHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_sql, container, false);
        this.rootView = rootView;

        // Find buttons.
        Button insertButton = (Button) rootView.findViewById(R.id.sqlButtonInsert);
        Button refreshButton = (Button) rootView.findViewById(R.id.sqlButtonRefresh);

        // Set OnClickListeners.
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertTestUsers();
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshTable();
            }
        });

        // Load the database.
        sqlDBHelper = new SQLiteDatabaseHelper(getActivity().getApplicationContext());

        // Refresh the view container.
        refreshTable();

        return rootView;
    }

    public void insertTestUsers () {
        sqlDBHelper.insertUser("Kahra", "Lord?");
        sqlDBHelper.insertUser("blargh", "blah");
        sqlDBHelper.insertUser("wut", "woot");
    }

    public void refreshTable () {
        // Access the view container.
        LinearLayout container = (LinearLayout) rootView.findViewById(R.id.sqlTableContainer);

        // Empty the view container.
        container.removeAllViews();

        // Create a container for the entries.
        ArrayList<String> entries = new ArrayList<String>();

        // Read the users from the database.
        Cursor cursor = sqlDBHelper.readUsers();

        // Iterate through the results.
        while (cursor.moveToNext()) {
            String entry = "E: ";
            entry += "ID: " + cursor.getLong(cursor.getColumnIndex(SQLiteDatabaseHelper.COLUMN_USER_ID));
            entry += "N: " + cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.COLUMN_NAME));
            entry += "T: " + cursor.getString(cursor.getColumnIndex(SQLiteDatabaseHelper.COLUMN_TITLE));
            entries.add(entry);
        }

        // Close the cursor.
        cursor.close();

        // Add all the entries to the container.
        for (String string : entries) {
            TextView textView = new TextView(getActivity().getApplicationContext());
            textView.setText(string);

            container.addView(textView);
        }
    }
}