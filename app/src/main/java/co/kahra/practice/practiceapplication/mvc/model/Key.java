package co.kahra.practice.practiceapplication.mvc.model;

public enum Key {
    ONE(String.class),
    TWO(String.class),
    THREE(String.class);

    final Class objectClass;

    Key(Class objectClass) {
        this.objectClass = objectClass;
    }

    public String toString() {
        return "";
    }

    public boolean isInstance(Object object) {
        return objectClass.isInstance(object);
    }
}
