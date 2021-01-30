package ru.rodikray.chess.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.rodikray.chess.AssetsGlobal;
import ru.rodikray.chess.Chess;

public class LogoScene implements ISceneBase {

    public short IDNextScene = -1;

    private Texture imgLogo;
    private Sprite spLogo;

    private int timer = 80;
    private float alphaLogo = 1f;
    private float alphaStep = 0.005f;


    // Ахахаха... да... лучше здесь не использовать assets, иначе может покрашиться
    @Override
    public void init(AssetsGlobal assets) {
        imgLogo = new Texture(Gdx.files.internal("badlogic.jpg"));
        imgLogo.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        spLogo = new Sprite(imgLogo);
        spLogo.setPosition((Chess.WIDTH_WORLD - imgLogo.getWidth()) / 2f, (Chess.HEIGHT_WORLD - imgLogo.getHeight()) / 2f);
    }

    @Override
    public void update(float _delta) {
        if (timer <= 0) {
            spLogo.setAlpha(alphaLogo);
            //spLogo.setPosition(Chess.WIDTH_WORLD / 2f - imgLogo.getWidth() / 2f, Chess.HEIGHT_WORLD / 2f - imgLogo.getHeight() / 2f);

            alphaLogo = Math.max(alphaLogo - alphaStep, 0);
            alphaStep += 0.001f;

            if (alphaLogo <= 0) {
                IDNextScene = SceneManager.SCENE_ID_MENU;
            }
        }else timer--;
    }

    @Override
    public void draw(SpriteBatch _mainBatch) {
        _mainBatch.begin();

        spLogo.draw(_mainBatch);

        _mainBatch.end();
    }

    @Override
    public short getIDNextScene() {
        return IDNextScene;
    }

    @Override
    public ISceneBase getSceneInstance() {
        return this;
    }

    @Override
    public void dispose() {
        imgLogo.dispose();
    }
}
