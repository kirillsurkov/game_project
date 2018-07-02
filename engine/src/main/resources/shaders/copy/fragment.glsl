#version 330 core

uniform sampler2D fromTexture;
layout (location = 0) out vec4 toTexture;

in vec2 uv;

void main() {
    toTexture = texture2D(fromTexture, uv);
}
