package co.kahra.practice.practiceapplication.mvc.model;

import android.util.Log;

import java.util.Calendar;

import co.kahra.practice.practiceapplication.mvc.ModelUpdateHandler;
import co.kahra.practice.practiceapplication.mvc.UserEventHandler;

public class Model implements UserEventHandler {
    // CONTROLLER
    ModelUpdateHandler controller;

    // VALUES
    String valueOne = "Loading...";
    String valueTwo = "Loading...";
    String valueThree = "Loading...";

    // CONSTRUCTOR
    public Model(ModelUpdateHandler controller) {
        this.controller = controller;
        setValueOne("Loaded.");
        setValueTwo("Loaded.");
        setValueThree("Loaded.");
    }

    public String getValueOne() {
        return valueOne;
    }

    public String getValueTwo() {
        return valueTwo;
    }

    public String getValueThree() {
        return valueThree;
    }

    public void setValueOne(String valueOne) {
        this.valueOne = valueOne;
        controller.updateValue(Key.ONE, getValueOne());
    }

    public void setValueTwo(String valueTwo) {
        this.valueTwo = valueTwo;
        controller.updateValue(Key.TWO, getValueTwo());
    }

    public void setValueThree(String valueThree) {
        this.valueThree = valueThree;
        controller.updateValue(Key.THREE, getValueThree());
    }

    // Controller validates values.
    @Override
    public void doAction(Action action) {
        Log.e("Model.doAction()", "Entered.");
        switch (action) {
            case ONE:
                setValueOne("One: " + Calendar.getInstance().getTimeInMillis());
                break;
            case TWO:
                setValueTwo("Two: " + Calendar.getInstance().getTimeInMillis());
                break;
            case THREE:
                setValueThree("Three: " + Calendar.getInstance().getTimeInMillis());
                break;
        }
    }
}
