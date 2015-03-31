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

		turn = 1;
		cityManager = CityManager.instance;
		player = new Player();
	}

	public void update(Input in) {
		int index;

		if(in != null) {

			index = (int) Math.floor(Math.random() * cityManager.cities.size());
			cityManager.current = cityManager.cities.get(index);

			cityManager.update();

			turn++;
		}
	}
}
