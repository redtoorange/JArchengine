package engine.rendering;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class MeshInstance {
    private Vector3f position;
    private Vector3f rotation;
    private Vector3f scale;
    private Mesh sourceMesh;

    public MeshInstance(Mesh sourceMesh){
        position = new Vector3f(0, 0, 0);
        rotation = new Vector3f(0, 0, 0);
        scale = new Vector3f(1, 1, 1);

        this.sourceMesh = sourceMesh;
    }

    public void render(ShaderProgram shaderProgram){
        // Upload MVP to the shaderProgram
        shaderProgram.setUniformMat4("transform", getTransform());

        sourceMesh.render();
    }

    public Matrix4f getTransform(){
        Matrix4f matrix = new Matrix4f();

        matrix.identity();
        matrix.translation(position);
        matrix.rotate(rotation.x, new Vector3f(1, 0, 0));
        matrix.rotate(rotation.y, new Vector3f(0, 1, 0));
        matrix.rotate(rotation.z, new Vector3f(0, 0, 1));
        matrix.scale(scale);

        return matrix;
    }

    public void translate(Vector3f amount){
        position.add(amount);
    }

    public void rotate(Vector3f amount){
        rotation.add(amount);
    }

    public void scale(Vector3f amount){
        scale.add(amount);
    }


}
