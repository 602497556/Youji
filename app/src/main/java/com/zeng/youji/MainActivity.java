package com.zeng.youji;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.zeng.youji.adapter.MyFragmentPagerAdapter;
import com.zeng.youji.fragment.FragmentTab1;
import com.zeng.youji.fragment.FragmentTab2;
import com.zeng.youji.fragment.FragmentTab3;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

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
        mToolBar.setOnMenuItemClickListener(this);

        addFragments();

        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(3);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = null;
//        if(searchItem != null){
//            searchView = (SearchView) searchItem.getActionView();
//        }
//        if(searchView != null){
//            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
//        }
        Log.e("------->","onCreateOptionsMenu() execute .");
        return true;
    }

    /*
      添加对应的Fragment
      */
    private void addFragments() {
        fragments.add(new FragmentTab1());
        fragments.add(new FragmentTab2());
        fragments.add(new FragmentTab3());
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:
                break;
            case R.id.action_profile:
                break;
            case R.id.action_0:
                break;
            case R.id.action_1:
                Toast.makeText(this,"你点击了反馈",Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_2:
                Intent intent = new Intent(this,SettingsActivity.class);
                startActivity(intent);
                break;
        }
        return false;
    }

}
