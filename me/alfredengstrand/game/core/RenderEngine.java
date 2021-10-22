package me.alfredengstrand.game.core;

import me.alfredengstrand.game.core.display.Window;
import me.alfredengstrand.game.core.display.WindowBuilder;
import me.alfredengstrand.game.core.extra.Camera;
import me.alfredengstrand.game.core.rendering.Batch;
import me.alfredengstrand.game.core.rendering.MasterRenderer;
import me.alfredengstrand.game.core.utils.ICamera;

public class RenderEngine {
	
	Window window;
	ICamera camera;
	Batch batch;
	MasterRenderer renderer;
	
	public RenderEngine() {
		init();
	}
	
	public Window getWindow() {
		return window;
	}
	
	public ICamera getCamera() {
		return camera;
	}
	
	public Batch getBatch() {
		return batch;
	}
	
	public MasterRenderer getRenderer() {
		return renderer;
	}
	
	public void destroy() {
		renderer.cleanup();
		window.destroy();
	}
	
	private void init() {
		this.window = new Window(new WindowBuilder());
		this.camera = new Camera();
		this.batch = new Batch();
		this.renderer = new MasterRenderer(camera, batch);
	}

}
