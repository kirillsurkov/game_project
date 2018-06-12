#version 330 core

uniform sampler2D positionTexture;
uniform sampler2D normalTexture;
uniform sampler2D colorTexture;
uniform sampler2D depthTexture;

in vec2 uv;
out vec4 outColor;

const vec3 viewPos = vec3(0, 0, -1);
const vec3 lightPos = vec3(0, 10, 0);

void main()
{
    vec3 fragPos = texture(positionTexture, uv).rgb;
    vec3 normal = texture(normalTexture, uv).rgb;

    vec3 viewDir = normalize(viewPos - fragPos);
    vec3 lightDir = normalize(lightPos - fragPos);
    float diffuse = max(dot(normal, lightDir), 0.0);
    outColor = vec4(diffuse * texture(colorTexture, uv).rgb * texture(depthTexture, uv).rgb, 1);
}