package me.alfredengstrand.game.core.rendering.entityRenderer;

import me.alfredengstrand.game.core.shaders.ShaderProgram;
import me.alfredengstrand.game.core.shaders.UniformMatrix;
import me.alfredengstrand.game.core.shaders.UniformSampler;
import me.alfredengstrand.game.core.utils.MyFile;

public class EntityShader extends ShaderProgram {

	private static final MyFile VERTEX_FILE = new MyFile("me", "alfredengstrand", "game", "core", "rendering",
			"entityRenderer", "vertexShader.glsl");
	
	private static final MyFile FRAGMENT_FILE = new MyFile("me", "alfredengstrand", "game", "core", "rendering",
			"entityRenderer", "fragmentShader.glsl");
	
	protected UniformMatrix projectionMatrix = new UniformMatrix("projectionMatrix");
	protected UniformMatrix viewMatrix = new UniformMatrix("viewMatrix");
	protected UniformMatrix transformationMatrix = new UniformMatrix("transformationMatrix");
	
	protected UniformSampler diffuseMap = new UniformSampler("diffuseTexture");
	protected UniformSampler extraMap = new UniformSampler("extraMap");

	public EntityShader() {
		super(VERTEX_FILE, FRAGMENT_FILE, "position", "textureCoordinates", "normal");
		super.storeAllUniformLocations(projectionMatrix, viewMatrix, transformationMatrix);
	}
	
	private void connectTextureUnits() {
		super.start();
		diffuseMap.loadTexUnit(0);
		extraMap.loadTexUnit(1);
	}

}
