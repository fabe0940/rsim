package cs328.fabe0940.hw4.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class City {
	private static final int GROWTH_RATE = 10;
	private static final int INVENTORY_CAP = 100;

	private int production;
	private int[] inventory;
	private Stat[] prices;

	public String name;

	public City() {
		this("city/default.txt");
	}

	public City(String fname) {
		int i;

		inventory = new int[7];
		prices = new Stat[7];

		for(i = 0; i < Resources.NUM; i++) {
			inventory[i] = 0;
		}

		loadFromFile(fname);
	}

	public void loadFromFile(String fname) {
		int i;
		int val;
		int mod;
		int offset;
		String fcontents;
		String[] vals;
		FileHandle fin;

		offset = 0;

		fin = Gdx.files.internal(fname);
		fcontents = fin.readString();
		vals = fcontents.trim().split("\\s+");

		name = vals[offset++];
		production = Integer.parseInt(vals[offset++]);

		for(i = offset; i < offset + (Resources.NUM * 2); i += 2) {
			val = Integer.parseInt(vals[i + 0]);
			mod = Integer.parseInt(vals[i + 1]);

			prices[(i - offset) / 2] = new Stat(val, mod);
		}
		offset += (Resources.NUM * 2);
	}

	public void update() {
		int i;
		int diff;
		int growth;

		for(i = 0; i < inventory.length; i++) {
			growth = i == production ? GROWTH_RATE : GROWTH_RATE / 3;

			inventory[i] += growth;

			diff = inventory[i] - INVENTORY_CAP;
			if(diff > 0 && diff > (int) Math.floor(Math.random() * 101)) {
				inventory[i] -= (int) (Math.random() * diff);
			}
		}
	}

	public int buy(int type, int size) {
		int price;

		if(type < 0 || type >= 7 || size < 1) {
			throw new IndexOutOfBoundsException();
		}

		if(inventory[type] < size) {
			price = -1;
		} else {
			inventory[type] -= size;
			price = prices[type].get() * size;
		}

		return price;
	}

	public int sell(int type, int size) {
		int price;

		if(type < 0 || type >= 7 || size < 1) {
			throw new IndexOutOfBoundsException();
		}

		inventory[type] += size;
		price = prices[type].get() * size;

		return price;
	}
}
