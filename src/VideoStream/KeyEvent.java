package com.unk.PoC.VideoStream;



public class KeyEvent {
    public int key;
    KeyEvent(){
        key = VK_NONE;
    }
    KeyEvent(KeyEvent keyevent){
        key = keyevent.key;
    }
    public final static int VK_NONE = 0;
    public final static int VK_CONTROL = 1;
    public final static int VK_ALT = 2;
    public final static int VK_ALT_GRAPH = 3;
    public final static int VK_SHIFT = 4;
    public final static int VK_LEFT = 5;
    public final static int VK_NUMPAD4 = 6;
    public final static int VK_RIGHT = 7;
    public final static int VK_NUMPAD6 = 8;
    public final static int VK_W = 9;
    public final static int VK_S = 10;
    public final static int VK_UP = 11;
    public final static int VK_DOWN = 12;
    public final static int VK_A = 13;
    public final static int VK_NUMPAD8 = 14;
    public final static int VK_NUMPAD2 = 15;
    public final static int VK_D = 16;
    public final static int VK_Q = 17;
    public final static int VK_E = 18;
    public final static int VK_SPACE = 19;
    public final static int VK_ESCAPE = 20;
    public final static int VK_1 = 21;
    public final static int VK_2 = 22;
    public final static int VK_3 = 23;
    public final static int VK_4 = 24;
    public final static int VK_5 = 25;
    public final static int VK_6 = 26;
    public final static int VK_7 = 27;
    public final static int VK_8 = 28;
    public final static int VK_9 = 29;
}
