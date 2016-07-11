package com.zeng.youji;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import com.zeng.youji.adapter.MyFragmentPagerAdapter;
import com.zeng.youji.fragment.FragmentTab1;
import com.zeng.youji.fragment.FragmentTab2;
import com.zeng.youji.fragment.FragmentTab3;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /*
    ToolBar
     */
    private Toolbar mToolBar;

    private MyFragmentPagerAdapter pagerAdapter;

    private ViewPager viewPager;

    private List<Fragment> fragments = new ArrayList<>();

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mToolBar = (Toolbar) findViewById(R.id.tool_bar);
        mToolBar.inflateMenu(R.menu.toolbar_menu);

        addFragments();

        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(pagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

    }

    private void addFragments() {
        fragments.add(new FragmentTab1());
        fragments.add(new FragmentTab2());
        fragments.add(new FragmentTab3());
    }


}
