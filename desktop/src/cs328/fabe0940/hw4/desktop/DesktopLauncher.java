package cs328.fabe0940.hw4.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import cs328.fabe0940.hw4.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "CS328 Homework 4 - Resource Simulation";
		config.width = 800;
		config.height = 600;
		new LwjglApplication(new Main(), config);
	}
}
