package com.unk.PoC.VideoStream;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class OpenGLView extends GLSurfaceView {

    public static boolean menuTouch = true;
    private static Finger leftFinger = new Finger();
    private static Finger rightFinger = new Finger();

    public OpenGLView(Context context) {
        super(context);
        init();
    }

    public OpenGLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public static KeyEvent GetLeftFingerKey()
    {
        if(menuTouch) {
            KeyEvent key = new KeyEvent(leftFinger.key);
            leftFinger.key.key = KeyEvent.VK_NONE;
            if (key.key != KeyEvent.VK_NONE) {
                leftFinger.active = false;
            }
            return key;
        }else {
            return new KeyEvent(leftFinger.key);
        }
    }

    public static KeyEvent GetRightFingerKey()
    {
        return new KeyEvent(rightFinger.key);
    }

    public static void CleanRightFingerKey(){
            rightFinger.key.key = KeyEvent.VK_NONE;
    }


    public void TouchProcess(float x, float y, int id ,int index,int actionMasked, int actionIndex)
    {
        final float deltaX = 0.04f;
        final float deltaY = 0.05f;
        final float deltaXX = 0.003f;

        if ((index == actionIndex) && (actionMasked != MotionEvent.ACTION_MOVE)) {

            switch (actionMasked) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN:

                    if (x < 0.6f) {
                        leftFinger.previousX = x;
                        leftFinger.previousY = y;
                        leftFinger.id = id;
                        leftFinger.active = true;
                    } else {
                        rightFinger.previousY = y;
                        rightFinger.previousX = x;
                        rightFinger.id = id;
                        rightFinger.active = true;
                        rightFinger.timer.Start();
                    }

                    final float yStat = 0.8f;

                    for (int i = 0; i < 8; i++) {
                        if ((x > 0.2f + i * 0.1f) && (x < 0.2f + (i + 1) * 0.1f) && (y > yStat)) {
                             rightFinger.key.key = KeyEvent.VK_1+i;
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                    if ((leftFinger.id == id) && (leftFinger.active)) {
                        leftFinger.key.key = KeyEvent.VK_NONE;
                        leftFinger.active = false;
                        leftFinger.id = -1;
                    }

                    if ((rightFinger.id == id) && (rightFinger.active)) {
                        if ( rightFinger.timer.Stop() < 150 ){
                            rightFinger.key.key = KeyEvent.VK_SPACE;
                        }
                        rightFinger.active = false;
                        rightFinger.id = -2;
                    }

                    break;
                default:
                  break;
            }
            return;
        }


            if ((rightFinger.active) && (rightFinger.id == id)) {
                final float dx = rightFinger.previousX - x;
                if (dx > deltaXX) {
                    rightFinger.key.key = KeyEvent.VK_Q;
                    rightFinger.previousX = x;
                    rightFinger.previousY = y;
                }

                if (dx < -deltaXX) {
                    rightFinger.key.key = KeyEvent.VK_E;
                    rightFinger.previousX = x;
                    rightFinger.previousY = y;
                }
            }

            if ((leftFinger.active) && (leftFinger.id == id)) {
                final float dx = leftFinger.previousX - x;
                final float dy = leftFinger.previousY - y;

                if (dx > deltaX) {
                    leftFinger.key.key = KeyEvent.VK_A;
                }

                if (dx < -deltaX) {
                    leftFinger.key.key = KeyEvent.VK_D;
                }

                if (dy > deltaY) {
                    leftFinger.key.key = KeyEvent.VK_W;
                }

                if (dy < -deltaY) {
                    leftFinger.key.key = KeyEvent.VK_S;
                }
            }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        for(int i = 0; i < e.getPointerCount(); i++){
            float x = e.getX(i) / getContext().getResources().getDisplayMetrics().widthPixels;
            float y = e.getY(i) / getContext().getResources().getDisplayMetrics().heightPixels;
            TouchProcess(x,y,e.getPointerId(i),i,e.getActionMasked(), e.getActionIndex());
        }
        return true;
    }

    private void init()
    {
        setEGLContextClientVersion(1);
        setPreserveEGLContextOnPause(true);
        setDebugFlags(DEBUG_CHECK_GL_ERROR | DEBUG_LOG_GL_CALLS);
        setRenderer(new OpenGLRenderer());
        setRenderMode(RENDERMODE_CONTINUOUSLY);
    }
}
