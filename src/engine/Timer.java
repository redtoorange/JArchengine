package engine;

import org.lwjgl.glfw.GLFW;

public class Timer {
    double lastFrame = 0;

    public double getTime() {
        return GLFW.glfwGetTime();
    }

    public float getDelta() {
        double time = getTime();
        float delta = (float)(time - lastFrame);
        lastFrame = time;

        return delta;
    }
}
