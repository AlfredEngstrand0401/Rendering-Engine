package me.alfredengstrand.game;

import me.alfredengstrand.game.core.RenderEngine;
import me.alfredengstrand.game.core.extra.Camera;
import me.alfredengstrand.game.core.material.TextureUtils;
import me.alfredengstrand.game.core.terrain.Terrain;
import me.alfredengstrand.game.core.utils.MyFile;

public class Main {

	public static void main(String[] args) {

		RenderEngine engine = new RenderEngine();
		
		Terrain terrain = new Terrain(-0.5f, -0.5f, TextureUtils.loadTexture(new MyFile("resources", "terrain", "default_grass.png")));

		Camera camera = (Camera) engine.getCamera();

		while (!engine.getWindow().isCloseRequested()) {
			camera.move();
			engine.getBatch().processTerrain(terrain);
			engine.getRenderer().render();
			engine.getWindow().update();
		}
		engine.getWindow().destroy();
	}

}
