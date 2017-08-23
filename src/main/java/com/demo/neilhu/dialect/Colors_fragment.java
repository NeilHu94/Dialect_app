package com.demo.neilhu.dialect;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.AUDIO_SERVICE;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;
import static com.demo.neilhu.dialect.R.mipmap.color_black;
import static com.demo.neilhu.dialect.R.mipmap.color_blue;
import static com.demo.neilhu.dialect.R.mipmap.color_gray;
import static com.demo.neilhu.dialect.R.mipmap.color_green;
import static com.demo.neilhu.dialect.R.mipmap.color_mustard_yellow;
import static com.demo.neilhu.dialect.R.mipmap.color_orange;
import static com.demo.neilhu.dialect.R.mipmap.color_purple;
import static com.demo.neilhu.dialect.R.mipmap.color_qing;
import static com.demo.neilhu.dialect.R.mipmap.color_red;
import static com.demo.neilhu.dialect.R.mipmap.color_white;
import static com.demo.neilhu.dialect.R.mipmap.ic_launcher;

/**
 * A simple {@link Fragment} subclass.
 */
public class Colors_fragment extends Fragment {
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mediaPlayer.pause();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            }
        }
    };
    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };


    public Colors_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        audioManager = (AudioManager)getActivity().getSystemService(AUDIO_SERVICE);

        final ArrayList word_colors = new ArrayList<Word>();//创建传输 字符串数组列表
        word_colors.add(new Word("红色", "hān sa", color_red, R.raw.my));//向数组表中添加数据
        word_colors.add(new Word("橙色", "qing sa", color_orange, R.raw.my));
        word_colors.add(new Word("黄色", "ōu sa", color_mustard_yellow, R.raw.my));
        word_colors.add(new Word("绿色", "lě sa", color_green, R.raw.my));
        word_colors.add(new Word("青色", "qìn sa", color_qing, R.raw.my));
        word_colors.add(new Word("蓝色", "lāo sa", color_blue, R.raw.my));
        word_colors.add(new Word("紫色", "zǐ sa", color_purple, R.raw.my));
        word_colors.add(new Word("白色", "pǎ sa", color_white, R.raw.my));
        word_colors.add(new Word("黑色", "hài sa", color_black, R.raw.my));
        word_colors.add(new Word("灰色", "fà sa", color_gray, R.raw.my));
        WordAdapter itemsAdapter = new WordAdapter(getActivity(), word_colors,R.color.Colors_color);//自定义列表适配器
        ListView listview = (ListView)rootView.findViewById(R.id.list);
        listview.setAdapter(itemsAdapter);//添加适配器
        //下面设置item的触发事件
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();                //播放语音前先释放内存，这样做当用户点击多个item时会释放上一个语音
                Word w = (Word) word_colors.get(position);
                int result = audioManager.requestAudioFocus(audioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
                {
                    if (result == AudioManager.AUDIOFOCUS_GAIN) {
                        mediaPlayer = MediaPlayer.create(getActivity(), w.getVideoID());
                        mediaPlayer.start();
                        mediaPlayer.setOnCompletionListener(completionListener);
                    }
                }
            }

            ;
        });


        return rootView;
    }
    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(audioFocusChangeListener);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }


}
