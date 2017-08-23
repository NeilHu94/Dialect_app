package com.demo.neilhu.dialect;

import android.graphics.Color;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.R.attr.y;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;
import static android.media.CamcorderProfile.get;
import static com.demo.neilhu.dialect.R.mipmap.color_black;
import static com.demo.neilhu.dialect.R.mipmap.color_gray;
import static com.demo.neilhu.dialect.R.mipmap.color_green;
import static com.demo.neilhu.dialect.R.mipmap.color_mustard_yellow;
import static com.demo.neilhu.dialect.R.mipmap.color_red;
import static com.demo.neilhu.dialect.R.mipmap.color_white;
import static com.demo.neilhu.dialect.R.mipmap.ic_launcher;

public class Colors_aty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new Colors_fragment())
                .commit();
    }

}
