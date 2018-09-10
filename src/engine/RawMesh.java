package engine;

import org.lwjgl.system.MemoryStack;

import static org.lwjgl.opengl.GL33.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class RawMesh implements Destroyable {
    private int VAO;
    private int vertexCount;

    private int vertexVBO;
    private int indexVBO;
    private int colorVBO;
    private int uvVBO;
    private int normalVBO;

    public RawMesh(int[] elementData, float[] vertexData, float[] uvData) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            // Generate Buffers
            IntBuffer iBuffer = stack.mallocInt(1);
            glGenVertexArrays(iBuffer);
            VAO = iBuffer.get(0);

            glGenBuffers(iBuffer);
            indexVBO = iBuffer.get(0);

            glGenBuffers(iBuffer);
            vertexVBO = iBuffer.get(0);

            glGenBuffers(iBuffer);
            uvVBO = iBuffer.get(0);

            glBindVertexArray(VAO);


            // Load Data into memory
            IntBuffer elementBuffer = stack.mallocInt(elementData.length);
            elementBuffer.put(elementData);
            elementBuffer.flip();

            FloatBuffer vertexBuffer = stack.mallocFloat(vertexData.length);
            vertexBuffer.put(vertexData);
            vertexBuffer.flip();

            FloatBuffer uvBuffer = stack.mallocFloat(uvData.length);
            uvBuffer.put(uvData);
            uvBuffer.flip();

            // Push data to the GPU
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexVBO);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW);

            // Enable attributes
            glBindBuffer(GL_ARRAY_BUFFER, vertexVBO);
            glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
            glEnableVertexAttribArray(0);


            glBindBuffer(GL_ARRAY_BUFFER, uvVBO);
            glBufferData(GL_ARRAY_BUFFER, uvBuffer, GL_STATIC_DRAW);
            glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
            glEnableVertexAttribArray(1);
        }
//        VAO = generateVAO();
//        glBindVertexArray(VAO);
//
//        indexVBO = generateVBO();
//        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexVBO);
//        loadElementArray(elementData);
//        vertexCount = elementData.length;
//
//        vertexVBO = generateVBO();
//        glBindBuffer(GL_ARRAY_BUFFER, vertexVBO);
//        loadBufferData(vertexData, GL_ARRAY_BUFFER, GL_STATIC_DRAW, 0, 3);
//
//        glBindVertexArray(0);
    }


    private int generateVAO() {
        int newVAO;

        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer pVAO = stack.mallocInt(1);
            glGenVertexArrays(pVAO);
            newVAO = pVAO.get(0);
        }

        return newVAO;
    }

    private int generateVBO() {
        int newVBO;

        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer pVBO = stack.mallocInt(1);
            glGenBuffers(pVBO);
            newVBO = pVBO.get(0);
        }

        return newVBO;
    }


    private void loadBufferData(float[] data, int target, int usage, int location, int size) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer floatBuffer = stack.mallocFloat(data.length);
            floatBuffer.put(data);
            floatBuffer.flip();

            glBufferData(target, floatBuffer, usage);
            glVertexAttribPointer(location, size, GL_FLOAT, false, 0, 0);
            glEnableVertexAttribArray(location);
        }
    }

    private void loadElementArray(int[] data) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer intBuffer = stack.mallocInt(data.length);
            intBuffer.put(data);
            intBuffer.flip();

            glBufferData(GL_ELEMENT_ARRAY_BUFFER, intBuffer, GL_STATIC_DRAW);
        }
    }

    public void render() {
        glBindVertexArray(VAO);
        glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0);
    }

    @Override
    public void destroy() {
        glDeleteBuffers(vertexVBO);
        glDeleteBuffers(uvVBO);
        glDeleteBuffers(indexVBO);
        glDeleteVertexArrays(VAO);
    }
}
