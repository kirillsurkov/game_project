#version 330 core

uniform sampler2D positionTexture;
uniform sampler2D normalTexture;
uniform sampler2D colorTexture;
uniform sampler2D depthTexture;

in vec2 uv;
out vec4 outColor;

const vec3 lightPos = vec3(0, -10, 10);

void main()
{
    vec3 fragPos = texture(positionTexture, uv).xyz;
    vec3 normal = texture(normalTexture, uv).xyz;

    vec3 lightDir = normalize(lightPos - fragPos);
    float diffuse = max(dot(normal, lightDir), 0.0);

    outColor = diffuse * texture(colorTexture, uv);//*vec4(fragPos*10, 1);// * vec4(texture(colorTexture, uv).rgb * (0.5 + texture(depthTexture, uv).rgb * 0.5), 1);
}