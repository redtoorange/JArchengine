import engine.*;
import engine.rendering.Mesh;
import engine.rendering.MeshInstance;
import engine.rendering.ShaderProgram;
import engine.rendering.Texture;
import game.GameScreen;
import org.lwjgl.opengl.GL;

import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.glfw.GLFW.*;

public class Main {


    public static void main(String[] args) {
        glfwInit();

        Engine engine = new Engine();
        Window window = WindowSystem.S.createWindow("Debug engine.Window", 800, 600);
        window.setInputSource();
        GL.createCapabilities();
        glfwSwapInterval(1);

        engine.setCurrentScreen(new GameScreen());
        engine.run();


        engine.destroy();
        glfwTerminate();
    }



}
