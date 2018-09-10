import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.glfw.GLFW.*;

public class Main {
    public static float vertices[] = {
            0.5f, 0.5f, 0.0f,  // top right
            0.5f, -0.5f, 0.0f,  // bottom right
            -0.5f, -0.5f, 0.0f,  // bottom left
            -0.5f, 0.5f, 0.0f   // top left
    };

    public static int indices[] = {  // note that we start from 0!
            0, 1, 3,   // first triangle
            1, 2, 3    // second triangle
    };

    public static float uvs[] = {  // note that we start from 0!
            1.0f, 1.0f,
            1.0f, 0.0f,
            0.0f, 0.0f,
            0.0f, 1.0f
    };

    public static void main(String[] args) {
        glfwInit();

        Engine engine = new Engine();
        Window window = WindowSystem.S.createWindow("Debug Window", 800, 600);
        GL.createCapabilities();

        Shader shader = new Shader("basic.vert", "basic.frag");
        Texture texture = TextureManager.S.loadTexture("assets/textures/" + "star.png");
        Texture texture2 = TextureManager.S.loadTexture("assets/textures/" + "star.png");
        RawMesh mesh = new RawMesh(indices, vertices, uvs);

        while (!window.shouldClose()) {
            glfwPollEvents();

            glClearColor(0.4f, 0.4f, 0.4f, 1);
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            shader.bindProgram();
            texture.bind();
            mesh.render();

            window.swapBuffers();
        }

        mesh.destroy();
        engine.destroy();
        glfwTerminate();
    }
}
