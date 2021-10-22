package me.alfredengstrand.game.core.modules;

import me.alfredengstrand.game.core.openglObjects.Vao;

public class Model {
	
	private Vao vao;
	
	public Model(Vao vao) {
		this.vao = vao;
	}
	
	public Vao getVao() {
		return vao;
	}

}
