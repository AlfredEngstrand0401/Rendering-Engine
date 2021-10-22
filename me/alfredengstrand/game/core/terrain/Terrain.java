package me.alfredengstrand.game.core.terrain;

import org.lwjgl.util.vector.Vector3f;

import me.alfredengstrand.game.core.OBJConverter.ModelData;
import me.alfredengstrand.game.core.material.Texture2D;
import me.alfredengstrand.game.core.math.Transform;
import me.alfredengstrand.game.core.openglObjects.Vao;

public class Terrain {
	
	private static final int SIZE = 500;
	private static final int VERTEX_COUNT = 128;
	
	private float x;
	private float z;
	private Texture2D texture;
	private Vao vao;
	private Transform transform;
	
	public Terrain(float x, float z, Texture2D texture) {
		this.x = x;
		this.z = z;
		this.texture = texture;
		this.transform = new Transform().setPositition(new Vector3f(x * SIZE, 0, z * SIZE));
		this.vao = generateVao();
	}
	
	private Vao generateVao() {
		int count = VERTEX_COUNT * VERTEX_COUNT;
		float[] vertices = new float[count * 3];
		float[] normals = new float[count * 3];
		float[] textureCoords = new float[count*2];
		int[] indices = new int[6*(VERTEX_COUNT-1)*(VERTEX_COUNT-1)];
		int vertexPointer = 0;
		for(int i=0;i<VERTEX_COUNT;i++){
			for(int j=0;j<VERTEX_COUNT;j++){
				vertices[vertexPointer*3] = (float)j/((float)VERTEX_COUNT - 1) * SIZE;
				vertices[vertexPointer*3+1] = 0;
				vertices[vertexPointer*3+2] = (float)i/((float)VERTEX_COUNT - 1) * SIZE;
				normals[vertexPointer*3] = 0;
				normals[vertexPointer*3+1] = 1;
				normals[vertexPointer*3+2] = 0;
				textureCoords[vertexPointer*2] = (float)j/((float)VERTEX_COUNT - 1);
				textureCoords[vertexPointer*2+1] = (float)i/((float)VERTEX_COUNT - 1);
				vertexPointer++;
			}
		}
		int pointer = 0;
		for(int gz=0;gz<VERTEX_COUNT-1;gz++){
			for(int gx=0;gx<VERTEX_COUNT-1;gx++){
				int topLeft = (gz*VERTEX_COUNT)+gx;
				int topRight = topLeft + 1;
				int bottomLeft = ((gz+1)*VERTEX_COUNT)+gx;
				int bottomRight = bottomLeft + 1;
				indices[pointer++] = topLeft;
				indices[pointer++] = bottomLeft;
				indices[pointer++] = topRight;
				indices[pointer++] = topRight;
				indices[pointer++] = bottomLeft;
				indices[pointer++] = bottomRight;
			}
		}
		ModelData data = new ModelData(vertices, textureCoords, normals, indices, 0);
		Vao vao = Vao.create();
		vao.storeData(data.getIndices(), data.getVertexCount(), data.getVertices(), data.getTextureCoords(), data.getNormals());
		return vao;
	}

	public static int getSize() {
		return SIZE;
	}

	public static int getVertexCount() {
		return VERTEX_COUNT;
	}

	public float getX() {
		return x;
	}

	public float getZ() {
		return z;
	}

	public Texture2D getTexture() {
		return texture;
	}

	public Vao getVao() {
		return vao;
	}

	public Transform getTransform() {
		return transform;
	}

}
