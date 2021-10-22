package me.alfredengstrand.game.core.material;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

public class Texture2D {
	
	private int id;
	
	public Texture2D(int id) {
		this.id = id;
	}
	
	public void bindToUnit(int unit) {
		GL13.glActiveTexture(GL13.GL_TEXTURE0 + unit);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
	}

}
