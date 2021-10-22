package me.alfredengstrand.game.core.modules;

import me.alfredengstrand.game.core.material.Material;

public class Mesh {
	
	private Model model;
	private Material material;
	
	public Mesh(Model model, Material material) {
		this.model = model;
		this.material = material;
	}

	public Model getModel() {
		return model;
	}
	
	public Material getMaterial() {
		return material;
	}

}
