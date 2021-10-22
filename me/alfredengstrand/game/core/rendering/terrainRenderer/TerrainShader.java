package me.alfredengstrand.game.core.rendering.terrainRenderer;

import me.alfredengstrand.game.core.shaders.ShaderProgram;
import me.alfredengstrand.game.core.shaders.UniformMatrix;
import me.alfredengstrand.game.core.utils.MyFile;

public class TerrainShader extends ShaderProgram {

	private static final MyFile VERTEX_FILE = new MyFile("me", "alfredengstrand", "game", "core", "rendering",
			"terrainRenderer", "vertexShader.glsl");

	private static final MyFile FRAGMENT_FILE = new MyFile("me", "alfredengstrand", "game", "core", "rendering",
			"terrainRenderer", "fragmentShader.glsl");

	protected UniformMatrix projectionMatrix = new UniformMatrix("projectionMatrix");
	protected UniformMatrix viewMatrix = new UniformMatrix("viewMatrix");
	protected UniformMatrix transformationMatrix = new UniformMatrix("transformationMatrix");

	public TerrainShader() {
		super(VERTEX_FILE, FRAGMENT_FILE, "position", "textureCoordinates", "normal");
		super.storeAllUniformLocations(projectionMatrix, viewMatrix, transformationMatrix);
	}

}
