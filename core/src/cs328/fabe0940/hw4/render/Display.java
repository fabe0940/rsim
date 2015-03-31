package cs328.fabe0940.hw4.render;

import java.util.Map;
import java.util.HashMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import cs328.fabe0940.hw4.input.MessageManager;
import cs328.fabe0940.hw4.model.*;

public class Display {
	private static final int SELL = 8;
	private static final int BUY = 9;
	private static final int ALEXANDRIA = 10;
	private static final int BABYLON = 11;
	private static final int EPHESOS = 12;
	private static final int GIZAH = 13;
	private static final int HALIKARNASSOS = 14;
	private static final int OLYMPIA = 15;
	private static final int RHODOS = 16;
	private static final int INTERFACE = 17;
	private static final int POPUP = 18;

	private SpriteBatch batch;
	private Sprite sprite;
	private BitmapFont mainFont;
	private BitmapFont inventoryFont;
	private BitmapFont travelFont;
	private BitmapFont titleFont;
	private FreeTypeFontGenerator heorotGen;
	private FreeTypeFontGenerator marathonGen;
	private Map<Integer, String> sprites;
	private Map<Integer, Texture> textures;

	private Texture getTexture(int i) {
		String fname;

		if(textures.get(i) == null) {
			if((fname = sprites.get(i)) == null) {
				throw new IndexOutOfBoundsException();
			}

			textures.put(i, new Texture(Gdx.files.internal(fname)));
		}

		return textures.get(i);
	}

	private void loadSprites() {
		int i;
		int index;
		int offset;
		int numEntities;
		String fname;
		String fcontents;
		String sprite;
		String[] vals;
		FileHandle fin;

		fname = "img/sprites.txt";
		offset = 0;

		fin = Gdx.files.internal(fname);
		fcontents = fin.readString();
		vals = fcontents.trim().split("\\s+");

		numEntities = Integer.parseInt(vals[offset++]);
		for(i = offset; i < offset + (numEntities * 2); i += 2) {
			index = Integer.parseInt(vals[i + 0]);
			sprite = vals[i + 1];

			sprites.put(index, sprite);
		}
		offset += numEntities * 2;
	}

	public Display() {
		String fname;

		batch = new SpriteBatch();
		sprites = new HashMap<Integer, String>();
		textures = new HashMap<Integer, Texture>();

		fname = "font/heorot.ttf";
		heorotGen = new FreeTypeFontGenerator(Gdx.files.internal(fname));

		fname = "font/marathon.ttf";
		marathonGen = new FreeTypeFontGenerator(Gdx.files.internal(fname));

		mainFont = marathonGen.generateFont(25);
		mainFont.setColor(Color.BLACK);

		inventoryFont = marathonGen.generateFont(30);
		inventoryFont.setColor(Color.BLACK);

		travelFont = marathonGen.generateFont(35);
		travelFont.setColor(Color.BLACK);

		titleFont = marathonGen.generateFont(45);
		titleFont.setColor(Color.BLACK);
                
		loadSprites();
	}

	public void dispose() {
		for(Map.Entry item : textures.entrySet()) {
			((Texture) item.getValue()).dispose();
		}
	}

	public void render() {
		int i;
		int id;
		String msg;
		GameManager gm;
		MessageManager mm;

		gm = GameManager.instance;
		mm = MessageManager.instance;

		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.enableBlending();
		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		batch.begin();

		sprite = new Sprite(getTexture(INTERFACE));
		sprite.setPosition(0, 0);
		sprite.draw(batch);

		id = -1;

		if(gm.cityManager.current.name.equals("Alexandria")) {
			id = ALEXANDRIA;
		} else if(gm.cityManager.current.name.equals("Babylon")) {
			id = BABYLON;
		} else if(gm.cityManager.current.name.equals("Ephesos")) {
			id = EPHESOS;
		} else if(gm.cityManager.current.name.equals("Gizah")) {
			id = GIZAH;
		} else if(gm.cityManager.current.name.equals("Halikarnassos")) {
			id = HALIKARNASSOS;
		} else if(gm.cityManager.current.name.equals("Olympia")) {
			id = OLYMPIA;
		} else if(gm.cityManager.current.name.equals("Rhodos")) {
			id = RHODOS;
		}

		sprite = new Sprite(getTexture(id));
		sprite.setPosition(0, 200);
		sprite.draw(batch);

		msg = gm.cityManager.current.name;
		titleFont.draw(batch, msg, 15, 582);

		msg = new String("Month " + gm.turn);
		titleFont.draw(batch, msg, 365, 582);

		msg = new String("$" + gm.player.money());
		titleFont.draw(batch, msg, 655, 582);

		titleFont.draw(batch, "Travel To", 635, 535);

		for(i = 0; i < gm.cityManager.names.length; i++) {
			travelFont.draw(batch, gm.cityManager.names[i], 615, 480 - (40 * i));
		}

		mainFont.draw(batch, "Price", 20, 135);
		mainFont.draw(batch, "Avalable", 20, 105);
		mainFont.draw(batch, "Owned", 20, 75);

		for(i = 0; i < Resources.NUM; i++) {
			sprite = new Sprite(getTexture(i + 1));
			sprite.setPosition((100 * (i + 1)) + 35, 150);
			sprite.draw(batch);

			msg = Integer.toString(gm.cityManager.current.price(i));
			inventoryFont.draw(batch, msg, (100 * (i + 1)) + 40, 140);

			msg = Integer.toString(gm.cityManager.current.get(i));
			inventoryFont.draw(batch, msg, (100 * (i + 1)) + 40, 110);

			msg = Integer.toString(gm.player.get(i));
			inventoryFont.draw(batch, msg, (100 * (i + 1)) + 40, 80);

			sprite = new Sprite(getTexture(BUY));
			sprite.setPosition((100 * (i + 1)) + 15, 20);
			sprite.draw(batch);

			sprite = new Sprite(getTexture(SELL));
			sprite.setPosition((100 * (i + 1)) + 55, 20);
			sprite.draw(batch);
		}

		if(mm.instance.messages.size() > 0) {
			sprite = new Sprite(getTexture(POPUP));
			sprite.setPosition(0, 0);
			sprite.draw(batch);

			msg = mm.instance.messages.get(0);
			titleFont.draw(batch, msg, 165, 435);
		}

		batch.end();
	}
}
