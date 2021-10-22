package me.alfredengstrand.game.core.rendering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.alfredengstrand.game.core.modules.Mesh;
import me.alfredengstrand.game.core.terrain.Terrain;
import me.alfredengstrand.game.gameObjects.Entity;

public class Batch {

	private Map<Mesh, List<Entity>> entities = new HashMap<Mesh, List<Entity>>();
	private List<Terrain> terrains = new ArrayList<Terrain>();

	public void processEntity(Entity entity) {
		List<Entity> batch = entities.get(entity.getMesh());
		if (batch != null) {
			batch.add(entity);
		} else {
			List<Entity> newBatch = new ArrayList<Entity>();
			newBatch.add(entity);
			entities.put(entity.getMesh(), newBatch);
		}
	}

	public void processTerrain(Terrain terrain) {
		terrains.add(terrain);
	}

	public Map<Mesh, List<Entity>> getEntities() {
		return entities;
	}
	
	public List<Terrain> getTerrains(){
		return terrains;
	}
	
	public void cleanup() {
		entities.clear();
		terrains.clear();
	}

}
