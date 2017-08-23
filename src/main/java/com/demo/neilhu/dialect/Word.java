package com.demo.neilhu.dialect;

import android.media.Image;

/**
 * Created by NeilHu on 2016/10/16.
 */

public class Word {
    private String MandarinTranslation;//普通话
    private String DialectTranslation;//方言
    private int word_imageID;//储存文字匹配的图片的ID,这样会减少加载量,提高性能
    private final int ImageViewIsNotExit = word_imageID;
    private int videoID;
    public Word(String M, String D, int image,int video){
        this.MandarinTranslation = M;
        this.DialectTranslation = D;
        this.word_imageID = image;
        this.videoID = video;
    }
    public Word(String M, String D,int video){
        this.MandarinTranslation = M;
        this.DialectTranslation = D;
        this.videoID = video;
    }

    public int getVideoID() {
        return videoID;
    }
    public int getWord_imageID() {
        return word_imageID;
    }
    public boolean hasImageView(){
        return word_imageID != ImageViewIsNotExit;
    }
    public String getMandarinTranslation() {
        return MandarinTranslation;
    }

    public String getDialectTranslation() {
        return DialectTranslation;
    }
}
