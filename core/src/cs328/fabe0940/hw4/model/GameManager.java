package cs328.fabe0940.hw4.model;

import cs328.fabe0940.hw4.input.Input;
import cs328.fabe0940.hw4.input.MessageManager;

public final class GameManager {
	public static final GameManager instance = new GameManager();

	public int turn;
	public int goal;
	public CityManager cityManager;
	public Player player;

	private GameManager() {
		if(instance != null) {
			throw new IllegalStateException("reinstantiating singleton");
		}

		turn = 1;
		goal = 1000;
		cityManager = CityManager.instance;
		player = new Player();
	}

	public void update(Input in) {
		int index;
		MessageManager mm;

		mm = MessageManager.instance;

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

					switch(++turn) {
						case 10:
						case 11:
						case 12:
							mm.messages.add("You have a payment of $1000 in " + (13 - turn) + " turns.");
							break;
						case 13:
							player.pay(goal);
							if(player.money() < 0) {
								mm.messages.add("You couldn't pay - GAME OVER");
							} else {
								mm.messages.add("You payed $1000 on your loan.");
								mm.messages.add("The next payment is $1000 after Month 18");
							}
							goal = 1000;
							break;
						case 16:
						case 17:
						case 18:
							mm.messages.add("You have a payment of $1000 in " + (19 - turn) + " turns.");
							break;
						case 19:
							player.pay(goal);
							if(player.money() < 0) {
								mm.messages.add("You couldn't pay - GAME OVER");
							} else {
								mm.messages.add("You payed $1000 on your loan.");
								mm.messages.add("The next payment is $2000 after Month 24");
							}
							goal = 2000;
							break;
						case 22:
						case 23:
						case 24:
							mm.messages.add("You have a payment of $1000 in " + (25 - turn) + " turns.");
							break;
						case 25:
							player.pay(goal);
							if(player.money() < 0) {
								mm.messages.add("You couldn't pay - GAME OVER");
							} else {
								mm.messages.add("You payed $2000 on your loan.");
								mm.messages.add("You have repayed the loan in full.");
								mm.messages.add("Your family's honor is restored.");
								mm.messages.add("You WIN! Now go make even more money.");
							}
							goal = 0;
							break;
					}

					break;
				default:
					throw new IndexOutOfBoundsException();
			}
		}
	}
}
