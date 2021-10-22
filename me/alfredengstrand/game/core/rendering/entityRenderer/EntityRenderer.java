package me.alfredengstrand.game.core.rendering.entityRenderer;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import me.alfredengstrand.game.core.modules.Mesh;
import me.alfredengstrand.game.core.openglObjects.Vao;
import me.alfredengstrand.game.core.utils.ICamera;
import me.alfredengstrand.game.gameObjects.Entity;

public class EntityRenderer {
	
	EntityShader shader;
	ICamera camera;
	
	public EntityRenderer(ICamera camera) {
		this.shader = new EntityShader();
		this.camera = camera;
		loadProjectionMatrix();
	}
	
	private void loadProjectionMatrix() {
		shader.start();
		shader.projectionMatrix.loadMatrix(camera.getProjectionMatrix());
		shader.stop();
	}
	
	public void render(Map<Mesh, List<Entity>> entities) {
		for(Mesh mesh : entities.keySet()) {
			bind(mesh.getModel().getVao());
			for(Entity entity : entities.get(mesh)) {
				prepare(entity);
				GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getModel().getVao().getIndexCount(), GL11.GL_UNSIGNED_INT, 0);
			}
			unbind(mesh.getModel().getVao());
		}
	}
	
	private void bind(Vao vao) {
		shader.start();
		vao.bind(0, 1, 2);
	}
	
	private void prepare(Entity entity) {
		shader.viewMatrix.loadMatrix(camera.getViewMatrix());
		shader.transformationMatrix.loadMatrix(entity.getTransform().getMatrix());
		entity.getMesh().getMaterial().getTexture().bindToUnit(0);
		if(entity.getMesh().getMaterial().hasExtraMap()) {
			entity.getMesh().getMaterial().getExtraMap().bindToUnit(1);
		}
	}
	
	private void unbind(Vao vao) {
		vao.unbind(0, 1, 2);
		shader.stop();
	}
	
	public void cleanup() {
		shader.cleanUp();
	}

}
