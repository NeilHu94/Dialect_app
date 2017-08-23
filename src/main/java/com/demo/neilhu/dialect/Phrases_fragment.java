package com.demo.neilhu.dialect;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class Phrases_fragment extends Fragment {
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener =new AudioManager.OnAudioFocusChangeListener() {
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
    private  MediaPlayer.OnCompletionListener completionListener= new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    public Phrases_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.word_list,container,false);


        audioManager = (AudioManager) getActivity().getSystemService(AUDIO_SERVICE);
        final ArrayList<Word> word_phrases = new ArrayList<Word>();//创建传输 字符串数组列表
        word_phrases.add(new Word("早上好","zái shōu hái",R.raw.my));//向数组表中添加数据
        word_phrases.add(new Word("吃饭没","chì fǎo miao",R.raw.my));
        word_phrases.add(new Word("回家了","qǐ gòu le",R.raw.my));
        word_phrases.add(new Word("睡觉了","jǔ giǎ le",R.raw.my));
        word_phrases.add(new Word("再说","zǎ góu",R.raw.my));
        word_phrases.add(new Word("出去玩","cǎi qì shì",R.raw.my));
        word_phrases.add(new Word("早上好","zái shōu hái",R.raw.my));//向数组表中添加数据
        word_phrases.add(new Word("吃饭没","chì fǎo miao",R.raw.my));
        word_phrases.add(new Word("回家了","qǐ gòu le",R.raw.my));
        word_phrases.add(new Word("睡觉了","jǔ giǎ le",R.raw.my));
        word_phrases.add(new Word("再说","zǎ góu",R.raw.my));
        word_phrases.add(new Word("出去玩","cǎi qì shì",R.raw.my));
        WordAdapter itemsAdapter = new WordAdapter(getActivity(), word_phrases, R.color.Phrases_color);//自定义列表适配器
        ListView listview =(ListView)rootView.findViewById(R.id.list);
        listview.setAdapter(itemsAdapter);//添加适配器
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();                //播放语音前先释放内存，这样做当用户点击多个item时会释放上一个语音
                Word w = word_phrases.get(position);
                int result = audioManager.requestAudioFocus(audioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN);
                {
                    if (result == AudioManager.AUDIOFOCUS_GAIN){
                        mediaPlayer = MediaPlayer.create(getActivity(), w.getVideoID());
                        mediaPlayer.start();
                        mediaPlayer.setOnCompletionListener(completionListener);
                    }
                }
            }
        });


        return rootView;
    }
    private  void releaseMediaPlayer(){
        if(mediaPlayer!=null)
        {
            mediaPlayer.release();
            mediaPlayer=null;
            audioManager.abandonAudioFocus(audioFocusChangeListener);
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
