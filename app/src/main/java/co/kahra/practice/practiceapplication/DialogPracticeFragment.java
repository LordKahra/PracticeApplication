package co.kahra.practice.practiceapplication;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class DialogPracticeFragment extends PracticeFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dialog, container, false);
        this.rootView = rootView;
        Button button = (Button) rootView.findViewById(R.id.dialogButtonShow);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TestDialogFragment();
                Bundle fragmentArgs = new Bundle();

                //newFragment.setArguments();
                newFragment.show(getFragmentManager(), "testdialog");
            }
        });
        return rootView;
    }
}
