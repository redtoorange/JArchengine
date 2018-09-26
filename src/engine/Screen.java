package engine;

public interface Screen {
    void update( float deltaTime );
    void render();
    void input();
}
