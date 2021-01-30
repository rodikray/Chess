package ru.rodikray.chess.scene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.rodikray.chess.AssetsGlobal;

public class GameScene implements ISceneBase {

    public short IDNextScene = -1;

    @Override
    public void init(AssetsGlobal assets) {
        //
    }

    @Override
    public void update(float _delta) {
        //
    }

    @Override
    public void draw(SpriteBatch _mainBatch) {
        //
    }

    @Override
    public short getIDNextScene() {
        return 0;
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
