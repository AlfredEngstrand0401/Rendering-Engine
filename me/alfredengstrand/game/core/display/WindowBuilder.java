package me.alfredengstrand.game.core.display;

import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class WindowBuilder {
	
	private static final int DEFAULT_WIDTH = 1280;
	private static final int DEFAULT_HEIGHT = 720;
	private static final int DEFAULT_FPS = 100;
	private static final String DEFAULT_TITLE = "Kingdom Builder Game";
	
	private int width = DEFAULT_WIDTH;
	private int height = DEFAULT_HEIGHT;
	private int fps = DEFAULT_FPS;
	private String title = DEFAULT_TITLE;

	boolean fullscreen;
	boolean vSync;

	public WindowBuilder setFullscreen(boolean fullscreen) {
		this.fullscreen = fullscreen;
		return this;
	}

	public WindowBuilder setVSync(boolean vSync) {
		this.vSync = vSync;
		return this;
	}
	
	public WindowBuilder setWidth(int width) {
		this.width = width;
		return this;
	}
	
	public WindowBuilder setHeight(int height) {
		this.height = height;
		return this;
	}
	
	public WindowBuilder setFps(int fps) {
		this.fps = fps;
		return this;
	}

	public boolean isFullscreen() {
		return fullscreen;
	}

	public boolean isVSync() {
		return vSync;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getFps() {
		return fps;
	}

	public String getTitle() {
		return title;
	}

	public DisplayMode getDisplayMode() {
		if (fullscreen) {
			try {
				Display.setFullscreen(true);
				return Display.getDesktopDisplayMode();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new DisplayMode(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	public ContextAttribs getAttribs() {
		return new ContextAttribs(3, 3).withForwardCompatible(true).withProfileCore(true);
	}

}
