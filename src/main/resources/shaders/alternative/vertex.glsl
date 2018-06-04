#version 150 core

uniform float uScale;
uniform vec3 uOffset;
uniform vec3 uColor;
uniform mat4 uMVP;

in vec3 position;
out vec3 color;

void main()
{
    color = uColor;
    gl_Position = uMVP * vec4(position, 1.0);
}