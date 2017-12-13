package com.example.loveb.fds;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity implements View.OnClickListener {

    private VideoView videoView;
    private Button btn_start;
    private Button btn_pause;
    private Button btn_stop;
    private String url1="http://video.feidieshuo.com/mp4/飞碟说/205.为啥深思熟虑做的抉择，有时还不如直觉？主站.mp4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_visit);
        bindViews();
    }
    private void bindViews() {
        videoView = (VideoView) findViewById(R.id.vV_video);
//        btn_start = (Button) findViewById(R.id.btn_start);
//        btn_pause = (Button) findViewById(R.id.btn_pause);
//        btn_stop = (Button) findViewById(R.id.btn_stop);

//        btn_start.setOnClickListener(this);
//        btn_pause.setOnClickListener(this);
//        btn_stop.setOnClickListener(this);

        //根据文件路径播放
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            videoView.setVideoPath(Environment.getExternalStorageDirectory() + "/lesson.mp4");
        }

        //读取放在raw目录下的文件
        //videoView.setVideoURI(Uri.parse("android.resource://com.jay.videoviewdemo/" + R.raw.lesson));
        videoView.setVideoURI(Uri.parse(url1.toString()));
        videoView.setMediaController(new MediaController(this));
        videoView.start();
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_start:
//                videoView.start();
//                break;
//            case R.id.btn_pause:
//                videoView.pause();
//                break;
//            case R.id.btn_stop:
//                videoView.stopPlayback();
//                break;
//        }
    }
}
