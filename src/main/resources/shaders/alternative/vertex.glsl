#version 150 core

uniform float uScale;
uniform vec3 uOffset;
uniform vec3 uColor;

in vec2 position;
out vec3 color;

void main()
{
    color = uColor;
    gl_Position = vec4(uScale * (position + uOffset.xy), 0.0, 1.0);
}