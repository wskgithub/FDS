package com.example.loveb.fds;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by loveb on 2017/12/10.
 */

class JsonHelper {
    static ArrayList<HomeVideo> homeVideoJson(String json){
        ArrayList<HomeVideo> homeVideos = new ArrayList<HomeVideo>();
        try{
            JSONArray jsonArray = new JSONArray(extractJson(json));
            for(int i = 0;i < jsonArray.length();i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                HomeVideo homeVideo = new HomeVideo();
                homeVideo.setVideoId(jsonObject.getString("VideoId"));
                homeVideo.setVideoTitle(jsonObject.getString("VideoTitle"));
                homeVideo.setVideoImageUrl(jsonObject.getString("VideoImageUrl"));
                homeVideos.add(homeVideo);
            }
        }catch (Exception e){e.printStackTrace();}
        return homeVideos;
    }

    static ArrayList<FlipViewImageUrl> flipViewImageJson(String json){
        ArrayList<FlipViewImageUrl> flipViewImages = new ArrayList<FlipViewImageUrl>();
        try{
            JSONArray jsonArray = new JSONArray(extractJson(json));
            for(int i = 0;i < jsonArray.length();i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                FlipViewImageUrl flipViewImage = new FlipViewImageUrl();
                flipViewImage.setId(jsonObject.getInt("id"));
                flipViewImage.setImageUrl(jsonObject.getString("ImageUrl"));
                flipViewImages.add(flipViewImage);
            }
        }catch (Exception e){e.printStackTrace();}
        return flipViewImages;
    }

    static ArrayList<VideoInfo> videoInfoJson(String json){
        ArrayList<VideoInfo> videoInfos = new ArrayList<VideoInfo>();
        try{
            JSONArray jsonArray = new JSONArray(extractJson(json));
            for(int i = 0;i < jsonArray.length();i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                VideoInfo videoInfo = new VideoInfo();
                videoInfo.setVideoId(jsonObject.getString("VideoId"));
                videoInfo.setVideoSource(jsonObject.getString("VideoSource"));
                videoInfo.setVideoTitle(jsonObject.getString("VideoTitle"));
                videoInfo.setVideoIntroduce(jsonObject.getString("VideoIntroduce"));
                videoInfos.add(videoInfo);
            }
        }catch (Exception e){e.printStackTrace();}
        return videoInfos;
    }

    static ArrayList<VideoComment> videoCommentJson(String json){
        ArrayList<VideoComment> videoComments = new ArrayList<VideoComment>();
        try{
            JSONArray jsonArray = new JSONArray(extractJson(json));
            for(int i = 0;i < jsonArray.length();i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                VideoComment videoComment = new VideoComment();
                videoComment.setVideoId(jsonObject.getString("VideoId"));
                videoComment.setUserIcon(jsonObject.getString("UserIcon"));
                videoComment.setUserName(jsonObject.getString("UserName"));
                videoComment.setUserComment(jsonObject.getString("UserComment"));
                videoComment.setCommentTime(jsonObject.getString("CommentTime"));
                videoComments.add(videoComment);
            }
        }catch (Exception e){e.printStackTrace();}
        return videoComments;
    }

    private static String extractJson(String sourceJson){
        char[] jsonchar = sourceJson.toCharArray();
        char[] temp = new char[8000];
        int count = 0;
        String err = "com.android.volley.ParseError: org.json.JSONException: Value ";
        for (int i = err.length();i < jsonchar.length; i++){
            temp[count] = jsonchar[i];
            count++;
        }
        return String.valueOf(temp);
    }
}
