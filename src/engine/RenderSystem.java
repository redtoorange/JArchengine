package engine;

import org.joml.Vector4f;

import static org.lwjgl.opengl.GL11.*;

public class RenderSystem implements Destroyable {
    public static RenderSystem S = null;

    public void clearScreen(){
        clearScreen(new Vector4f(0.4f, 0.4f, 0.4f, 1));
    }

    public void clearScreen(Vector4f color){
        glClearColor(color.x, color.y, color.z, color.w);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    @Override
    public void destroy() {

    }
}
