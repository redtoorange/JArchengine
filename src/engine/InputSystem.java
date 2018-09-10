package engine;

import java.util.ArrayList;

public class InputSystem implements Destroyable {
    public static InputSystem S;
    private ArrayList<InputListener> inputListeners;

    public InputSystem() {
        if (S == null)
            S = this;

        inputListeners = new ArrayList<InputListener>();
    }

    public void removeListener(InputListener listener) {
        inputListeners.remove(listener);
    }

    public void addListener(InputListener listener) {
        inputListeners.add(listener);
    }

    @Override
    public void destroy() {
        // Cleanup
    }
}
