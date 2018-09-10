import org.joml.Vector2i;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;

public class Window implements Destroyable {
    private long nativeWindow;

    public Window(String windowTitle, int windowWidth, int windowHeight) {
        // Set up the context
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        nativeWindow = glfwCreateWindow(windowWidth, windowHeight, windowTitle, 0, 0);
        makeCurrent();
    }

    /**
     * Hide the Window.
     */
    public void close() {
        glfwHideWindow(nativeWindow);
    }

    /**
     * Destroy the window and it's attached context.
     */
    @Override
    public void destroy() {
        glfwDestroyWindow(nativeWindow);
    }

    /**
     * Make the window's context the current openGL context
     */
    public void makeCurrent() {
        glfwMakeContextCurrent(nativeWindow);
    }

    /**
     * Should the window close?
     *
     * @return True of the window should close
     */
    public boolean shouldClose() {
        return glfwWindowShouldClose(nativeWindow);
    }

    /**
     * Swap the openGL buffers.
     */
    public void swapBuffers() {
        glfwSwapBuffers(nativeWindow);
    }

    /**
     * Get the width and height of the window.
     *
     * @return Vector2 with x = width and y = height
     */
    public Vector2i getWindowSize() {
        Vector2i size = new Vector2i(0, 0);
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            glfwGetWindowSize(nativeWindow, pWidth, pHeight);
            size.set(pWidth.get(), pHeight.get());
        }
        return size;
    }
}
