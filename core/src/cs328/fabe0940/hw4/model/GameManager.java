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
			switch(in.action) {
				case Input.BUY:
					player.buy(cityManager.current, in.resource, in.size);
					break;
				case Input.SELL:
					player.sell(cityManager.current, in.resource, in.size);
					break;
				case Input.TRAVEL:
					cityManager.setCurrent(cityManager.names[in.resource]);
					cityManager.update();
					turn++;
					break;
				default:
					throw new IndexOutOfBoundsException();
			}
		}
	}
}
