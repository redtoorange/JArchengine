public class Engine implements Destroyable {
    private RenderSystem renderSystem;
    private WindowSystem windowSystem;
    private TextureManager textureManager;

    /**
     * Create a new Engine and start all the subsystems.
     */
    public Engine() {
        renderSystem = new RenderSystem();
        windowSystem = new WindowSystem();
        textureManager = new TextureManager();
    }

    /**
     * Automatically cleanup all resources associated with the Engine.
     */
    @Override
    public void destroy() {
        textureManager.destroy();
        renderSystem.destroy();
        windowSystem.destroy();
    }
}
