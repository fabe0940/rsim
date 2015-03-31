package cs328.fabe0940.hw4.model;

import cs328.fabe0940.hw4.input.MessageManager;

public class Player {
	private static final int INVENTORY_CAP = 100;
	private static final int INITIAL_MONEY = 100;

	private int money;
	private int[] inventory;

	public Player() {
		int i;

		money = INITIAL_MONEY;
		inventory = new int[Resources.NUM];

		for(i = 0; i < Resources.NUM; i++) {
			inventory[i] = 0;
		}
	}

	public void pay(int val) {
		money -= val;
	}

	public int money() {
		return money;
	}

	public int get(int type) {
		return inventory[type];
	}

	public void buy(City c, int type, int size) {
		int i;
		int free;
		int cost;
		MessageManager mm;

		mm = MessageManager.instance;

		free = 0;
		for(i = 0; i < Resources.NUM; i++) {
			free += inventory[i];
		}
		free = INVENTORY_CAP - free;

		if(free > 0 && size <= free) {
			cost = c.buy(type, size);

			if(cost != -1 && cost <= money) {
				inventory[type] += size;
				money -= cost;
			}
		} else {
			mm.messages.add("Your inventory is full!");
		}
	}

	public void sell(City c, int type, int size) {
		if(size <= inventory[type]) {
			inventory[type] -= size;
			money += c.sell(type, size);
		}
	}
}
