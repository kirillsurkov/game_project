#version 330 core

uniform mat4 uMVP;

in vec3 position;
in vec3 normal;
in vec2 texCoord;
out vec2 uv;

void main()
{
    uv = texCoord;
    gl_Position = uMVP * vec4(position, 1.0);
}