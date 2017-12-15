package com.example.loveb.fds;

/**
 * Created by loveb on 2017/12/10.
 */

public class VideoInfo {
    private String VideoId;

    private String VideoSource;

    private String VideoTitle;

    private String VideoIntroduce;

    public void setVideoId(String VideoId){
        this.VideoId = VideoId;
    }
    public String getVideoId(){
        return this.VideoId;
    }
    public void setVideoSource(String VideoSource){
        this.VideoSource = VideoSource;
    }
    public String getVideoSource(){
        return this.VideoSource;
    }
    public void setVideoTitle(String VideoTitle){
        this.VideoTitle = VideoTitle;
    }
    public String getVideoTitle(){
        return this.VideoTitle;
    }
    public void setVideoIntroduce(String VideoIntroduce){
        this.VideoIntroduce = VideoIntroduce;
    }
    public String getVideoIntroduce() {
        return this.VideoIntroduce;
    }
}
