package co.kahra.practice.practiceapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import co.kahra.practice.practiceapplication.backstack.BackStackAFragment;
import co.kahra.practice.practiceapplication.backstack.BackStackFragment;
import co.kahra.practice.practiceapplication.mvc.view.ViewFragment;


public class PracticeActivity extends Activity implements OnFragmentAction {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        if (savedInstanceState == null) {
            System.out.println("savedInstanceState == null. Adding NavigationFragment.");
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new NavigationFragment(), "ui")
                    .commit();
            System.out.println("NavigationFragment added?");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.practice, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // INTERFACE IMPLEMENTATION /////

    public void runFragment(PracticeFragment practiceFragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, practiceFragment, "ui")
                .addToBackStack(null)
                .commit();
    }

    public void runNotificationFragment(View view) {
        runFragment(new NotificationFragment());
    }

    public void runSQLFragment(View view) {
        runFragment(new SQLFragment());
    }

    public void runPlaytesterAPIFragment(View view) {
        runFragment(new PlaytesterAPIFragment());
    }

    public void runFileFragment(View view) {
        runFragment(new FileFragment());
    }

    public void runDialogFragment(View view) {
        runFragment(new DialogPracticeFragment());
    }

    public void runListViewFragment(View view) {
        runFragment(new ListViewFragment());
    }

    public void runBackStackFragment(View view) {
        runFragment(new BackStackAFragment());
    }

    public void runMVCFragment(View view) {
        runFragment(new ViewFragment());
    }
}
