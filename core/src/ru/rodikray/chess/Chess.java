package ru.rodikray.chess;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.rodikray.chess.debug.Logs;
import ru.rodikray.chess.scene.SceneManager;

// Не использовать класс Game, конкретно здесь это лишнее. Только ApplicationAdapter
public class Chess extends ApplicationAdapter {

	// Virtual size game world; default 800x480 or 1.6(6)
	public static float WIDTH_WORLD  = 800f;
	public static float HEIGHT_WORLD = 480f;

	public static boolean isDebugMode = false;

	private OrthographicCamera ortCamera;
	private Viewport extViewport;

	private SceneManager sceneManager;
	private SpriteBatch mainBatch;
	
	@Override
	public void create() {

		/* ---- debug info ---- */
		if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
			isDebugMode = true;

			Logs.log(Logs.LOG_TAG_GL_VERSION, Gdx.graphics.getGLVersion().getMajorVersion() + "." + Gdx.graphics.getGLVersion().getMinorVersion());
			Logs.log(Logs.LOG_TAG_TEXTURE_MAX_SIZE, String.valueOf(GL20.GL_MAX_TEXTURE_SIZE));
			Logs.log(Logs.LOG_TAG_JAVA_VERSION, String.valueOf(System.getProperty("java.version")));
		}

		ortCamera    = new OrthographicCamera(WIDTH_WORLD, HEIGHT_WORLD);
		extViewport  = new ExtendViewport(ortCamera.viewportWidth, ortCamera.viewportHeight, ortCamera);
		sceneManager = new SceneManager(SceneManager.SCENE_ID_MENU); /* === id first scene for fast debug === */
		mainBatch    = new SpriteBatch();

		extViewport.apply(true);
		ortCamera.position.set(ortCamera.viewportWidth / 2f, ortCamera.viewportHeight / 2f, 0);

		sceneManager.setEffect(SceneManager.EFFECT_FADING);
	}

	/* --- Основная логика игры, то есть переключение "сцен" ---  */
	private void gameUpdate() {
		if (sceneManager.isReady()) {
			if (sceneManager.getScene().getIDNextScene() > 0) {
				sceneManager.setEffect(SceneManager.EFFECT_FADING);
				sceneManager.goToScene(sceneManager.getScene().getIDNextScene());
			}
		}
	}

	@Override
	public void render() {
		float delta = Gdx.graphics.getDeltaTime();
		GL20 gl2 = Gdx.graphics.getGL20();

		gameUpdate();

		// preparation
		ortCamera.update(true);
		mainBatch.setProjectionMatrix(extViewport.getCamera().combined);

		gl2.glClearColor(0f, 0f, 0f, 1f);
		gl2.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		gl2.glEnable(GL20.GL_BLEND);
		gl2.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		/* --------------------- RENDER Scene ------------------ */

		// Стоит заметить, да... такая конструкция медлительная, но зато рабочая и удобная
		sceneManager.renderActiveScene(delta, mainBatch);

		gl2.glDisable(GL20.GL_BLEND);
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

		extViewport.update(width, height, true);
		ortCamera.position.set(ortCamera.viewportWidth / 2f, ortCamera.viewportHeight / 2f, 0);

		WIDTH_WORLD  = extViewport.getWorldWidth();
		HEIGHT_WORLD = extViewport.getWorldHeight();
	}

	@Override
	public void pause() {
		super.pause();

		// TODO
	}

	@Override
	public void resume() {
		super.resume();

		// TODO
	}

	@Override
	public void dispose() {
		sceneManager.dispose();
		mainBatch.dispose();
	}
}
