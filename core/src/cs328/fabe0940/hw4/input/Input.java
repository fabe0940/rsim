package cs328.fabe0940.hw4.input;

public class Input {
	public static final int BUY = 1;
	public static final int SELL = 2;
	public static final int TRAVEL = 3;

	public final int action;
	public final int resource;
	public final int size;

	public Input(int a, int r, int s) {
		action = a;
		resource = r;
		size = s;
	}
}
