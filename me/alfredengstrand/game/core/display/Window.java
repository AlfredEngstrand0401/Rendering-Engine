package me.alfredengstrand.game.core.display;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.PixelFormat;

public class Window {

	WindowBuilder settings;
	
	private static long lastFrameTime;
	private static float delta;

	public Window(WindowBuilder settings) {
		this.settings = settings;
		launch();
	}

	private void launch() {
		try {
			Display.setDisplayMode(settings.getDisplayMode());
			Display.setTitle(settings.getTitle());
			Display.create(new PixelFormat().withSamples(4), settings.getAttribs());
		} catch (Exception e) {
			System.out.println("Failed to create display!");
		}
		lastFrameTime = getCurrentTime();
	}

	public void update() {
		Display.sync(settings.getFps());
		Display.update();
		long currentFrameTime = getCurrentTime();
		delta = (currentFrameTime - lastFrameTime)/1000f;
		lastFrameTime = currentFrameTime;
	}

	public void destroy() {
		Display.destroy();
	}

	public boolean isCloseRequested() {
		return Display.isCloseRequested();
	}
	
	public static float getFrameTimeSeconds(){
		return delta;
	}
	
	private static long getCurrentTime(){
		return Sys.getTime()*1000/Sys.getTimerResolution();
	}

}
