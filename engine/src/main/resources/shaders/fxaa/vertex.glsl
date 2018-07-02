#version 330 core

uniform vec2 resolution;

in vec3 position;
in vec2 texCoord;

out vec2 uv;
out vec2 rgbNW;
out vec2 rgbNE;
out vec2 rgbSW;
out vec2 rgbSE;
out vec2 rgbM;

void main()
{
    uv = texCoord;
    rgbNW = texCoord+(vec2(-1, -1)/resolution);
    rgbNE = texCoord+(vec2( 1, -1)/resolution);
    rgbSW = texCoord+(vec2(-1,  1)/resolution);
    rgbSE = texCoord+(vec2( 1,  1)/resolution);
    rgbM = texCoord;
    gl_Position = vec4(position, 1.0);
}