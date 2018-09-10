package engine.input;

public class KeyEvent {
    public Key key;
    public KeyAction action;

    public KeyEvent(Key key, KeyAction action) {
        this.key = key;
        this.action = action;
    }
}
