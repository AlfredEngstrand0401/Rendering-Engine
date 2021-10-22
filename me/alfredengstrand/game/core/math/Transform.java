package me.alfredengstrand.game.core.math;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class Transform {
	
	private Vector3f position;
	private Vector3f rotation;
	private float scale;
	
	public Transform() {
		this.position = new Vector3f(0, 0, 0);
		this.rotation = new Vector3f(0, 0, 0);
		this.scale = 1;
	}
	
	public Transform setPositition(Vector3f position) {
		this.position = position;
		return this;
	}
	
	public Transform setRotation(Vector3f rotation) {
		this.rotation = rotation;
		return this;
	}
	
	public Transform setScale(float scale) {
		this.scale = scale;
		return this;
	}
	
	public void increasePosition(float x, float y, float z) {
		this.position.x += x;
		this.position.y += y;
		this.position.z += z;
	}
	
	public void increaseRotation(float rx, float ry, float rz) {
		this.rotation.x += rx;
		this.rotation.y += ry;
		this.rotation.z += rz;
	}
	
	public void reset() {
		this.position = new Vector3f(0, 0, 0);
		this.rotation = new Vector3f(0, 0, 0);
		this.scale = 1;
	}
	
	public Vector3f getPosition() {
		return position;
	}
	
	public Vector3f getRotation() {
		return rotation;
	}
	
	public float getScale() {
		return scale;
	}
	
	public Matrix4f getMatrix() {
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(position, matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rotation.x), new Vector3f(1, 0, 0), matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rotation.y), new Vector3f(0, 1, 0), matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rotation.z), new Vector3f(0, 0, 1), matrix, matrix);
		Matrix4f.scale(new Vector3f(scale, scale, scale), matrix, matrix);
		return matrix;
	}

}
