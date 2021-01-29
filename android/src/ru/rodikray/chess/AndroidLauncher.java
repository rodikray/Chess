package ru.rodikray.chess;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import ru.rodikray.chess.Chess;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		config.hideStatusBar    = true; // API 14+
		config.useImmersiveMode = true; // API 19+
		config.useGL30 = false; // Для красоты и понятности; API 18+

		// set RGBA8888
		config.r = 8;
		config.g = 8;
		config.b = 8;
		config.a = 8;

		// Сглаживание CSAA/MSAA
		config.numSamples = 2;

		config.useCompass       = false;
		config.useGyroscope     = false;
		config.useAccelerometer = false;

		config.useWakelock = true;

		initialize(new Chess(), config);
	}
}
