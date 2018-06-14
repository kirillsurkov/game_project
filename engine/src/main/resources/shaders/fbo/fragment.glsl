#version 330 core

uniform sampler2D colorTexture;
uniform sampler2D positionTexture;
uniform sampler2D normalTexture;
uniform sampler2D glowTexture;
uniform sampler2D depthTexture;

in vec2 uv;

out vec4 outColor;

const vec3 lightPos = vec3(0, -10, 0);

// FXAA
#define FXAA_REDUCE_MIN   (1.0/ 128.0)
#define FXAA_REDUCE_MUL   (1.0 / 8.0)
#define FXAA_SPAN_MAX 8.0
vec4 fxaa(sampler2D tex, vec2 uv, vec2 resolution,
            vec2 v_rgbNW, vec2 v_rgbNE,
            vec2 v_rgbSW, vec2 v_rgbSE,
            vec2 v_rgbM) {
    vec4 color;
    vec2 inverseVP = 1.0 / resolution;
    vec3 rgbNW = texture2D(tex, v_rgbNW).xyz;
    vec3 rgbNE = texture2D(tex, v_rgbNE).xyz;
    vec3 rgbSW = texture2D(tex, v_rgbSW).xyz;
    vec3 rgbSE = texture2D(tex, v_rgbSE).xyz;
    vec4 texColor = texture2D(tex, v_rgbM);
    vec3 rgbM  = texColor.xyz;
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

    float dirReduce = max((lumaNW + lumaNE + lumaSW + lumaSE) *
                          (0.25 * FXAA_REDUCE_MUL), FXAA_REDUCE_MIN);

    float rcpDirMin = 1.0 / (min(abs(dir.x), abs(dir.y)) + dirReduce);
    dir = min(vec2(FXAA_SPAN_MAX, FXAA_SPAN_MAX),
              max(vec2(-FXAA_SPAN_MAX, -FXAA_SPAN_MAX),
              dir * rcpDirMin)) * inverseVP;

    vec3 rgbA = 0.5 * (
        texture2D(tex, uv + dir * (1.0 / 3.0 - 0.5)).xyz +
        texture2D(tex, uv + dir * (2.0 / 3.0 - 0.5)).xyz);
    vec3 rgbB = rgbA * 0.5 + 0.25 * (
        texture2D(tex, uv + dir * -0.5).xyz +
        texture2D(tex, uv + dir * 0.5).xyz);

    float lumaB = dot(rgbB, luma);
    if ((lumaB < lumaMin) || (lumaB > lumaMax))
        color = vec4(rgbA, texColor.a);
    else
        color = vec4(rgbB, texColor.a);
    return color;
}

vec4 FXAA(sampler2D tex, vec2 uv) {
    vec2 resolution = textureSize(tex, 0);
    vec2 rgbNW=uv+(vec2(-1, -1)/resolution);
    vec2 rgbNE=uv+(vec2( 1, -1)/resolution);
    vec2 rgbSW=uv+(vec2(-1,  1)/resolution);
    vec2 rgbSE=uv+(vec2( 1,  1)/resolution);
    vec2 rgbM=uv;
    return fxaa(tex, uv, resolution, rgbNW, rgbNE, rgbSW, rgbSE, rgbM);
}
// FXAA

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

void main()
{
    vec3 fragPos = texture2D(positionTexture, uv).xyz;
    vec3 normal = texture2D(normalTexture, uv).xyz;
    float glow = FXAA(glowTexture, uv).r;

    vec3 lightVector = lightPos - fragPos;
    vec3 lightDir = normalize(lightVector);
    float diffuse = 250 * max(dot(normal, lightDir), 0.0) / pow(length(lightVector), 2);

    float ambient = 0.1;

//    outColor = vec4(vec3(glow), 0);//(glow + glow2) + (ambient + diffuse * (1 - ambient)) * texture(colorTexture, uv);//*vec4(fragPos*10, 1);// * vec4(texture(colorTexture, uv).rgb * (0.5 + texture(depthTexture, uv).rgb * 0.5), 1);
    vec3 res = blur13(glowTexture, colorTexture, uv);

    outColor = vec4(res + min(glow + ambient + diffuse * (1 - ambient), 1) * FXAA(colorTexture, uv).rgb, 1);
}
