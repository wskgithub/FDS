package com.example.loveb.fds;

import java.net.URL;

/**
 * Created by 何建钦 on 2017/12/8.
 */

public class Video {
    private int vId;
    private String vName;
//    private URL vUrl;

    public Video() {
    }

    public Video(int vId, String vName) {
        this.vId = vId;
        this.vName = vName;
//        this.vUrl=vUrl;
    }

    public int getvId() {
        return vId;
    }

    public String getvName() {
        return vName;
    }

//    public URL getvUrl(){
//        return vUrl;
//    }

    public void setvId(int vId) {
        this.vId = vId;
    }

    public void setvName(String vName) {
        this.vName = vName;
    }
//    public void setvUrl(URL vUrl){
//        this.vUrl=vUrl;
//    }
}
