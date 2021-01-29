package ru.rodikray.chess.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.rodikray.chess.Chess;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config_dek = new LwjglApplicationConfiguration();

		config_dek.forceExit = false;
		config_dek.useGL30 = false;

		config_dek.title = "Chess";

		config_dek.x = 0;
		config_dek.y = 0;
		config_dek.width = 800;
		config_dek.height = 480;
		config_dek.resizable = true;

		config_dek.pauseWhenBackground = true;

		new LwjglApplication(new Chess(), config_dek);
	}
}
