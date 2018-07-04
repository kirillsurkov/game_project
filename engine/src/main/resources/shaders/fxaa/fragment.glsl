#version 330 core

uniform sampler2D colorTexture;
uniform vec2 resolution;
uniform bool enabled;

in vec2 uv;
in vec2 rgbNW;
in vec2 rgbNE;
in vec2 rgbSW;
in vec2 rgbSE;
in vec2 rgbM;

layout (location = 0) out vec4 gColor;

#define FXAA_REDUCE_MIN   (1.0 / 128.0)
#define FXAA_REDUCE_MUL   (1.0 /   8.0)
#define FXAA_SPAN_MAX 8.0

vec4 fxaa(sampler2D tex, vec2 resolution,
            vec2 v_rgbNW, vec2 v_rgbNE,
            vec2 v_rgbSW, vec2 v_rgbSE,
            vec2 v_rgbM, vec2 uv) {
    vec2 inverseVP = 1.0 / resolution;
    vec3 rgbNW = texture2D(tex, v_rgbNW).rgb;
    vec3 rgbNE = texture2D(tex, v_rgbNE).rgb;
    vec3 rgbSW = texture2D(tex, v_rgbSW).rgb;
    vec3 rgbSE = texture2D(tex, v_rgbSE).rgb;
    vec3 rgbM = texture2D(tex, v_rgbM).rgb;
    vec3 luma = vec3(0.299, 0.587, 0.114);
    float lumaNW = dot(rgbNW, luma);
    float lumaNE = dot(rgbNE, luma);
    float lumaSW = dot(rgbSW, luma);
    float lumaSE = dot(rgbSE, luma);
    float lumaM  = dot(rgbM,  luma);
    float lumaMin = min(lumaM, min(min(lumaNW, lumaNE), min(lumaSW, lumaSE)));
    float lumaMax = max(lumaM, max(max(lumaNW, lumaNE), max(lumaSW, lumaSE)));

    vec2 dir;
    dir.x = -((lumaNW + lumaNE) - (lumaSW + lumaSE));
    dir.y =  ((lumaNW + lumaSW) - (lumaNE + lumaSE));

    float dirReduce = max((lumaNW + lumaNE + lumaSW + lumaSE) * (0.25 * FXAA_REDUCE_MUL), FXAA_REDUCE_MIN);

    float rcpDirMin = 1.0 / (min(abs(dir.x), abs(dir.y)) + dirReduce);
    dir = min(vec2(FXAA_SPAN_MAX, FXAA_SPAN_MAX), max(vec2(-FXAA_SPAN_MAX, -FXAA_SPAN_MAX), dir * rcpDirMin)) * inverseVP;

    vec4 rgbaA = 0.5 * (texture2D(tex, uv - dir / 6.0) + texture2D(tex, uv + dir / 6.0));
    vec4 rgbaB = rgbaA * 0.5 + 0.25 * (texture2D(tex, uv - dir * 0.5) + texture2D(tex, uv + dir * 0.5));

    float lumaB = dot(rgbaB.rgb, luma);

    if ((lumaB < lumaMin) || (lumaB > lumaMax)) return rgbaA;
    return rgbaB;
}

void main() {
    if (enabled) {
        gColor = fxaa(colorTexture, resolution, rgbNW, rgbNE, rgbSW, rgbSE, rgbM, uv);
    } else {
        gColor = texture2D(colorTexture, uv);
    }
}