package engine.input;

import engine.Destroyable;
import engine.Window;
import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.*;

import java.util.ArrayList;

public class InputSystem implements Destroyable {
    public static InputSystem S;
    private ArrayList<InputListener> inputListeners;
    private Window currentWindow;

    public InputSystem() {
        if (S == null)
            S = this;

        inputListeners = new ArrayList<InputListener>();
    }

    public void update(){
        glfwPollEvents();

        // TODO add frame input code
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

    public void setInputSource(Window window) {
        glfwSetKeyCallback(window.getNativeWindow(), new KeyCallback());
    }

    class KeyCallback extends GLFWKeyCallback {
        @Override
        public void invoke(long window, int key, int scancode, int action, int mods) {
            KeyEvent event = new KeyEvent(Key.fromGLFW(key), KeyAction.fromGLFW(action));
            if (event.key == Key.KEY_A) {
                if (event.action == KeyAction.PRESSED) {
                    System.out.println("A Pressed");
                } else if (event.action == KeyAction.RELEASED) {
                    System.out.println("A Released");
                }
            }
            for (InputListener listener : inputListeners) {
                if (listener.handleKeyEvent(event)) {
                    break;
                }
            }
        }
    }
}
