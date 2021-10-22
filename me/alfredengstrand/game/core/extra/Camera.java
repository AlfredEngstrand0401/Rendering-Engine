package me.alfredengstrand.game.core.extra;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import me.alfredengstrand.game.core.display.Window;
import me.alfredengstrand.game.core.utils.ICamera;

public class Camera implements ICamera {
	
	private static final float FOV = 60;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000;
	private static final float MOVEMENT_SPEED = 10;
	
	private Matrix4f projectionMatrix = new Matrix4f();
	private Matrix4f viewMatrix = new Matrix4f();
	
	private Vector3f position = new Vector3f(0, 10, 0);

	private float pitch = 0;
	private float yaw = 0;
	private float roll;

	public Camera(){
		Mouse.setGrabbed(true);
		createProjectionMatrix();
	}

	public void move(){
		calculateYaw();
		calculatePitch();
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			moveForward();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			moveBackward();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			moveRight();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			moveLeft();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			position.y += 0.1f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			position.y -= 0.1f;
		}
		updateViewMatrix();
	}
	
	private void calculateYaw() {
		float yawChange = Mouse.getDX() * 0.1f;
		this.yaw += yawChange;
	}
	
	private void calculatePitch() {
		float pitchChange = Mouse.getDY() * 0.1f;
		this.pitch -= pitchChange;
	}
	
	private void moveForward() {
		float dx = (float) Math.sin(Math.toRadians(-yaw)) * Window.getFrameTimeSeconds() * MOVEMENT_SPEED;
		float dz = (float) Math.cos(Math.toRadians(-yaw)) * Window.getFrameTimeSeconds() * MOVEMENT_SPEED;
		position.x -= dx;
		position.z -= dz;
	}
	
	private void moveBackward() {
		float dx = (float) Math.sin(Math.toRadians(-yaw)) * Window.getFrameTimeSeconds() * MOVEMENT_SPEED;
		float dz = (float) Math.cos(Math.toRadians(-yaw)) * Window.getFrameTimeSeconds() * MOVEMENT_SPEED;
		position.x += dx;
		position.z += dz;
	}
	
	private void moveRight() {
		float dx = (float) Math.sin(Math.toRadians(-yaw + 90)) * Window.getFrameTimeSeconds() * MOVEMENT_SPEED;
		float dz = (float) Math.cos(Math.toRadians(-yaw + 90)) * Window.getFrameTimeSeconds() * MOVEMENT_SPEED;
		position.x += dx;
		position.z += dz;
	}
	
	private void moveLeft() {
		float dx = (float) Math.sin(Math.toRadians(-yaw - 90)) * Window.getFrameTimeSeconds() * MOVEMENT_SPEED;
		float dz = (float) Math.cos(Math.toRadians(-yaw - 90)) * Window.getFrameTimeSeconds() * MOVEMENT_SPEED;
		position.x += dx;
		position.z += dz;
	}

	@Override
	public Vector3f getPosition() {
		return position;
	}

	@Override
	public Matrix4f getViewMatrix() {
		return viewMatrix;
	}

	@Override
	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}
	
	public void invertPitch(){
		this.pitch = -pitch;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
	
	private void updateViewMatrix() {
		viewMatrix.setIdentity();
		Matrix4f.rotate((float) Math.toRadians(pitch), new Vector3f(1, 0, 0), viewMatrix,
				viewMatrix);
		Matrix4f.rotate((float) Math.toRadians(yaw), new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
		Vector3f negativeCameraPos = new Vector3f(-position.x,-position.y,-position.z);
		Matrix4f.translate(negativeCameraPos, viewMatrix, viewMatrix);
	}

	private Matrix4f createProjectionMatrix(){
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))));
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;
	
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
		projectionMatrix.m33 = 0;
		return projectionMatrix;
	}

}
