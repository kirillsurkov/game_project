#version 330 core

uniform sampler2D colorTexture;
uniform sampler2D positionTexture;
uniform sampler2D normalTexture;
uniform sampler2D glowTexture;
uniform sampler2D glowTextureBlur;
uniform sampler2D depthTexture;

in vec2 uv;

out vec4 outColor;

const vec3 lightPos = vec3(0, -10, 0);

void main()
{
    vec3 fragPos = texture2D(positionTexture, uv).xyz;
    vec3 normal = texture2D(normalTexture, uv).xyz;
    float glow = texture2D(colorTexture, uv).a;
    vec3 glowColor = texture2D(glowTextureBlur, uv).rgb;

    vec3 lightVector = lightPos - fragPos;
    vec3 lightDir = normalize(lightVector);
    float diffuse = 250 * max(dot(normal, lightDir), 0.0) / pow(length(lightVector), 2);

    float ambient = 0.1;

    outColor = vec4(glowColor + min(glow + ambient + diffuse * (1 - ambient), 1) * texture2D(colorTexture, uv).rgb, 1);
}
