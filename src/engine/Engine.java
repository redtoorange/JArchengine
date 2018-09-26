package engine;

import engine.input.InputSystem;

public class Engine implements Destroyable {
    private RenderSystem renderSystem;
    private WindowSystem windowSystem;
    private InputSystem inputSystem;
    private TextureManager textureManager;
    private Timer timer;
    private boolean running;

    private Screen currentScreen;

    /**
     * Create a new engine.Engine and start all the subsystems.
     */
    public Engine() {
        renderSystem = new RenderSystem();
        windowSystem = new WindowSystem();
        inputSystem = new InputSystem( );
        textureManager = new TextureManager();
        timer = new Timer();
    }

    public void run(){
        running = true;

        while(running){
            input();
            update();
            render();
        }
    }

    private void input(){
        inputSystem.update();

        if(currentScreen != null)
            currentScreen.input();

        if(windowSystem.getCurrentWindow().shouldClose())
            quit();

    }

    private void update(){
        float deltaTime = timer.getDelta();

        if(currentScreen != null)
            currentScreen.update(deltaTime);
    }

    private void render(){
        renderSystem.clearScreen();

        if(currentScreen != null)
            currentScreen.render();

        windowSystem.getCurrentWindow().swapBuffers();
    }

    public void quit(){
        running = false;
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

    public void setCurrentScreen(Screen screen){
        currentScreen = screen;
    }
}
