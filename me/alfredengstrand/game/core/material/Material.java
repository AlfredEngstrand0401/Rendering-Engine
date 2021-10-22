package me.alfredengstrand.game.core.material;

public class Material {

	private Texture2D texture;
	private Texture2D extraMap;

	public Material(Texture2D texture) {
		this.texture = texture;
	}

	public Material setExtraMap(Texture2D extraMap) {
		this.extraMap = extraMap;
		return this;
	}

	public void addExtraMap(Texture2D extraMap) {
		this.extraMap = extraMap;
	}

	public boolean hasExtraMap() {
		return extraMap != null;
	}

	public Texture2D getTexture() {
		return texture;
	}

	public Texture2D getExtraMap() {
		if (hasExtraMap()) {
			return extraMap;
		} else {
			return null;
		}
	}

}
