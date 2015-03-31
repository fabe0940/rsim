package cs328.fabe0940.hw4.model;

import cs328.fabe0940.hw4.input.Input;

public final class GameManager {
	public static final GameManager instance = new GameManager();

	public int turn;
	public CityManager cityManager;
	public Player player;

	private GameManager() {
		if(instance != null) {
			throw new IllegalStateException("reinstantiating singleton");
		}

		turn = 0;
		cityManager = CityManager.instance;
		player = new Player();
	}

	public void update(Input in) {
		if(in != null) {
			cityManager.update();

			turn++;
		}
	}
}
