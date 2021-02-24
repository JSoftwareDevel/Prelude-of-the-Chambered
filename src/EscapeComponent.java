package com.unk.PoC;

import android.util.Log;

import com.unk.PoC.VideoStream.DeltaTimer;
import com.unk.PoC.VideoStream.KeyEvent;
import com.unk.PoC.VideoStream.OpenGLView;
import com.unk.PoC.gui.Screen;

public class EscapeComponent {
    private Game game;
    private Screen screen;
    private boolean firstRun = true;
    private DeltaTimer timer = new DeltaTimer();

    public EscapeComponent(){
        game = new Game();
        screen = new Screen(Config.ScreenWidth,Config.ScreenHeight);
    }

    public void run() {
        timer.Start();
        tick();
        long dtime = timer.Stop();
        render(dtime);
    }

    private void tick()
    {
        if (firstRun)
        {
            firstRun = false;
            game.newGame();
        }else{

        }
        boolean []keys = new boolean[200];

        if(game.menu != null){
            OpenGLView.menuTouch = true;
        }else{
            OpenGLView.menuTouch = false;
        }

        KeyEvent key1 = OpenGLView.GetLeftFingerKey();
        KeyEvent key2 = OpenGLView.GetRightFingerKey();

        if((key1.key != KeyEvent.VK_NONE) && (key2.key != KeyEvent.VK_NONE)) {
            Log.d("Key Press\n",
                    " Left " + (key1.key == KeyEvent.VK_A) +
                            " \nRight " + (key1.key == KeyEvent.VK_D) +
                            " \nUP " + (key1.key == KeyEvent.VK_W) +
                            " \nDown " + (key1.key == KeyEvent.VK_S)+
                            " \nTurn Left " + (key2.key == KeyEvent.VK_Q)+
                            " \nDown Right " + (key2.key == KeyEvent.VK_E)
            );

        }

        keys[key1.key] = true;
        keys[key2.key] = true;
        OpenGLView.CleanRightFingerKey();
        game.tick(keys);
    }

    private void render(long time) {
        screen.render(game, time);
    }
}
