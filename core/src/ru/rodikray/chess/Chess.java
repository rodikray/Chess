package ru.rodikray.chess;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Chess extends ApplicationAdapter {

	// Удалить в будущем...
	SpriteBatch batch;
	Texture img;

	// Virtual size's world
	public static float WIDTH_WORLD  = 800f;
	public static float HEIGHT_WORLD = 480f;

	private OrthographicCamera ortCamera;
	private Viewport viewport;
	
	@Override
	public void create () {
		ortCamera = new OrthographicCamera(WIDTH_WORLD, HEIGHT_WORLD);
		viewport = new ExtendViewport(ortCamera.viewportWidth, ortCamera.viewportHeight, ortCamera);

		viewport.apply(true);
		ortCamera.position.set(ortCamera.viewportWidth / 2f, ortCamera.viewportHeight / 2f, 0);

		// Изменить в будущем...
		batch = new SpriteBatch();
		img   = new Texture(Gdx.files.internal("badlogic.jpg"));

		img.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
	}

	@Override
	public void render () {
		GL20 gl2 = Gdx.gl20;

		ortCamera.update(true);
		batch.setProjectionMatrix(viewport.getCamera().combined);

		gl2.glClearColor(1, 0, 0, 1);
		gl2.glClear(GL20.GL_COLOR_BUFFER_BIT);


		batch.begin();

		batch.draw(img, WIDTH_WORLD / 2f - img.getWidth() / 2f, HEIGHT_WORLD / 2f - img.getHeight() / 2f, img.getWidth(), img.getHeight());

		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

		viewport.update(width, height, true);
		ortCamera.position.set(ortCamera.viewportWidth / 2f, ortCamera.viewportHeight / 2f, 0);

		WIDTH_WORLD  = viewport.getWorldWidth();
		HEIGHT_WORLD = viewport.getWorldHeight();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
