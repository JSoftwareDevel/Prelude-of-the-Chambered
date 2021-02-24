package com.unk.PoC.DrawStuff;

import android.opengl.GLES10;

public class DrawHelper {

    public static void drawQuad(Vector2<Float> p1, Vector2<Float> p2,Vector3 color)
    {
        GLES10.glColor4f(color.x,color.y,color.z,1.0f);
        java.nio.FloatBuffer mTextureBuffer;

        float[] textureCoords = { 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f,
                                  1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f };

        java.nio.ByteBuffer tbb =  java.nio.ByteBuffer.allocateDirect(textureCoords.length * Float.BYTES);
        tbb.order( java.nio.ByteOrder.nativeOrder());
        mTextureBuffer = tbb.asFloatBuffer();
        mTextureBuffer.put(textureCoords);
        mTextureBuffer.position(0);

        float vertex[] = { p1.x,p1.y,  p1.x,p2.y   ,   p2.x,p2.y,
                             p2.x,p2.y,  p2.x,p1.y   ,   p1.x,p1.y
        };

        java.nio.ByteBuffer byteBuf = java.nio.ByteBuffer.allocateDirect(vertex.length * Float.BYTES);
        byteBuf.order(java.nio.ByteOrder.nativeOrder());
        java.nio.FloatBuffer buffer = byteBuf.asFloatBuffer();
        buffer.put(vertex);
        buffer.position(0);

        GLES10.glEnableClientState(GLES10.GL_VERTEX_ARRAY);
        GLES10.glEnable(GLES10.GL_TEXTURE_2D);
        GLES10.glTexCoordPointer(2, GLES10.GL_FLOAT,0, mTextureBuffer);
        GLES10.glEnableClientState(GLES10.GL_TEXTURE_COORD_ARRAY);

        GLES10.glVertexPointer(2, GLES10.GL_FLOAT, 0, buffer);
        GLES10.glDrawArrays(GLES10.GL_TRIANGLES, 0, 6);
        GLES10.glDisableClientState(GLES10.GL_VERTEX_ARRAY);
    }
}

