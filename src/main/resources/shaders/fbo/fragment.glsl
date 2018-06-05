#version 150 core

uniform sampler2D fboTexture;

in vec2 uv;
out vec4 outColor;

void main()
{
    outColor = vec4(uv, 0, 0) + texture(fboTexture, uv);
}