package me.alfredengstrand.game.core.rendering;

import org.lwjgl.opengl.GL11;

import me.alfredengstrand.game.core.rendering.entityRenderer.EntityRenderer;
import me.alfredengstrand.game.core.rendering.terrainRenderer.TerrainRenderer;
import me.alfredengstrand.game.core.utils.ICamera;

public class MasterRenderer {
	
	Batch batch;
	EntityRenderer entityRenderer;
	TerrainRenderer terrainRenderer;
	
	public MasterRenderer(ICamera camera, Batch batch) {
		this.batch = batch;
		entityRenderer = new EntityRenderer(camera);
		terrainRenderer = new TerrainRenderer(camera);
	}
	
	private void prepare() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(1, 1, 1, 1);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
	}
	
	public void render() {
		prepare();
		entityRenderer.render(batch.getEntities());
		terrainRenderer.render(batch.getTerrains());
		clear();
	}
	
	private void clear() {
		batch.cleanup();
	}
	
	public void cleanup() {
		entityRenderer.cleanup();
		terrainRenderer.cleanup();
	}

}
