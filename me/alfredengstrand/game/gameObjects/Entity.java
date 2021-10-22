package me.alfredengstrand.game.gameObjects;

import me.alfredengstrand.game.core.math.Transform;
import me.alfredengstrand.game.core.modules.Mesh;

public class Entity {
	
	private Mesh mesh;
	private Transform transform;

	public Entity(Mesh mesh, Transform transform) {
		this.mesh = mesh;
		this.transform = transform;
	}

	public Mesh getMesh() {
		return mesh;
	}

	public Transform getTransform() {
		return transform;
	}
}
