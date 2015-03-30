package cs328.fabe0940.hw4;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import cs328.fabe0940.hw4.input.*;
import cs328.fabe0940.hw4.model.*;
import cs328.fabe0940.hw4.render.*;

public class Main implements ApplicationListener, InputProcessor {
	private GameManager gm;
	private InputManager in;
	private Display screen;

	@Override
	public void create () {
		Gdx.input.setInputProcessor(this);

		screen = new Display();

		gm = GameManager.instance;
		in = InputManager.instance;
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {
		screen.render();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public boolean keyDown (int key) {
		return false;
	}

	@Override
	public boolean keyUp (int key) {
		return false;
	}

	@Override
	public boolean keyTyped (char ch) {
		return false;
	}

	@Override
	public boolean touchDown (int x, int y, int ptr, int btn) {
		return false;
	}

	@Override
	public boolean touchUp (int x, int y, int ptr, int btn) {
		return false;
	}

	@Override
	public boolean touchDragged (int x, int y, int ptr) {
		return false;
	}

	@Override
	public boolean mouseMoved (int x, int y) {
		return false;
	}

	@Override
	public boolean scrolled (int val) {
		return false;
	}
}
