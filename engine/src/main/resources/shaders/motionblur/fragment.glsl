#version 330 core

#define count 4

uniform sampler2D colorTexture[count];
uniform float kernel[count];
uniform bool enabled;

in vec2 uv;

out vec4 gColor;

void main() {
    if (enabled) {
        gColor = vec4(0);
        gColor += kernel[0] * texture2D(colorTexture[0], uv);
        gColor += kernel[1] * texture2D(colorTexture[1], uv);
        gColor += kernel[2] * texture2D(colorTexture[2], uv);
        gColor += kernel[3] * texture2D(colorTexture[3], uv);
    } else {
        gColor = texture2D(colorTexture[0], uv);
    }
}