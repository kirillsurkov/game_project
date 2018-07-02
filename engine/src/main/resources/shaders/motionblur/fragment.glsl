#version 330 core

uniform sampler2D colorTexture0;
uniform sampler2D colorTexture1;
uniform sampler2D colorTexture2;
uniform sampler2D colorTexture3;
uniform sampler2D colorTexture4;
uniform sampler2D colorTexture5;
uniform sampler2D colorTexture6;
uniform sampler2D colorTexture7;
uniform sampler2D colorTexture8;
uniform sampler2D colorTexture9;
uniform sampler2D colorTexture10;
uniform sampler2D colorTexture11;
uniform sampler2D colorTexture12;
uniform sampler2D colorTexture13;
uniform sampler2D colorTexture14;
uniform sampler2D colorTexture15;
uniform sampler2D colorTexture16;
uniform sampler2D colorTexture17;
uniform sampler2D colorTexture18;
uniform sampler2D colorTexture19;
uniform sampler2D colorTexture20;
uniform sampler2D colorTexture21;
uniform sampler2D colorTexture22;
uniform sampler2D colorTexture23;
uniform sampler2D colorTexture24;
uniform sampler2D colorTexture25;
uniform sampler2D colorTexture26;
uniform sampler2D colorTexture27;
uniform sampler2D colorTexture28;
uniform sampler2D colorTexture29;
uniform bool enabled;

in vec2 uv;

out vec4 gColor;

const float kernel[30] = float[] (
    0.332452, 0.234927, 0.082898, 0.014607, 0.001285, 0.000056, 0.000001, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000, 0.000000
);

void main() {
    if (enabled) {
        gColor = (
            kernel[29] * texture2D(colorTexture29, uv) +
            kernel[28] * texture2D(colorTexture28, uv) +
            kernel[27] * texture2D(colorTexture27, uv) +
            kernel[26] * texture2D(colorTexture26, uv) +
            kernel[25] * texture2D(colorTexture25, uv) +
            kernel[24] * texture2D(colorTexture24, uv) +
            kernel[23] * texture2D(colorTexture23, uv) +
            kernel[22] * texture2D(colorTexture22, uv) +
            kernel[21] * texture2D(colorTexture21, uv) +
            kernel[20] * texture2D(colorTexture20, uv) +
            kernel[19] * texture2D(colorTexture19, uv) +
            kernel[18] * texture2D(colorTexture18, uv) +
            kernel[17] * texture2D(colorTexture17, uv) +
            kernel[16] * texture2D(colorTexture16, uv) +
            kernel[15] * texture2D(colorTexture15, uv) +
            kernel[14] * texture2D(colorTexture14, uv) +
            kernel[13] * texture2D(colorTexture13, uv) +
            kernel[12] * texture2D(colorTexture12, uv) +
            kernel[11] * texture2D(colorTexture11, uv) +
            kernel[10] * texture2D(colorTexture10, uv) +
            kernel[9] * texture2D(colorTexture9, uv) +
            kernel[8] * texture2D(colorTexture8, uv) +
            kernel[7] * texture2D(colorTexture7, uv) +
            kernel[6] * texture2D(colorTexture6, uv) +
            kernel[5] * texture2D(colorTexture5, uv) +
            kernel[4] * texture2D(colorTexture4, uv) +
            kernel[3] * texture2D(colorTexture3, uv) +
            kernel[2] * texture2D(colorTexture2, uv) +
            kernel[1] * texture2D(colorTexture1, uv) +
            kernel[0] * texture2D(colorTexture0, uv)
        ) * 2;
    } else {
        gColor = texture2D(colorTexture0, uv);
    }
}