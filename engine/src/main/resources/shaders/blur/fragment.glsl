#version 330 core

uniform sampler2D colorTexture;
uniform bool firstPass;

uniform vec2 resolution = vec2(800, 600);

in vec2 uv;

layout (location = 0) out vec4 gGlow;

const float kernel[13] = float[] (0.061539, 0.060915, 0.059078, 0.056140, 0.052270, 0.047683, 0.042620, 0.037326, 0.032028, 0.026928, 0.022182, 0.017903, 0.014158);

void main() {
    vec2 step = vec2(firstPass, !firstPass) / resolution;
    float r = 1;
    vec4 res = texture2D(colorTexture, uv) * kernel[0];
    for (int i = 1; i < kernel.length(); i++) {
        float w = kernel[i];
        vec2 offset = i*r*step;
        vec4 frag1 = texture2D(colorTexture, uv + offset);
        vec4 frag2 = texture2D(colorTexture, uv - offset);
        res += w * frag1 * frag1.a;
        res += w * frag2 * frag2.a;
    }
    gGlow = vec4(res.rgb, res.a > 0 ? 1 : 0);
}
