package com.example.nitinsuranatask2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    private posts posts;
    private Message message;
    private News news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout=(TabLayout)findViewById(R.id.mainacttablayout);
        viewPager=(ViewPager)findViewById(R.id.mainactviewpager);

        if(checkInternet()) {
            posts = new posts();
            message = new Message();
            news=new News();

            tabLayout.setupWithViewPager(viewPager);

            ViewPagerAdapter viewpageradapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
            viewpageradapter.addFragment(posts, "Posts");
            viewpageradapter.addFragment(message, "Messages");
            viewpageradapter.addFragment(news, "News");
            viewPager.setAdapter(viewpageradapter);
        }
        else
        {
            Toast.makeText(this,"Please turn on your internet",Toast.LENGTH_SHORT);
        }
    }
    private boolean checkInternet() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;
        return connected;
    }
    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments=new ArrayList<>();
        private List<String> fragmenttitle=new ArrayList<>();
        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }
        public void addFragment(Fragment fragment,String title)
        {
            fragments.add(fragment);
            fragmenttitle.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmenttitle.get(position);
        }
    }
}
