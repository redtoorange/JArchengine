package game;

import engine.Screen;
import engine.TextureManager;
import engine.rendering.Mesh;
import engine.rendering.MeshInstance;
import engine.rendering.ShaderProgram;
import engine.rendering.Texture;

public class GameScreen implements Screen {
    private float vertices[] = {
            0.5f, 0.5f, 0.0f,  // top right
            0.5f, -0.5f, 0.0f,  // bottom right
            -0.5f, -0.5f, 0.0f,  // bottom left
            -0.5f, 0.5f, 0.0f   // top left
    };

    private int indices[] = {  // note that we start from 0!
            0, 1, 3,   // first triangle
            1, 2, 3    // second triangle
    };

    private float uvs[] = {  // note that we start from 0!
            1.0f, 1.0f,
            1.0f, 0.0f,
            0.0f, 0.0f,
            0.0f, 1.0f
    };

    private ShaderProgram shaderProgram;
    private Texture texture;
    private Mesh mesh;
    private MeshInstance instance;

    public GameScreen() {
        shaderProgram = new ShaderProgram("basic.vert", "basic.frag");
        texture = TextureManager.S.loadTexture("assets/textures/" + "star.png");
        mesh = new Mesh(indices, vertices, uvs);
        instance = new MeshInstance(mesh);
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render() {
        shaderProgram.bindProgram();
        texture.bind();
        instance.render(shaderProgram);
    }

    @Override
    public void input() {

    }
}
