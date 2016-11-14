package co.kahra.practice.practiceapplication.mvc;

import co.kahra.practice.practiceapplication.mvc.model.Key;

public interface ModelUpdateHandler {
    public void updateValue(Key key, Object value);
    // Overloads of updateValue allow for more potential choices.
}
