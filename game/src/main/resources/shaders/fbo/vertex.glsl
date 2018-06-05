#version 150 core

in vec3 position;
in vec2 texCoord;

out vec2 uv;

void main()
{
    uv = texCoord;
    gl_Position = vec4(position, 1.0);
}