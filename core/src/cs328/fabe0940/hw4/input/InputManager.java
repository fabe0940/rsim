package cs328.fabe0940.hw4.input;

import java.util.List;
import java.util.ArrayList;
import cs328.fabe0940.hw4.model.GameManager;
import cs328.fabe0940.hw4.model.Resources;
import cs328.fabe0940.hw4.input.MessageManager;

public final class InputManager {
	public static final InputManager instance = new InputManager();

	private GameManager gm;
	private MessageManager mm;
	private List<Position> clicks;

	private InputManager() {
		if(instance != null) {
			throw new IllegalStateException("reinstantiating singleton");
		}

		gm = GameManager.instance;
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
		int action;
		int type;
		Input res;
		Position pos;

		res = null;

		if(consumable()) {
			pos = clicks.remove(0);

			if(pos.x >= 600 && pos.y >= 120 && pos.y <= 400) {
				action = Input.TRAVEL;
				type = (int) ((pos.y - 120) / 40);

				res = new Input(action, type, 0);
			}

			if(pos.x >= 101 && pos.y >= 550 && pos.y <= 580) {
				action = (pos.x % 100) < 50 ? Input.BUY : Input.SELL;
				type = (int) ((pos.x - 100) / 100);

				res = new Input(action, type, 1);
			}
		}

		return res;
	}
}
