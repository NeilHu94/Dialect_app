package com.demo.neilhu.dialect;


import android.app.Service;
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
import java.util.zip.Inflater;

import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;
import static com.demo.neilhu.dialect.R.mipmap.family_daughter;
import static com.demo.neilhu.dialect.R.mipmap.family_father;
import static com.demo.neilhu.dialect.R.mipmap.family_grandfather;
import static com.demo.neilhu.dialect.R.mipmap.family_grandmother;
import static com.demo.neilhu.dialect.R.mipmap.family_mother;
import static com.demo.neilhu.dialect.R.mipmap.family_older_brother;
import static com.demo.neilhu.dialect.R.mipmap.family_older_sister;
import static com.demo.neilhu.dialect.R.mipmap.family_son;
import static com.demo.neilhu.dialect.R.mipmap.family_younger_brother;
import static com.demo.neilhu.dialect.R.mipmap.family_younger_sister;
import static com.demo.neilhu.dialect.R.mipmap.ic_launcher;

/**
 * A simple {@link Fragment} subclass.
 */
public class Family_fragment extends Fragment {
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
    private  MediaPlayer.OnCompletionListener completionListener= new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    public Family_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list,container,false);

        audioManager = (AudioManager)getActivity().getSystemService(Service.AUDIO_SERVICE);//获取系统音频服务
        final ArrayList<Word> word_family = new ArrayList<Word>();//创建传输 字符串数组列表word_numebrs
        word_family.add(new Word("爸","bào",family_father,R.raw.my));//向数组表中添加数据
        word_family.add(new Word("妈","mào",family_mother,R.raw.my));
        word_family.add(new Word("爷爷","jiāo jiao",family_grandfather,R.raw.my));
        word_family.add(new Word("奶奶","nào nao",family_grandmother,R.raw.my));
        word_family.add(new Word("姐","zei",family_older_sister,R.raw.my));
        word_family.add(new Word("哥","ge",family_older_brother,R.raw.my));
        word_family.add(new Word("妹妹","ni ma",family_younger_sister,R.raw.my));
        word_family.add(new Word("弟","di",family_younger_brother,R.raw.my));
        word_family.add(new Word("儿子","e zi",family_son,R.raw.my));
        word_family.add(new Word("女儿","ni",family_daughter,R.raw.my));

        WordAdapter itemsAdapter = new WordAdapter(getActivity(), word_family, R.color.Family_color);//自定义列表适配器
        ListView listview =(ListView)rootView.findViewById(R.id.list);
        listview.setAdapter(itemsAdapter);//添加适配器
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                releaseMediaPlayer();                //播放语音前先释放内存，这样做当用户点击多个item时会释放上一个语音
                Word w = word_family.get(position);

                //监听音频焦点的变化 做出相应的音频操控

                int result = audioManager.requestAudioFocus(audioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN);
                {
                    if (result == AudioManager.AUDIOFOCUS_GAIN)
                    {
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
