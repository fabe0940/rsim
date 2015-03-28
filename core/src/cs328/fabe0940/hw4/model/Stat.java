package cs328.fabe0940.hw4.model;

import java.lang.Math;

public class Stat {
	private int val;
	private int mod;

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
	}

	public int getVal() {
		return val;
	}

	public int getMod() {
		return mod;
	}

	public int get() {
		int res;

		res = val;
		res += Math.floor(Math.random() * (mod + 1));

		return res;
	}
}
