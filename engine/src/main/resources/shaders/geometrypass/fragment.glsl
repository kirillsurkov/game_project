#version 330 core

uniform sampler2D uTexture;

layout (location = 0) out vec4 gColor;
layout (location = 1) out vec3 gPosition;
layout (location = 2) out vec3 gNormal;

in vec3 fPosition;
in vec3 fNormal;
in vec2 fTexCoord;

void main()
{
    gColor = vec4(texture2D(uTexture, fTexCoord).rgb, 1);
    gPosition = fPosition;
    gNormal = normalize(fNormal);
}
