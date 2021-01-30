package ru.rodikray.chess.debug;

import com.badlogic.gdx.Gdx;

public class Logs {
    public static final String LOG_TAG_GL_VERSION       = "[GL] Version";
    public static final String LOG_TAG_JAVA_VERSION     = "[JAVA] Version";
    public static final String LOG_TAG_TEXTURE_MAX_SIZE = "[GL] Max texture size";
    public static final String LOG_TAG_SCENE_ERROR      = "[!!!SCENE_ERROR!!!]";
    public static final String LOG_TAG_SCENE_MANAGER    = "[SCENE]";

    public static void log(String tag, String msg) {
        if (msg != null) Gdx.app.log(tag + ": @", msg);
        else Gdx.app.log(tag + ": @", "Msg is null");
    }

    public static void log(String tag, int msg) {
        Gdx.app.log(tag + ": @", String.valueOf(msg));
    }
}
