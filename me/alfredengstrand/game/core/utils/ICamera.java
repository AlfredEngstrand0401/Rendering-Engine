package me.alfredengstrand.game.core.utils;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public interface ICamera {
	
	public Matrix4f getProjectionMatrix();
	public Matrix4f getViewMatrix();
	public Vector3f getPosition();

}
