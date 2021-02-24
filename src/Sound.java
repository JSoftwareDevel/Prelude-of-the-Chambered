package com.unk.PoC;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

public class Sound {
	public static Sound altar;
	public static Sound bosskill;
	public static Sound click1;
	public static Sound click2;
	public static Sound hit;
	public static Sound hurt;
	public static Sound hurt2;
	public static Sound kill;
	public static Sound death;
	public static Sound splash;
	public static Sound key;
	public static Sound pickup;
	public static Sound roll;
	public static Sound shoot;
	public static Sound treasure;
	public static Sound crumble;
	public static Sound slide;
	public static Sound cut;
	public static Sound thud ;
	public static Sound ladder;
	public static Sound potion;

	private static Context superContext;
	private MediaPlayer dataSound;

	public static void SetContext(Context context)
	{
		superContext = context;
	}

   public static void LoadAll()
   {
	   altar = loadSound("snd/altar.wav");
	   bosskill = loadSound("snd/bosskill.wav");
	   click1 = loadSound("snd/click.wav");
	   click2 = loadSound("snd/click2.wav");
	   hit = loadSound("snd/hit.wav");
	   hurt = loadSound("snd/hurt.wav");
	   hurt2 = loadSound("snd/hurt2.wav");
	   kill = loadSound("snd/kill.wav");
	   death = loadSound("snd/death.wav");
	   splash = loadSound("snd/splash.wav");
	   key = loadSound("snd/key.wav");
	   pickup = loadSound("snd/pickup.wav");
	   roll = loadSound("snd/roll.wav");
	   shoot = loadSound("snd/shoot.wav");
	   treasure = loadSound("snd/treasure.wav");
	   crumble = loadSound("snd/crumble.wav");
	   slide = loadSound("snd/slide.wav");
	   cut = loadSound("snd/cut.wav");
	   thud = loadSound("snd/thud.wav");
	   ladder = loadSound("snd/ladder.wav");
	   potion = loadSound("snd/potion.wav");
   }


	private static Sound loadSound(String fileName) {

		Sound sound = new Sound();
		try {
			AssetFileDescriptor afd = superContext.getAssets().openFd(fileName);
			sound.dataSound = new MediaPlayer();
			sound.dataSound.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
			sound.dataSound.prepare();
			sound.dataSound.setLooping(false);
		} catch (Exception e) {
			System.out.println(e);
		}
		return sound;
	}

	public void play() {
		dataSound.start();
	}
}