package cs328.fabe0940.hw4.model;

import java.lang.Math;

public class Stat {
	private int val;
	private int mod;
	private int modCurrent;

	public Stat() {
		this(0, 0);
	}

	public Stat(int v, int m) {
		val = v;
		mod = m;
	}

	public Stat(Stat s) {
		val = s.getVal();
		mod = s.getMod();

		update();
	}

	public int getVal() {
		return val;
	}

	public int getMod() {
		return mod;
	}

	public void update() {
		modCurrent = (int) Math.floor(Math.random() * (mod + 1));
	}

	public int get() {
		return val + modCurrent;
	}
}
