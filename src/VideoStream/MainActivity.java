package com.unk.PoC.VideoStream;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.unk.PoC.Art;
import com.unk.PoC.R;
import com.unk.PoC.Sound;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setVisual();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Art.SetContext(this);
        Art.LoadData();
        Sound.SetContext(this);
        Sound.LoadAll();
        FastRandom.Randomize(111);
    }

    protected void onResume() {
        setVisual();
        super.onResume();
    }

    @Override
    protected void onPause() {
        setVisual();
        super.onPause();
    }

    private void setVisual()
    {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}
