package me.alfredengstrand.game.core.rendering.terrainRenderer;

import java.util.List;

import org.lwjgl.opengl.GL11;

import me.alfredengstrand.game.core.openglObjects.Vao;
import me.alfredengstrand.game.core.terrain.Terrain;
import me.alfredengstrand.game.core.utils.ICamera;

public class TerrainRenderer {
	
	TerrainShader shader;
	ICamera camera;
	
	public TerrainRenderer(ICamera camera) {
		this.shader = new TerrainShader();
		this.camera = camera;
		loadProjectionMatrix();
	}
	
	private void loadProjectionMatrix() {
		shader.start();
		shader.projectionMatrix.loadMatrix(camera.getProjectionMatrix());
		shader.stop();
	}
	
	public void render(List<Terrain> terrains) {
		for(Terrain terrain : terrains) {
			bind(terrain.getVao());
			prepare(terrain);
			GL11.glDrawElements(GL11.GL_TRIANGLES, terrain.getVao().getIndexCount(), GL11.GL_UNSIGNED_INT, 0);
			unbind(terrain.getVao());
		}
	}
	
	private void bind(Vao vao) {
		shader.start();
		vao.bind(0, 1, 2);
	}
	
	private void prepare(Terrain terrain) {
		shader.viewMatrix.loadMatrix(camera.getViewMatrix());
		shader.transformationMatrix.loadMatrix(terrain.getTransform().getMatrix());
		terrain.getTexture().bindToUnit(0);
	}
	
	private void unbind(Vao vao) {
		vao.unbind(0, 1, 2);
		shader.stop();
	}
	
	public void cleanup() {
		shader.cleanUp();
	}

}
