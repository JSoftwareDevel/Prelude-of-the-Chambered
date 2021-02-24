package com.unk.PoC;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import com.unk.PoC.gui.Bitmap;
import java.io.InputStream;
import java.nio.IntBuffer;


public class Art {
    public static Bitmap walls;
    public static Bitmap floors;
    public static Bitmap sprites ;
    public static Bitmap font ;
    public static Bitmap panel;
    public static Bitmap items;
    public static Bitmap sky;
    public static Bitmap logo;
    @SuppressLint("StaticFieldLeak")
    private static Context superContext;

    public static void SetContext(Context context)
    {
        superContext = context;
    }

    public static void LoadData()
    {
        walls = loadBitmap("tex/walls.png");
        floors = loadBitmap("tex/floors.png");
        sprites = loadBitmap("tex/sprites.png");
        font = loadBitmap("tex/font.png");
        panel = loadBitmap("tex/gamepanel.png");
        items = loadBitmap("tex/items.png");
        sky = loadBitmap("tex/sky.png");
        logo = loadBitmap("gui/logo.png");
    }

    public static Bitmap recoverBitmap(String myfilename)
    {
        try {
            AssetManager assetManager = superContext.getAssets();
            InputStream istr;
            android.graphics.Bitmap myBitmap;
            istr = assetManager.open(myfilename);

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPremultiplied = false;
            options.inScaled = false;
            options.inPreferredConfig = android.graphics.Bitmap.Config.ARGB_8888;

            myBitmap = BitmapFactory.decodeStream(istr,null,options);

            if (myBitmap == null)
            {
                throw new RuntimeException();
            }

            int w = myBitmap.getWidth();
            int h = myBitmap.getHeight();

            Bitmap result = new Bitmap(w, h);
            int []pixels = new int[w*h];
            IntBuffer pbuf = IntBuffer.wrap(pixels);

            myBitmap.setPremultiplied(false);
            myBitmap.copyPixelsToBuffer(pbuf);

            for (int i = 0; i < w*h; i++) {
                int a = (pixels[i] >> 24) & 0xff;
                int b = (pixels[i] >> 16) & 0xff;
                int g = (pixels[i] >> 8) & 0xff;
                int r = (pixels[i]) & 0xff;

                result.pixels[i] = Color.argb(a,r,g,b);
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static Bitmap loadBitmap(String fileName) {
        try {
            AssetManager assetManager = superContext.getAssets();
            InputStream istr;
            android.graphics.Bitmap myBitmap;
            istr = assetManager.open(fileName);
            myBitmap = BitmapFactory.decodeStream(istr);

            if (myBitmap == null)
            {
                throw new RuntimeException();
            }

            int w = myBitmap.getWidth();
            int h = myBitmap.getHeight();

            Bitmap result = new Bitmap(w, h);
            int []pixels = new int[w*h];
            IntBuffer pbuf = IntBuffer.wrap(pixels);

            myBitmap.setPremultiplied(false);
            myBitmap.copyPixelsToBuffer(pbuf);

            for (int i = 0; i < pixels.length; i++)
            {
                int a = (pixels[i] >> 24) & 0xff;
                int b = (pixels[i] >> 16) & 0xff;
                int g = (pixels[i] >> 8) & 0xff;
                int r = (pixels[i]) & 0xff;
                result.pixels[i] = Color.argb(a,r,g,b);
            }

            for (int i = 0; i < result.pixels.length; i++) {
                int in = result.pixels[i];
                int col = (in & 0xf) >> 2;
                if (in == 0xffff00ff) col = -1;
                result.pixels[i] = col;
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int getCol(int c) {
        int r = (c >> 16) & 0xff;
        int g = (c >> 8) & 0xff;
        int b = (c) & 0xff;

        r = r * 0x55 / 0xff;
        g = g * 0x55 / 0xff;
        b = b * 0x55 / 0xff;

        return r << 16 | g << 8 | b;
    }
}
