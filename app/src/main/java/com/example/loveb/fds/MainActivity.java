package com.example.loveb.fds;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.loveb.fds.view.SimpleViewPagerIndicator;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends FragmentActivity {

    private String[] mTitles = new String[]{"精彩视频", "飞碟说", "飞碟一分钟",
    "飞碟唱"};
    private SimpleViewPagerIndicator mIndicator;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private TabFragment[] mFragments = new TabFragment[mTitles.length];
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initDatas();
        initEvents();

        String homeViewFlipperJosnUrl = "http://118.89.50.76:8888/api/FlipViewImage";
        String homeVideoinfoJsonUrl = "http://118.89.50.76:8888/api/HomeVideo";
        getJSONByVolley(homeViewFlipperJosnUrl, 1);
//        getJSONByVolley(homeVideoinfoJsonUrl,2);


    }


    private void initEvents() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                mIndicator.scroll(position, positionOffset);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initDatas() {
        mIndicator.setTitles(mTitles);

        for (int i = 0; i < mTitles.length; i++) {
            mFragments[i] = (TabFragment) TabFragment.newInstance(mTitles[i]);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

        };

        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
    }

    private void initViews() {
        mIndicator = (SimpleViewPagerIndicator) findViewById(R.id.id_stickynavlayout_indicator);
        mViewPager = (ViewPager) findViewById(R.id.id_stickynavlayout_viewpager);

		/*
		RelativeLayout ll = (RelativeLayout) findViewById(R.id.id_stickynavlayout_topview);
		TextView tv = new TextView(this);
		tv.setText("我的动态添加的");
		tv.setBackgroundColor(0x77ff0000);
		ll.addView(tv, new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, 600));
		*/
    }




    public void getJSONByVolley(String JSONDataUrl, final int model) {
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
                if (model == 1) {
                    ArrayList<FlipViewImageUrl> flipImage = JsonHelper.flipViewImageJson(arg0.toString());
                    new DownTask().execute(flipImage.get(0).getImageUrl());
                    new DownTask().execute(flipImage.get(1).getImageUrl());
                    new DownTask().execute(flipImage.get(2).getImageUrl());
                    new DownTask().execute(flipImage.get(3).getImageUrl());
                }
                if (model == 2) {

                }

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
            ImageView image1 = (ImageView) findViewById(R.id.iV_index);
            ImageView image2 = (ImageView) findViewById(R.id.imageView2);
            ImageView image3 = (ImageView) findViewById(R.id.imageView3);
            ImageView image4 = (ImageView) findViewById(R.id.imageView4);
            if (count == 0) {
                image1.setImageBitmap(result);
                count++;
            } else if (count == 1) {
                image2.setImageBitmap(result);
                count++;
            } else if (count == 2) {
                image3.setImageBitmap(result);
                count++;
            } else if (count == 3) {
                image4.setImageBitmap(result);
                count++;
            }
        }
    }
}
