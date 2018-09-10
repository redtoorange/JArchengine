package engine;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera {
    private Vector3f position = new Vector3f(0, 0, 0);
    private Vector3f rotation = new Vector3f(0, 0, -90.0f);

    private Vector3f Front = new Vector3f(0, 0, -1);
    private Vector3f Up = new Vector3f(0, 1, 0);
    private Vector3f WorldUp = new Vector3f(0, 1, 0);
    private Vector3f Right = new Vector3f(1, 0, 0);

    private float aspectRatio = 800.0f / 600.0f;
    private float fieldOfView = 90.0f;
    private float nearClipPlane = 0.1f;
    private float farClipPlane = 100.0f;


    public void updateCamera() {
        double pitch = rotation.x;
        double yaw = rotation.z;

        Vector3f front = new Vector3f();
        front.x = (float) (Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)));
        front.y = (float) (Math.sin(Math.toRadians(pitch)));
        front.z = (float) (Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)));
        Front.set(front.normalize());

        Right = Front.cross(WorldUp).normalize();
        Up = Right.cross(Front).normalize();
    }

    public Matrix4f getViewMatrix() {
        Vector3f forward = new Vector3f(position);
        forward.add(Front);

        Matrix4f view = new Matrix4f();
        return view.lookAt(position, forward, Up);
    }

    public Matrix4f getProjectionMatrix() {
        Matrix4f projection = new Matrix4f();
        return projection.perspective((float) Math.toRadians(fieldOfView), aspectRatio, nearClipPlane, farClipPlane);
    }
}
