package com.example.loveb.fds;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.loveb.fds.view.CommonAdapter;
import com.example.loveb.fds.view.ViewHolder;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TabFragment extends Fragment
{
    public static final String TITLE = "title";
    private String mTitle = "Defaut Value";
    private RecyclerView mRecyclerView;
    // private TextView mTextView;
    private List<String> mDatas = new ArrayList<String>();
    private int j=0;
    private ArrayList<HomeVideo> homeVideos = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mTitle = getArguments().getString(TITLE);
        }
        String homeVideoinfoJsonUrl = "http://118.89.50.76:8888/api/HomeVideo";
        getJSONByVolley(homeVideoinfoJsonUrl);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        mRecyclerView = (RecyclerView) view
                .findViewById(R.id.id_stickynavlayout_innerscrollview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // mTextView = (TextView) view.findViewById(R.id.id_info);
        // mTextView.setText(mTitle);
        for (int i = 0; i < homeVideos.size(); i++)
        {
            HomeVideo hv = new HomeVideo();
            hv = homeVideos.get(i);
            mDatas.add(hv.getVideoTitle());
        }
        mRecyclerView.setAdapter(new CommonAdapter<String>(getActivity(), R.layout.item, mDatas)
        {
            @Override
            public void convert(ViewHolder holder, String o)
            {
                Resources resources = mContext.getResources();
                holder.setText(R.id.txt_video, o);
                //holder.setImageDrawable(R.id.img_video,resources.getDrawable(R.drawable.ttt));
                holder.setImageBitmap(R.id.img_video,new DownTask().execute(homeVideos.get(j).getVideoImageUrl()));
                j++;
            }
        });

        return view;

    }

    public static TabFragment newInstance(String title)
    {
        TabFragment tabFragment = new TabFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    public void getJSONByVolley(String JSONDataUrl) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, JSONDataUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //处理返回的JSON数据
                        Log.e("bbb", response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError arg0) {
                homeVideos = JsonHelper.homeVideoJson(arg0.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    /*
    * 异步任务执行网络下载图片
    * */
    public class DownTask extends AsyncTask<String, Void, Bitmap> {
        //上面的方法中，第一个参数：网络图片的路径，第二个参数的包装类：进度的刻度，第三个参数：任务执行的返回结果
        @Override

        protected Bitmap doInBackground(String... url) {
            URL myFileURL;
            Bitmap bitmap = null;
            try {
                myFileURL = new URL(url[0]);
                //获得连接
                HttpURLConnection conn = (HttpURLConnection) myFileURL.openConnection();
                //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
                conn.setConnectTimeout(6000);
                //连接设置获得数据流
                conn.setDoInput(true);
                //不使用缓存
                conn.setUseCaches(false);
                //这句可有可无，没有影响
                //conn.connect();
                //得到数据流
                InputStream is = conn.getInputStream();
                //解析得到图片
                bitmap = BitmapFactory.decodeStream(is);
                //关闭数据流
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return bitmap;
        }

        //主要是更新UI
        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            //更新UI
        }
    }

}
