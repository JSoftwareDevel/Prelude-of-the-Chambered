package com.unk.PoC;

import com.unk.PoC.VideoStream.KeyEvent;
import com.unk.PoC.entities.Item;
import com.unk.PoC.entities.Player;
import com.unk.PoC.level.Level;
import com.unk.PoC.level.block.LadderBlock;
import com.unk.PoC.menu.LoseMenu;
import com.unk.PoC.menu.Menu;
import com.unk.PoC.menu.PauseMenu;
import com.unk.PoC.menu.TitleMenu;
import com.unk.PoC.menu.WinMenu;

public class Game {
    public Level level;
    public Player player;
    public int pauseTime;
    public Menu menu;

    public Game()
    {
        if(!Config.Debug) {
            setMenu(new TitleMenu());
        }
    }

    public void newGame() {
        Level.clear();
        level = Level.loadLevel(this, "start");

        player = new Player();
        player.level = level;
        level.player = player;
        player.x = level.xSpawn;
        player.z = level.ySpawn;
        level.addEntity(player);
        player.rot = Math.PI + 0.4;
    }

    public void switchLevel(String name, int id) {
        pauseTime = 30;
        level.removeEntityImmediately(player);
        level = Level.loadLevel(this, name);
        level.findSpawn(id);
        player.x = level.xSpawn;
        player.z = level.ySpawn;
        ((LadderBlock) level.getBlock(level.xSpawn, level.ySpawn)).wait = true;
        player.x += Math.sin(player.rot) * 0.2;
        player.z += Math.cos(player.rot) * 0.2;
        level.addEntity(player);
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void getLoot(Item item) {
        player.addLoot(item);
    }

    public void win(Player player) {
        setMenu(new WinMenu(player));
    }


    public void lose(Player player) {
        setMenu(new LoseMenu(player));
    }

    public void tick(boolean[] keys) {
        if (pauseTime > 0) {
            pauseTime--;
            return;
        }

        boolean lk = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_NUMPAD4];
        boolean rk = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_NUMPAD6];

        boolean up = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_NUMPAD8];
        boolean down = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_NUMPAD2];
        boolean left = keys[KeyEvent.VK_A] ;
        boolean right = keys[KeyEvent.VK_D] ;

        boolean turnLeft = keys[KeyEvent.VK_Q] ;
        boolean turnRight = keys[KeyEvent.VK_E] ;

        boolean use = keys[KeyEvent.VK_SPACE];

        for (int i = 0; i < 8; i++) {
            if (keys[KeyEvent.VK_1 + i]) {
                keys[KeyEvent.VK_1 + i] = false;
                player.selectedSlot = i;
                player.itemUseTime = 0;
            }
        }

        if (keys[KeyEvent.VK_ESCAPE]) {
            keys[KeyEvent.VK_ESCAPE] = false;
            if (menu == null) {
                setMenu(new PauseMenu());
            }
        }

        if (use) {
            keys[KeyEvent.VK_SPACE] = false;
        }

        if (menu != null) {
            keys[KeyEvent.VK_W] = keys[KeyEvent.VK_UP] = keys[KeyEvent.VK_NUMPAD8] = false;
            keys[KeyEvent.VK_S] = keys[KeyEvent.VK_DOWN] = keys[KeyEvent.VK_NUMPAD2] = false;
            keys[KeyEvent.VK_A] = false;
            keys[KeyEvent.VK_D] = false;

            menu.tick(this, up, down, left, right, use);
        } else {
            player.tick(up, down, left, right, turnLeft, turnRight);
            if (use) {
                player.activate();
            }
            level.tick();
        }
    }
}
