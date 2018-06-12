#version 330 core

uniform vec3 uColor;

layout (location = 0) out vec4 gColor;
layout (location = 1) out vec3 gPosition;
layout (location = 2) out vec3 gNormal;

in vec3 fPosition;
in vec3 fNormal;
in vec2 fTexCoord;

void main()
{
    gPosition = fPosition;
    gNormal = fNormal;
    gColor = vec4(uColor + vec3(fTexCoord, 0), 1.0);
}
