package ru.rodikray.chess.scene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.rodikray.chess.AssetsGlobal;

public interface ISceneBase {
    void init(AssetsGlobal assets);
    void update(float _delta);
    void draw(SpriteBatch _mainBatch);
    short getIDNextScene();
    void dispose();

    // default or static in API 24+
    ISceneBase getSceneInstance();
}
