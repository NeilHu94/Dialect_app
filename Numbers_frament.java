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
import static com.demo.neilhu.dialect.R.mipmap.ic_launcher;
import static com.demo.neilhu.dialect.R.mipmap.number_eight;
import static com.demo.neilhu.dialect.R.mipmap.number_five;
import static com.demo.neilhu.dialect.R.mipmap.number_four;
import static com.demo.neilhu.dialect.R.mipmap.number_hundred;
import static com.demo.neilhu.dialect.R.mipmap.number_nine;
import static com.demo.neilhu.dialect.R.mipmap.number_one;
import static com.demo.neilhu.dialect.R.mipmap.number_seven;
import static com.demo.neilhu.dialect.R.mipmap.number_six;
import static com.demo.neilhu.dialect.R.mipmap.number_ten;
import static com.demo.neilhu.dialect.R.mipmap.number_thousand;
import static com.demo.neilhu.dialect.R.mipmap.number_three;
import static com.demo.neilhu.dialect.R.mipmap.number_two;
import static com.demo.neilhu.dialect.R.mipmap.number_wan;
import static com.demo.neilhu.dialect.R.mipmap.number_zero;

/**
 * A simple {@link Fragment} subclass.
 */
public class Numbers_frament extends Fragment {

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener=new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mediaPlayer.pause();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            }       }
    };
    private MediaPlayer.OnCompletionListener completionListener= new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    public Numbers_frament() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.word_list,container,false);

        audioManager = (AudioManager)getActivity().getSystemService(AUDIO_SERVICE);
        final ArrayList<Word> word_numbers = new ArrayList<Word>();//创建传输 字符串数组列表word_numebrs
        word_numbers.add(new Word("零","līn",number_zero,R.raw.my));//向数组表中添加数据
        word_numbers.add(new Word("一","yì",number_one,R.raw.my));
        word_numbers.add(new Word("二","e",number_two,R.raw.my));
        word_numbers.add(new Word("三","sào",number_three,R.raw.my));
        word_numbers.add(new Word("四","sǐ",number_four,R.raw.my));
        word_numbers.add(new Word("五","wú",number_five,R.raw.my));
        word_numbers.add(new Word("六","le",number_six,R.raw.my));
        word_numbers.add(new Word("七","qi",number_seven,R.raw.my));
        word_numbers.add(new Word("八","bào",number_eight,R.raw.my));
        word_numbers.add(new Word("九","jiú",number_nine,R.raw.my));
        word_numbers.add(new Word("十","xi",number_ten,R.raw.my));
        word_numbers.add(new Word("百","bà",number_hundred,R.raw.my));
        word_numbers.add(new Word("千","qiè",number_thousand,R.raw.my));
        word_numbers.add(new Word("万","wao",number_wan,R.raw.my));
        WordAdapter itemsAdapter = new WordAdapter(getActivity(), word_numbers, R.color.Family_color);//自定义列表适配器
        ListView listview =(ListView)rootView.findViewById(R.id.list);
        listview.setAdapter(itemsAdapter);//添加适配器
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();                //播放语音前先释放内存，这样做当用户点击多个item时会释放上一个语音
                Word w = word_numbers.get(position);//定义一个word类，便于获取辅助识别语音id
                int reslut =audioManager.requestAudioFocus(onAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN);
                {
                    if (reslut==AudioManager.AUDIOFOCUS_GAIN) {
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
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
