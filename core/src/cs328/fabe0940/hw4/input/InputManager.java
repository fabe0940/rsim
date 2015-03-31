package cs328.fabe0940.hw4.input;

import java.util.List;
import java.util.ArrayList;
import cs328.fabe0940.hw4.input.MessageManager;

public final class InputManager {
	public static final InputManager instance = new InputManager();

	private MessageManager mm;
	private List<Position> clicks;

	private InputManager() {
		if(instance != null) {
			throw new IllegalStateException("reinstantiating singleton");
		}

		mm = MessageManager.instance;
		clicks = new ArrayList<Position>();
	}

	private boolean consumable() {
		return clicks.size() > 0;
	}

	public void addClick(int x, int y) {
		if(mm.messages.size() == 0) {
			clicks.add(new Position(x, y));
		} else {
			mm.messages.remove(0);
		}
	}

	public Input consume() {
		Input res;
		Position pos;

		res = null;

		if(consumable()) {
			pos = clicks.remove(0);
			mm.messages.add(new String("Click: (" + pos.x + "," + pos.y + ")"));
		}

		return res;
	}
}
