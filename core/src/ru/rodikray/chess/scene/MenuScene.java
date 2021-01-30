package ru.rodikray.chess.scene;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.rodikray.chess.AssetsGlobal;
import ru.rodikray.chess.Chess;

public class MenuScene implements ISceneBase {

    public short IDNextScene = -1;

    private Texture imgMenuBg;

    @Override
    public void init(AssetsGlobal assets) {
        imgMenuBg = assets.manager.get(assets.TEXTURE_MENU_BG);
    }

    @Override
    public void update(float _delta) {
        //
    }

    @Override
    public void draw(SpriteBatch _mainBatch) {
        _mainBatch.begin();

        _mainBatch.draw(imgMenuBg, 0, 0, Chess.WIDTH_WORLD, Chess.HEIGHT_WORLD);

        _mainBatch.end();
    }

    @Override
    public short getIDNextScene() {
        return IDNextScene;
    }

    @Override
    public void dispose() {
        //
    }

    @Override
    public ISceneBase getSceneInstance() {
        return this;
    }
}
