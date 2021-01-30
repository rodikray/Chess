package ru.rodikray.chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class AssetsGlobal {

    public AssetManager manager;

    public final AssetDescriptor<Texture> TEXTURE_MENU_BG;


    // Не использовать данный метод при большом количестве ресурсов
    public AssetsGlobal() {
        manager = new AssetManager();

        TEXTURE_MENU_BG = new AssetDescriptor<Texture>(Gdx.files.internal("menu_bg.jpg"), Texture.class);
    }

    public void startLoading() {
        manager.load(TEXTURE_MENU_BG);

        manager.finishLoading();
    }

    public synchronized void dispose() {
        manager.dispose();
    }
}
