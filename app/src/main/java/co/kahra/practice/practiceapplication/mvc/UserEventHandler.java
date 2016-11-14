package co.kahra.practice.practiceapplication.mvc;

import co.kahra.practice.practiceapplication.mvc.model.Action;

public interface UserEventHandler {
    public void doAction(Action action);
    // Overloads of doAction allow for more potential choices.
}
