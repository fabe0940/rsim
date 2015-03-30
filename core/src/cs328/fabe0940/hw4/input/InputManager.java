package cs328.fabe0940.hw4.input;

public final class InputManager {
	public static final InputManager instance = new InputManager();

	private InputManager() {
		if(instance != null) {
			throw new IllegalStateException("reinstantiating singleton");
		}
	}

	private boolean consumable() {
		boolean res;

		res = false;

		return res;
	}

	public Input consume() {
		Input res;

		res = null;

		if(consumable()) {
		}

		return res;
	}
}
