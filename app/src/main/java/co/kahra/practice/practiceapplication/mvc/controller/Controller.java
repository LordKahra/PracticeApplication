package co.kahra.practice.practiceapplication.mvc.controller;

import android.util.Log;

import co.kahra.practice.practiceapplication.mvc.ModelUpdateHandler;
import co.kahra.practice.practiceapplication.mvc.UserEventHandler;
import co.kahra.practice.practiceapplication.mvc.model.Action;
import co.kahra.practice.practiceapplication.mvc.model.Key;
import co.kahra.practice.practiceapplication.mvc.model.Model;

public class Controller implements UserEventHandler, ModelUpdateHandler {
    Model model;
    ModelUpdateHandler view;

    public Controller (ModelUpdateHandler view) {
        this.view = view;
        this.model = new Model(this);
    }

    public Controller (Model model, ModelUpdateHandler view) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void doAction(Action action) {
        Log.e("Con.doAction()", "Entered.");
        model.doAction(action);
    }

    @Override
    public void updateValue(Key key, Object value) {
        Log.e("Con.updateValue()", "Entered.");
        // Perform validation first.
        if(key.isInstance(value)) {
            Log.e("Con.updateValue()", "Value is of correct type.");
            view.updateValue(key, value);
        } else {
            Log.e("Con.updateValue()", "VALUE NOT CORRECT TYPE");
        }
    }
}
