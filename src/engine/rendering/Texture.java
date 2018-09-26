package engine.rendering;

import engine.Destroyable;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL33.*;

public class Texture implements Destroyable {
    private int textureID;

    public Texture(String fileName) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer texture = stack.mallocInt(1);
            glGenTextures(texture);
            textureID = texture.get();
            glActiveTexture(GL_TEXTURE0);
            glBindTexture(GL_TEXTURE_2D, textureID);

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);
            IntBuffer pChannels = stack.mallocInt(1);
            STBImage.stbi_set_flip_vertically_on_load(true);
            ByteBuffer imageData = STBImage.stbi_load(fileName, pWidth, pHeight, pChannels, 0);

            if (imageData != null) {

                if (pChannels.get() > 3)
                    glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, pWidth.get(), pHeight.get(), 0, GL_RGBA, GL_UNSIGNED_BYTE, imageData);
                else
                    glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, pWidth.get(), pHeight.get(), 0, GL_RGB, GL_UNSIGNED_BYTE, imageData);

                STBImage.stbi_image_free(imageData);
            } else {
                System.err.println("Failed to load texture <" + fileName + ">.");
            }

        }
    }

    public void generateMipMap() {
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, textureID);
        glGenerateMipmap(GL_TEXTURE_2D);
    }

    public void bind() {
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, textureID);
    }

    @Override
    public void destroy() {
        glDeleteTextures(textureID);
    }
}
