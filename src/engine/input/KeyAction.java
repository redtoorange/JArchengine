package engine.input;

import static org.lwjgl.glfw.GLFW.*;

public enum KeyAction {
    PRESSED(GLFW_PRESS), RELEASED(GLFW_RELEASE), REPEAT(GLFW_REPEAT);

    private int glfwCode;

    KeyAction(int action) {
        glfwCode = action;
    }

    public static KeyAction fromGLFW(int code) {
        for (KeyAction action : KeyAction.values()) {
            if (action.glfwCode == code) {
                return action;
            }
        }

        return PRESSED;
    }
}
