package com.example.loveb.fds;

/**
 * Created by loveb on 2017/12/10.
 */

class VideoComment {
    private String VideoId;

    private String UserIcon;

    private String UserName;

    private String UserComment;

    private String CommentTime;

    public void setVideoId(String VideoId){
        this.VideoId = VideoId;
    }
    public String getVideoId(){
        return this.VideoId;
    }
    public void setUserIcon(String UserIcon){
        this.UserIcon = UserIcon;
    }
    public String getUserIcon(){
        return this.UserIcon;
    }
    public void setUserName(String UserName){
        this.UserName = UserName;
    }
    public String getUserName(){
        return this.UserName;
    }
    public void setUserComment(String UserComment){
        this.UserComment = UserComment;
    }
    public String getUserComment(){
        return this.UserComment;
    }
    public void setCommentTime(String CommentTime){
        this.CommentTime = CommentTime;
    }
    public String getCommentTime() {
        return this.CommentTime;
    }
}
