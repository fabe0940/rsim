package cs328.fabe0940.hw4.input;

import java.util.List;
import java.util.ArrayList;

public final class MessageManager {
	public static final MessageManager instance = new MessageManager();

	public List<String> messages;

	private MessageManager() {
		if(instance != null) {
			throw new IllegalStateException("reinstantiating singleton");
		}

		messages = new ArrayList<String>();
	}
}
