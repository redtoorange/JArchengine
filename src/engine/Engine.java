package engine;

import engine.input.InputSystem;

public class Engine implements Destroyable {
    private RenderSystem renderSystem;
    private WindowSystem windowSystem;
    private InputSystem inputSystem;
    private TextureManager textureManager;

    /**
     * Create a new engine.Engine and start all the subsystems.
     */
    public Engine() {
        renderSystem = new RenderSystem();
        windowSystem = new WindowSystem();
        inputSystem = new InputSystem( );
        textureManager = new TextureManager();
    }

    /**
     * Automatically cleanup all resources associated with the engine.Engine.
     */
    @Override
    public void destroy() {
        textureManager.destroy();
        inputSystem.destroy();
        renderSystem.destroy();
        windowSystem.destroy();
    }
}
