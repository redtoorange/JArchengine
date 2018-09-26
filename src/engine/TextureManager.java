package engine;

import engine.rendering.Texture;

import java.util.HashMap;

public class TextureManager implements Destroyable {
    public static TextureManager S;
    private HashMap<String, Texture> loadedTextures;

    public TextureManager() {
        if (S == null) {
            S = this;
        }

        loadedTextures = new HashMap<String, Texture>();
    }

    public Texture loadTexture(String textureName) {
        if (!textureLoaded(textureName)) {
            loadedTextures.put(textureName, new Texture(textureName));
        }

        return loadedTextures.get(textureName);
    }

    public boolean textureLoaded(String textureName) {
        return loadedTextures.containsKey(textureName);
    }

    @Override
    public void destroy() {
        for (Texture t : loadedTextures.values()) {
            t.destroy();
        }

        loadedTextures.clear();
    }
}
