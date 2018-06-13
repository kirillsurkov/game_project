#version 330 core

uniform sampler2D tex;

out vec4 outColor;

void main()
{
    outColor = tex();
}