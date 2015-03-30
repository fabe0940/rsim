package cs328.fabe0940.hw4.model;

public final class GameManager {
	public static final GameManager instance = new GameManager();

	public CityManager cityManager;
	public Player player;

	private GameManager() {
		if(instance != null) {
			throw new IllegalStateException("reinstantiating singleton");
		}

		cityManager = CityManager.instance;
		player = new Player();
	}

	public void update() {
		cityManager.update();
	}
}
