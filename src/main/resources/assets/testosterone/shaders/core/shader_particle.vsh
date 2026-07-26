#version 150

in vec3 Position;
in vec2 UV0;
in vec4 Color;
in ivec2 UV2;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;
uniform mat4 ModelViewMatInverse;
uniform float GameTime;
uniform vec3 cameraPos;

out vec2  texCoord0;
out vec4  vertexColor;
out vec2  lightMapCoord;
out float gameTime;
out vec3  worldPos;

void main() {
    int id = gl_VertexID;

//    vec3 basePos = ((id & 1) == 1)
//    ? prevPos - cameraPos
//    : Position;
    vec3 basePos = Position;

    vec4 viewPos = ModelViewMat * vec4(basePos, 1.0);
    gl_Position  = ProjMat * viewPos;

    vec4 wp = ModelViewMatInverse * viewPos;
    worldPos = wp.xyz;

    texCoord0     = UV0;
    vertexColor   = Color;
    lightMapCoord = vec2(UV2) / 65535.0;
    gameTime      = GameTime;
}