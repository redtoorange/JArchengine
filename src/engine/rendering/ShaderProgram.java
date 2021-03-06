package engine.rendering;

import engine.Destroyable;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.system.MemoryStack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL33.*;

public class ShaderProgram implements Destroyable {
    private final static String shaderPath = "assets/shaders/";
    private int programID;
    private int fragmentID;
    private int vertexID;

    public ShaderProgram(String vertexPath, String fragmentPath) {
        programID = glCreateProgram();

        // Vertex engine.rendering.ShaderProgram
        vertexID = glCreateShader(GL_VERTEX_SHADER);
        String vertexSource = readShaderSource(vertexPath);
        // Read the source file into the source string
        glShaderSource(vertexID, vertexSource);
        glCompileShader(vertexID);

        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer pSuccess = stack.mallocInt(1);
            glGetShaderiv(vertexID, GL_COMPILE_STATUS, pSuccess);

            if (pSuccess.get() != GL_TRUE)
                System.err.println("Failed to load the vertex shader");
        }


        // Fragment engine.rendering.ShaderProgram
        fragmentID = glCreateShader(GL_FRAGMENT_SHADER);
        String fragmentSource = readShaderSource(fragmentPath);
        // Read the source file into the source string
        glShaderSource(fragmentID, fragmentSource);
        glCompileShader(fragmentID);

        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer pSuccess = stack.mallocInt(1);
            glGetShaderiv(fragmentID, GL_COMPILE_STATUS, pSuccess);

            if (pSuccess.get() != GL_TRUE)
                System.err.println("Failed to load the fragment shader");
        }

        // Bind together
        glAttachShader(programID, vertexID);
        glAttachShader(programID, fragmentID);
        glLinkProgram(programID);
    }

    public void bindProgram() {
        glUseProgram(programID);
    }

    @Override
    public void destroy() {
        glDeleteShader(vertexID);
        glDeleteShader(fragmentID);
        glDeleteProgram(programID);
    }

    private String readShaderSource(String shaderName) {
        StringBuilder builder = new StringBuilder();

        try {
            BufferedReader fileInputStream = new BufferedReader(new FileReader(shaderPath + shaderName));
            String line;
            while ((line = fileInputStream.readLine()) != null) {
                builder.append(line + "\n");
            }
        } catch (Exception e) {
            System.err.println("Reading file <" + shaderPath + shaderName + "> has failed.");
        }

        return builder.toString();
    }

    public void setUniformVec3(String uniform, Vector3f vector){
        try(MemoryStack stack = MemoryStack.stackPush()) {
            int location = glGetUniformLocation(programID, uniform);

            FloatBuffer buffer = stack.mallocFloat(3);
            vector.get(buffer);
            glUniform3fv(location, buffer);
        }
    }

    public void setUniformVec4(String uniform, Vector4f vector){
        try(MemoryStack stack = MemoryStack.stackPush()) {
            int location = glGetUniformLocation(programID, uniform);

            FloatBuffer buffer = stack.mallocFloat(4);
            vector.get(buffer);
            glUniform4fv(location, buffer);
        }
    }


    public void setUniformMat4(String uniform, Matrix4f matrix){
        try(MemoryStack stack = MemoryStack.stackPush()) {
            int location = glGetUniformLocation(programID, uniform);

            FloatBuffer buffer = stack.mallocFloat(16);
            matrix.get(buffer);
            glUniformMatrix4fv(location, false, buffer);
        }
    }
}
