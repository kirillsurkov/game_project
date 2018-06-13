#version 330 core

uniform mat4 uModel;
uniform mat4 uView;
uniform mat4 uProjection;
uniform mat4 uModelView;
uniform mat4 uModelInverse;
uniform mat4 uNormal;
uniform mat4 uMVP;

in vec3 position;
in vec3 normal;
in vec2 texCoord;

out vec3 fPosition;
out vec3 fNormal;
out vec2 fTexCoord;

void main()
{
    fPosition = (uModelView * vec4(position, 1)).xyz;
    fNormal = (uNormal * vec4(normal, 1)).xyz;
    fTexCoord = texCoord;
    gl_Position = uMVP * vec4(position, 1);
}