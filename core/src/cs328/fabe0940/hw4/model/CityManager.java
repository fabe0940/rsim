package cs328.fabe0940.hw4.model;

import java.util.List;
import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public final class CityManager {
	public static final CityManager instance = new CityManager();

	public List<City> cities;
	public City current;

	private CityManager() {
		if(instance != null) {
			throw new IllegalStateException("reinstantiating singleton");
		}

		cities = new ArrayList<City>();

		loadFromFile("city/list.txt");
		update();
	}

	public void loadFromFile(String fname) {
		int i;
		String fcontents;
		String[] vals;
		FileHandle fin;
		City c;

		fin = Gdx.files.internal(fname);
		fcontents = fin.readString();
		vals = fcontents.trim().split("\\s+");

		for(i = 0; i < vals.length; i++) {
			c = new City();
			c.loadFromFile(vals[i]);
			cities.add(c);
		}

		i = (int) Math.floor(Math.random() * cities.size());
		current = cities.get(i);
	}

	public void update() {
		for(City c : cities) {
			c.update();
		}
	}

	public void setCurrent(String name) {
		for(City c : cities) {
			if(c.name == name) {
				current = c;
			}
		}
	}
}
