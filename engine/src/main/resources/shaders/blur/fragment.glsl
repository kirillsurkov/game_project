#version 330 core

uniform sampler2D colorTexture;
uniform sampler2D glowTexture;

uniform vec2 resolution = vec2(800, 600);

in vec2 uv;

layout (location = 0) out vec3 gGlow;

const int kernelSize = 7;
const float kernel[kernelSize*kernelSize] = float[] (0.006781, 0.006747, 0.006646, 0.006482, 0.006259, 0.005984, 0.005664, 0.006747, 0.006713, 0.006613, 0.006450, 0.006228, 0.005954, 0.005635, 0.006646, 0.006613, 0.006515, 0.006354, 0.006135, 0.005865, 0.005551, 0.006482, 0.006450, 0.006354, 0.006197, 0.005984, 0.005720, 0.005414, 0.006259, 0.006228, 0.006135, 0.005984, 0.005778, 0.005524, 0.005228, 0.005984, 0.005954, 0.005865, 0.005720, 0.005524, 0.005281, 0.004998, 0.005664, 0.005635, 0.005551, 0.005414, 0.005228, 0.004998, 0.004731);
vec3 blur13(sampler2D glowMask, sampler2D image, vec2 uv) {
    vec4 color = vec4(0.0);
    vec2 resolution = textureSize(image, 0);
    float r = 1.5;
    for (int i = 0; i < kernelSize; i++) {
        for (int j = 0; j < kernelSize; j++) {
            float w = kernel[j * kernelSize + i];
            vec2 off1 = uv + r*vec2( i,  j) / resolution;
            vec2 off2 = uv + r*vec2( i, -j) / resolution;
            vec2 off3 = uv + r*vec2(-i,  j) / resolution;
            vec2 off4 = uv + r*vec2(-i, -j) / resolution;
            color += texture2D(glowMask, off1) * texture2D(image, off1) * w;
            if (j != 0) color += texture2D(glowMask, off2) * texture2D(image, off2) * w;
            if (i != 0) color += texture2D(glowMask, off3) * texture2D(image, off3) * w;
            if (j != 0) color += texture2D(glowMask, off4) * texture2D(image, off4) * w;
        }
    }
    return color.rgb;
}

void main() {
    gGlow = blur13(glowTexture, colorTexture, uv).rgb;
}
