#version 330 core

uniform sampler2D colorTexture;
uniform sampler2D glowTexture;

uniform vec2 resolution = vec2(800, 600);

in vec2 uv;

layout (location = 0) out vec4 gColor;
layout (location = 1) out vec3 gGlow;

#define FXAA_REDUCE_MIN   (1.0 / 128.0)
#define FXAA_REDUCE_MUL   (1.0 /   8.0)
#define FXAA_SPAN_MAX 8.0

vec4 fxaa(sampler2D tex, vec2 uv, vec2 resolution,
            vec2 v_rgbNW, vec2 v_rgbNE,
            vec2 v_rgbSW, vec2 v_rgbSE,
            vec2 v_rgbM) {
    vec4 color;
    vec2 inverseVP = 1.0 / resolution;
    vec4 rgbaNW = texture2D(tex, v_rgbNW);
    vec4 rgbaNE = texture2D(tex, v_rgbNE);
    vec4 rgbaSW = texture2D(tex, v_rgbSW);
    vec4 rgbaSE = texture2D(tex, v_rgbSE);
    vec4 rgbaM = texture2D(tex, v_rgbM);
    vec3 luma = vec3(0.299, 0.587, 0.114);
    float lumaNW = dot(rgbaNW.rgb * rgbaNW.a, luma);
    float lumaNE = dot(rgbaNE.rgb * rgbaNE.a, luma);
    float lumaSW = dot(rgbaSW.rgb * rgbaSW.a, luma);
    float lumaSE = dot(rgbaSE.rgb * rgbaSE.a, luma);
    float lumaM  = dot(rgbaM.rgb * rgbaM.a,  luma);
    float lumaMin = min(lumaM, min(min(lumaNW, lumaNE), min(lumaSW, lumaSE)));
    float lumaMax = max(lumaM, max(max(lumaNW, lumaNE), max(lumaSW, lumaSE)));

    vec2 dir;
    dir.x = -((lumaNW + lumaNE) - (lumaSW + lumaSE));
    dir.y =  ((lumaNW + lumaSW) - (lumaNE + lumaSE));

    float dirReduce = max((lumaNW + lumaNE + lumaSW + lumaSE) *
                          (0.25 * FXAA_REDUCE_MUL), FXAA_REDUCE_MIN);

    float rcpDirMin = 1.0 / (min(abs(dir.x), abs(dir.y)) + dirReduce);
    dir = min(vec2(FXAA_SPAN_MAX, FXAA_SPAN_MAX),
              max(vec2(-FXAA_SPAN_MAX, -FXAA_SPAN_MAX),
              dir * rcpDirMin)) * inverseVP;

    vec4 rgbaA = 0.5 * (
        texture2D(tex, uv - dir * 1.0 / 6.0) +
        texture2D(tex, uv + dir * 1.0 / 6.0));
    vec4 rgbaB = rgbaA * 0.5 + 0.25 * (
        texture2D(tex, uv - dir * 0.5) +
        texture2D(tex, uv + dir * 0.5));

    float lumaB = dot(rgbaB.rgb, luma);
    if ((lumaB < lumaMin) || (lumaB > lumaMax))
        color = rgbaA;
    else
        color = rgbaB;
    return color;
}

vec4 FXAA(sampler2D tex, vec2 uv) {
    vec2 rgbNW=uv+(vec2(-1, -1)/resolution);
    vec2 rgbNE=uv+(vec2( 1, -1)/resolution);
    vec2 rgbSW=uv+(vec2(-1,  1)/resolution);
    vec2 rgbSE=uv+(vec2( 1,  1)/resolution);
    vec2 rgbM=uv;
    return fxaa(tex, uv, resolution, rgbNW, rgbNE, rgbSW, rgbSE, rgbM);
}

void main() {
    gColor = FXAA(colorTexture, uv);
}