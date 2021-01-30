package ru.rodikray.chess.scene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import ru.rodikray.chess.AssetsGlobal;
import ru.rodikray.chess.Chess;
import ru.rodikray.chess.debug.Logs;

public class SceneManager {
    public static final short SCENE_ID_GAME = 2;
    public static final short SCENE_ID_LOGO = 0;
    public static final short SCENE_ID_MENU = 1;

    public static final String EFFECT_NONE   = "None";
    public static final String EFFECT_FADING = "Fading";

    private final String[] SCENE_NAMES_ORDER = {"logo", "menu", "game"}; // 0, 1, 2 ...

    private short sceneID = -1;
    private short previousSceneID = -1;
    private ISceneBase sceneInstance;

    private boolean isSceneInit  = false;
    private boolean isSceneReady = false;

    private final ShapeRenderer effectsRender;
    private String effectName;
    private float alphaRect = 0f;

    private final AssetsGlobal assets;

    public SceneManager(short firstSceneID) {
        goToScene(firstSceneID);

        effectsRender = new ShapeRenderer(32);
        effectName    = EFFECT_NONE;

        assets = new AssetsGlobal();
        assets.startLoading();
    }

    private boolean initScene(String sceneName) {
        switch (sceneName) {
            case "logo":
                sceneInstance = new LogoScene().getSceneInstance();
                break;

            case "menu":
                sceneInstance = new MenuScene().getSceneInstance();
                break;

            case "game":
                // TODO
                break;

            default:
                Logs.log(Logs.LOG_TAG_SCENE_ERROR, "What? It's impossible! Scene '" + sceneName + "' not found!");
                return false;
        }

        return true;
    }

    public void goToScene(short newSceneID) {
        if (previousSceneID != newSceneID) {
            if (Chess.isDebugMode)
                Logs.log(Logs.LOG_TAG_SCENE_MANAGER, "Loading / Init new scene...");

            isSceneReady = false;
            isSceneInit  = false;

            if (sceneInstance != null) {
                sceneInstance.dispose();
                sceneInstance = null;
            }

            sceneID = newSceneID;
            isSceneReady = initScene(getNowSceneName());

            previousSceneID = sceneID;

            if (Chess.isDebugMode)
                Logs.log(Logs.LOG_TAG_SCENE_MANAGER, "Scene '" + getNowSceneName() + "' successful init!");
        }
    }

    public void renderActiveScene(float _delta, SpriteBatch _mainBatch) {

        // scene effects!
        effectsRender.begin(ShapeRenderer.ShapeType.Filled);
        if (!effectName.equals(EFFECT_NONE)) {
            // update
            effectsRender.setProjectionMatrix(_mainBatch.getProjectionMatrix());

            if (alphaRect > -1f) {
                alphaRect -= 0.01f;
            } else {
                alphaRect = 1f;
            }

            // draw
            effectsRender.setColor(new Color(0f, 0f, 0f, alphaRect));
            effectsRender.rect(0, 0, Chess.WIDTH_WORLD, Chess.HEIGHT_WORLD);
        }

        // scene render and init
        if (isReady()) {
            sceneInstance.update(_delta);
            sceneInstance.draw(_mainBatch);
        }

        if (!isSceneInit) {
            if (assets.manager.update() && assets.manager.isFinished() || sceneID == SCENE_ID_LOGO) {
                sceneInstance.init(assets);
                isSceneInit = true;
            }
        }

        effectsRender.end();
    }

    public void setEffect(String effectName) {
        if (this.effectName.equals(EFFECT_NONE)) this.effectName = effectName;
    }

    public boolean isReady() {
        return (isSceneReady && sceneInstance != null && isSceneInit);
    }

    public String getNowSceneName() {
        return SCENE_NAMES_ORDER[sceneID];
    }

    public ISceneBase getScene() {
        return sceneInstance;
    }

    public void dispose() {
        if (sceneInstance != null) {
            sceneInstance.dispose();
            sceneInstance = null;
        }
        effectsRender.dispose();
        assets.dispose();
    }
}
