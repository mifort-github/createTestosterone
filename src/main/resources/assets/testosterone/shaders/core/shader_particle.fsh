#version 150

uniform sampler2D Sampler0;
uniform sampler2D Sampler2;

uniform vec4  ColorModulator;
uniform float FogStart;
uniform float FogEnd;
uniform vec4  FogColor;
uniform float GameTime;

in vec2  texCoord0;
in vec4  vertexColor;
in vec2  lightMapCoord;
in float gameTime;
in vec3  worldPos;

out vec4 fragColor;

void main() {
    // vertexColor.r carries the per-face shade baked in CylinderGeometry
    float shade = vertexColor.r;

    // Animated hue — cycles over time, varies by height (worldPos.y)
    float hue  = fract(gameTime * 0.15 + worldPos.y * 0.4);
    vec4  K    = vec4(1.0, 2.0/3.0, 1.0/3.0, 3.0);
    vec3  hsvP = abs(fract(vec3(hue) + K.xyz) * 6.0 - K.www);
    vec3  col  = mix(K.xxx, clamp(hsvP - K.xxx, 0.0, 1.0), 0.9);

    // Apply per-face shading
    col *= shade;

    // Lightmap
    col *= texture(Sampler2, lightMapCoord).rgb;

    // Fog
    float fogDist   = gl_FragCoord.z / gl_FragCoord.w;
    float fogFactor = clamp((FogEnd - fogDist) / (FogEnd - FogStart), 0.0, 1.0);
    col = mix(FogColor.rgb, col, fogFactor);


//    fragColor = vec4(texCoord0.x, texCoord0.y, 0., 1.);
    fragColor = vec4(1., 1., 1., 1.);
}
