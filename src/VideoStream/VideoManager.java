package com.unk.PoC.VideoStream;

import android.opengl.GLES10;
import android.util.Log;
import javax.microedition.khronos.opengles.GL10;
import static com.unk.PoC.DrawStuff.DrawHelper.drawQuad;
import com.unk.PoC.Config;
import  com.unk.PoC.DrawStuff.Vector3;
import  com.unk.PoC.DrawStuff.Vector2;
import com.unk.PoC.EscapeComponent;


public class VideoManager {

    private static final int screenWidth = Config.ScreenWidth;
    private static final int screenHeight = Config.ScreenHeight;

    private static boolean firstStart = false;
    private static int[] texture = new int[1];
    private static int[] pixels = new int[screenWidth*screenHeight];
    private static EscapeComponent gameComponent;


    static void DrawFrameOnScreen(GL10 gl10) {
        if(!firstStart)
        {
            firstStart=true;
            gameComponent = new EscapeComponent();
            Start();
        }else{
            gameComponent.run();
            Refresh();
        }
        drawQuad(new Vector2<>(-1.0f,-1.0f),
                new Vector2<>(1.0f,1.0f),
                new Vector3(1.0f, 1.0f, 1.0f));

        if(Config.Debug) {
            CheckErrors();
        }
    }

    public static int[] GetScreenBuffer()
    {
        return pixels;
    }

    public static void DrawPixel(int x, int y, int color)
    {
        pixels[x+y*screenWidth]=color;
    }

    private static void  Refresh()
    {
        java.nio.ByteBuffer byteBuf = java.nio.ByteBuffer.allocateDirect(pixels.length * Integer.BYTES);
        byteBuf.order(java.nio.ByteOrder.nativeOrder());
        java.nio.IntBuffer buffer = byteBuf.asIntBuffer();
        buffer.put(pixels);
        buffer.position(0);
        GLES10.glTexSubImage2D(GLES10.GL_TEXTURE_2D, 0, 0,0, screenWidth, screenHeight, GLES10.GL_RGBA,GLES10.GL_UNSIGNED_BYTE, buffer);
    }

    private static void CheckErrors()
    {
        int error;
        while ((error = GLES10.glGetError()) != GLES10.GL_NO_ERROR) {
            Log.e("MyApp ",": glError " + error);
            System.exit(-1);
        }
    }

    private static void Start()
    {
        GLES10.glGenTextures(1,texture,0);
        GLES10.glBindTexture(GLES10.GL_TEXTURE_2D,texture[0]);

        java.nio.ByteBuffer byteBuf = java.nio.ByteBuffer.allocateDirect(pixels.length * Integer.BYTES); //4 bytes per float
        byteBuf.order(java.nio.ByteOrder.nativeOrder());
        java.nio.IntBuffer buffer = byteBuf.asIntBuffer();
        buffer.put(pixels);
        buffer.position(0);

        GLES10.glTexImage2D(GLES10.GL_TEXTURE_2D, 0, GLES10.GL_RGBA, screenWidth, screenHeight, 0,GLES10.GL_RGBA, GLES10.GL_UNSIGNED_BYTE,buffer);

        GLES10.glTexParameterf(GLES10.GL_TEXTURE_2D, GLES10.GL_TEXTURE_MIN_FILTER,GL10.GL_NEAREST);
        GLES10.glTexParameterf(GLES10.GL_TEXTURE_2D, GLES10.GL_TEXTURE_MAG_FILTER,GL10.GL_NEAREST);
        GLES10.glTexParameterf(GLES10.GL_TEXTURE_2D, GLES10.GL_TEXTURE_WRAP_S,GLES10.GL_CLAMP_TO_EDGE);
        GLES10.glTexParameterf(GLES10.GL_TEXTURE_2D, GLES10.GL_TEXTURE_WRAP_T, GLES10.GL_CLAMP_TO_EDGE);
    }
}
