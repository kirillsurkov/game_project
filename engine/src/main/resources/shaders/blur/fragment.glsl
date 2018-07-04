#version 330 core

#define count 13

uniform sampler2D colorTexture;
uniform bool firstPass;
uniform vec2 resolution;
uniform float kernel[count];

in vec2 uv;

layout (location = 0) out vec4 gGlow;

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
    gGlow = vec4(res.rgb, 1.5*pow(res.a, 0.25));
}
