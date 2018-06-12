#version 330 core

uniform mat4 uMVP;

in vec3 position;
in vec3 normal;
in vec2 texCoord;

out vec3 fPosition;
out vec3 fNormal;
out vec2 fTexCoord;

void main()
{
    fPosition = (uMVP * vec4(position, 1.0)).xyz;
    fNormal = (uMVP * vec4(normal, 1.0)).xyz;
    fTexCoord = texCoord;
    gl_Position = uMVP * vec4(position, 1.0);
}