#version 330

in vec2 pass_textureCoordinates;
in vec3 surfaceNormal;
in vec3 toLightVector;

out vec4 out_colour;

uniform sampler2D diffuseTexture;
uniform sampler2D extraMap;

const vec3 lightColour = vec3(2, 2, 2);

void main(void) {

	vec3 unitNormal = normalize(surfaceNormal);
	vec3 unitLightVector = normalize(toLightVector);

	float nDotl = dot(unitNormal, unitLightVector);
	float brightness = max(nDotl, 0.4);
	vec3 diffuse = brightness * lightColour;

	out_colour = vec4(diffuse, 1.0) * texture(diffuseTexture, pass_textureCoordinates);

}
