package com.example.loveb.fds;

/**
 * Created by loveb on 2017/12/10.
 */

public class HomeVideo {
    private String VideoId;

    private String VideoTitle;

    private String VideoImageUrl;

    public void setVideoId(String VideoId){
        this.VideoId = VideoId;
    }
    public String getVideoId(){
        return this.VideoId;
    }
    public void setVideoTitle(String VideoTitle){
        this.VideoTitle = VideoTitle;
    }
    public String getVideoTitle(){
        return this.VideoTitle;
    }
    public void setVideoImageUrl(String VideoImageUrl){
        this.VideoImageUrl = VideoImageUrl;
    }
    public String getVideoImageUrl(){
        return this.VideoImageUrl;
    }
}
