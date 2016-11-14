package co.kahra.practice.practiceapplication.mvc.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import co.kahra.practice.practiceapplication.PracticeFragment;
import co.kahra.practice.practiceapplication.R;
import co.kahra.practice.practiceapplication.mvc.ModelUpdateHandler;
import co.kahra.practice.practiceapplication.mvc.controller.Controller;
import co.kahra.practice.practiceapplication.mvc.model.Action;
import co.kahra.practice.practiceapplication.mvc.model.Key;

public class ViewFragment extends PracticeFragment implements ModelUpdateHandler {
    // CONTROLLER
    Controller controller;

    // OUTPUT
    TextView oneView;
    TextView twoView;
    TextView threeView;

    // INPUT
    Button buttonOne;
    Button buttonTwo;
    Button buttonThree;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_mvc, container, false);
        this.rootView = rootView;

        // Load buttons.
        buttonOne = (Button) rootView.findViewById(R.id.mvc_button_a);
        buttonTwo = (Button) rootView.findViewById(R.id.mvc_button_b);
        buttonThree = (Button) rootView.findViewById(R.id.mvc_button_c);

        // Load TextViews.
        oneView = (TextView) rootView.findViewById(R.id.mvc_textview_a);
        twoView = (TextView) rootView.findViewById(R.id.mvc_textview_b);
        threeView = (TextView) rootView.findViewById(R.id.mvc_textview_c);

        // Create the controller.
        controller = new Controller(this);

        // Load onClickListener.
        Button.OnClickListener onClickListener = createOnClickListener();

        // Set button actions.
        buttonOne.setOnClickListener(onClickListener);
        buttonTwo.setOnClickListener(onClickListener);
        buttonThree.setOnClickListener(onClickListener);

        return rootView;
    }

    private Button.OnClickListener createOnClickListener() {
        return new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("View.OnClickListener()", "Entered: " + v.getId());
                switch (v.getId()) {
                    case R.id.mvc_button_a:
                        controller.doAction(Action.ONE);
                        break;
                    case R.id.mvc_button_b:
                        controller.doAction(Action.TWO);
                        break;
                    case R.id.mvc_button_c:
                        controller.doAction(Action.THREE);
                        break;
                }
            }
        };
    }

    // Controller validates values.
    @Override
    public void updateValue(Key key, Object value) {
        Log.e("View.updateValue()", "Entered: " + value.toString());
        switch(key) {
            case ONE:
                oneView.setText((String) value);
                break;
            case TWO:
                twoView.setText((String) value);
                break;
            case THREE:
                threeView.setText((String) value);
                break;
        }
    }
}
