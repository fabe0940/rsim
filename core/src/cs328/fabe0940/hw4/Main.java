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
	private InputManager im;
	private MessageManager mm;
	private Display screen;

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Gdx.input.setInputProcessor(this);

		screen = new Display();

		gm = GameManager.instance;
		im = InputManager.instance;
		mm = MessageManager.instance;

		mm.messages.add("Welcome to Traders of the Ancient World!");
		mm.messages.add("You belong to a noble family in misfortune.");
		mm.messages.add("Loaned $100, you seek to rebuild a fortune.");
		mm.messages.add("Make sure you earn enough to repay the load.");
		mm.messages.add("You will need $1000 after 12 months,");
		mm.messages.add("Another $1000 after 18 months,");
		mm.messages.add("and a final $2000 after 24 months.");
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {
		gm.update(im.consume());
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
		im.addClick(x, y);

		return true;
	}

	@Override
	public boolean touchUp (int x, int y, int ptr, int btn) {
		return true;
	}

	@Override
	public boolean touchDragged (int x, int y, int ptr) {
		return true;
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
