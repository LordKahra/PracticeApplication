package co.kahra.practice.practiceapplication.backstack;

import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import co.kahra.practice.practiceapplication.PracticeFragment;
import co.kahra.practice.practiceapplication.R;
import co.kahra.practice.practiceapplication.TestDialogFragment;

public abstract class BackStackFragment extends PracticeFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayout(), container, false);
        this.rootView = rootView;

        setOnClickListeners();

        return rootView;
    }

    private void showFragmentWithBackStack(int containerID, Fragment fragment, String tag) {
        Log.e("showFragWBackStack()", "Entered.");
        getFragmentManager().beginTransaction()

                .replace(containerID, fragment, tag)
                .addToBackStack(null)
                .commit();
    }

    abstract int getLayout();

    abstract void setOnClickListeners();

    protected void setOnClickA() {
        rootView.findViewById(R.id.backstack_button_a).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("showA.onClick()", "Showing A.");
                showFragmentWithBackStack(R.id.container, new BackStackAFragment(), "ui");
            }
        });
    }

    protected void setOnClickB() {
        rootView.findViewById(R.id.backstack_button_b).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("showB.onClick()", "Showing B.");
                showFragmentWithBackStack(R.id.container, new BackStackBFragment(), "ui");
            }
        });
    }

    protected void setOnClickC() {
        rootView.findViewById(R.id.backstack_button_c).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("showC.onClick()", "Showing C.");
                showFragmentWithBackStack(R.id.container, new BackStackCFragment(), "ui");
            }
        });
    }
}
