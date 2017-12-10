package com.example.loveb.fds;

import android.content.Context;
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
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.ViewFlipper;

import com.example.loveb.fds.view.SimpleViewPagerIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends FragmentActivity {

    private String[] mTitles = new String[] { "简介", "评价", "相关" };
    private SimpleViewPagerIndicator mIndicator;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private TabFragment[] mFragments = new TabFragment[mTitles.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initDatas();
        initEvents();

        String homeViewFlipperJosnUrl = "http://118.89.50.76:8888/api/FlipViewImage";
        String homeVideoinfoJsonUrl = "http://118.89.50.76:8888/api/HomeVideo";
//        getJSONByVolley(homeViewFlipperJosnUrl,1);
//        getJSONByVolley(homeVideoinfoJsonUrl,2);



    }


    private void initEvents()
    {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageSelected(int position)
            {
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels)
            {
                mIndicator.scroll(position, positionOffset);
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });

    }

    private void initDatas()
    {
        mIndicator.setTitles(mTitles);

        for (int i = 0; i < mTitles.length; i++)
        {
            mFragments[i] = (TabFragment) TabFragment.newInstance(mTitles[i]);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
        {
            @Override
            public int getCount()
            {
                return mTitles.length;
            }

            @Override
            public Fragment getItem(int position)
            {
                return mFragments[position];
            }

        };

        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
    }

    private void initViews()
    {
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


//    public void getJSONByVolley(String JSONDataUrl, final int model) {
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
//                Request.Method.GET, JSONDataUrl, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        //处理返回的JSON数据
//                        Log.e("bbb", response.toString());
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError arg0) {
//                if (model==1){
//                    ArrayList<FlipViewImageUrl> flipImage = JsonHelper.flipViewImageJson(arg0.toString());
//                    ImageView image1 = (ImageView)findViewById(R.id.iV_index);
//                    ImageView image2 = (ImageView)findViewById(R.id.imageView2);
//                    ImageView image3 = (ImageView)findViewById(R.id.imageView3);
//                    ImageView image4 = (ImageView)findViewById(R.id.imageView4);
//                    Uri uri1 = Uri.parse(flipImage.get(0).getImageUrl());
//                    Uri uri2 = Uri.parse(flipImage.get(1).getImageUrl());
//                    Uri uri3 = Uri.parse(flipImage.get(2).getImageUrl());
//                    Uri uri4 = Uri.parse(flipImage.get(3).getImageUrl());
//                    image1.setImageURI(uri1);
//                    image2.setImageURI(uri2);
//                    image3.setImageURI(uri3);
//                    image4.setImageURI(uri4);
//                }
//                if(model==2){
//                    JsonHelper.homeVideoJson(arg0.toString());
//                }
//
//            }
//        });
//        requestQueue.add(jsonObjectRequest);
//    }
}
