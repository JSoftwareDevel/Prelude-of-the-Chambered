package com.unk.PoC.VideoStream;

import android.opengl.GLES10;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

class OpenGLRenderer implements GLSurfaceView.Renderer {

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        SetConfig(gl10);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {
        SetConfig(gl10);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        VideoManager.DrawFrameOnScreen(gl10);
    }

    private void SetConfig(GL10 gl10)
    {
        GLES10.glClearColor(0.0f, 0.0f, 1f,1f);
        GLES10.glMatrixMode( GLES10.GL_PROJECTION );
        GLES10.glLoadIdentity();
        GLES10.glDisable(GLES10.GL_DEPTH_TEST);
        GLU.gluOrtho2D(gl10,-1.0f,1.0f,1.0f,-1.0f);
        GLES10.glMatrixMode(GLES10.GL_MODELVIEW );
    }
}
