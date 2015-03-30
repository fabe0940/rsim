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
import cs328.fabe0940.hw4.model.GameManager;

public class Display {
	private static final int SELL = 8;
	private static final int BUY = 9;
	private static final int INTERFACE = 10;
	private static final int ALEXANDRIA = 11;
	private static final int BABYLON = 12;
	private static final int EPHESOS = 13;
	private static final int GIZAH = 14;
	private static final int HALIKARNASSOS = 15;
	private static final int OLYMPIA = 16;
	private static final int RHODOS = 17;

	private SpriteBatch batch;
	private Sprite sprite;
	private BitmapFont font;
	private FreeTypeFontGenerator fgen;
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
		batch = new SpriteBatch();
		sprites = new HashMap<Integer, String>();
		textures = new HashMap<Integer, Texture>();

		fgen = new FreeTypeFontGenerator(Gdx.files.internal("font/heorot.ttf"));
		font = fgen.generateFont(30);
		font.setColor(Color.BLACK);

                
		loadSprites();
	}

	public void dispose() {
		for(Map.Entry item : textures.entrySet()) {
			((Texture) item.getValue()).dispose();
		}
	}

	public void render() {
		int id;
		GameManager gm;

		gm = GameManager.instance;

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

		font.draw(batch, gm.cityManager.current.name, 15, 585);

		batch.end();
	}
}
