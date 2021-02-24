package com.unk.PoC.VideoStream;

class Finger {
     int id = 0;
     float previousX = 0;
     float previousY = 0;
     boolean active = false;
     KeyEvent key = new KeyEvent();
     DeltaTimer timer = new DeltaTimer();
}
