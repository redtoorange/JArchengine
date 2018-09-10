package engine;

import java.util.ArrayList;

public class WindowSystem implements Destroyable {
    public static WindowSystem S = null;

    private ArrayList<Window> windows = new ArrayList<Window>();

    public WindowSystem() {
        if (S == null) {
            S = this;
        } else {
            throw new RuntimeException("engine.WindowSystem has already been started.");
        }
    }

    /**
     * Create a new engine.Window that will be managed by the engine.WindowSystem.
     *
     * @param title        title of the window
     * @param windowWidth  width of the window
     * @param windowHeight height of the window
     * @return Managed engine.Window.
     */
    public Window createWindow(String title, int windowWidth, int windowHeight) {
        Window window = new Window(title, windowWidth, windowHeight);
        windows.add(window);
        return window;
    }

    @Override
    public void destroy() {
        for (Window window : windows) {
            window.close();
            window.destroy();
        }
    }
}
