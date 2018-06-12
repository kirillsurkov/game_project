#version 330 core

uniform vec3 uColor;

in vec2 uv;
out vec4 outColor;

void main()
{
    outColor = vec4(uColor + vec3(uv, 0), 1.0);
}
