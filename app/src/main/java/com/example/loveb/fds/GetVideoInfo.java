package com.example.loveb.fds;

/**
 * Created by loveb on 2017/12/7.
 */

class GetVideoInfo {
    public static void getHomePageVideoinfo() throws Exception {
        String url = "http://118.89.50.76:8888/api/HomeVideo";
        String req = HttpRequest.get(url);
    }
}
