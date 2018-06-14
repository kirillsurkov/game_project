#version 330 core

uniform sampler2D uDiffuse;
uniform sampler2D uGlow;

layout (location = 0) out vec4 gColor;
layout (location = 1) out vec3 gPosition;
layout (location = 2) out vec3 gNormal;
layout (location = 3) out vec3 gGlow;

in vec3 fPosition;
in vec3 fNormal;
in vec2 fTexCoord;

void main()
{
    gColor = vec4(texture2D(uDiffuse, fTexCoord).rgb, 1);
    gPosition = fPosition;
    gNormal = normalize(fNormal);
    gGlow = texture2D(uGlow, fTexCoord).rgb;
}
