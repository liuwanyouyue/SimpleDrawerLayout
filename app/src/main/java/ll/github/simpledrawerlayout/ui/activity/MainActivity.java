package ll.github.simpledrawerlayout.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import ll.github.simpledrawerlayout.R;
import ll.github.simpledrawerlayout.base.BaseActivity;
import ll.github.simpledrawerlayout.ui.adapter.ListLeftAdapter;
import ll.github.simpledrawerlayout.ui.fragment.JokeFragment;
import ll.github.simpledrawerlayout.ui.fragment.MusicFragment;
import ll.github.simpledrawerlayout.ui.fragment.NewsFragment;
import ll.github.simpledrawerlayout.ui.fragment.SearchFragment;
import ll.github.simpledrawerlayout.ui.fragment.SettingFragment;
import ll.github.simpledrawerlayout.ui.fragment.VideoFragment;

/**
 * Created by liuwanyouyue on 2016/7/5.
 */
public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener{

//    @InjectView(R.id.home_container_dv)
//    DrawerViewPager home_container_dv;

//    @InjectView(R.id.home_navigation_bottom_lv)
    ListView home_navigation_bottom_lv;

//    @InjectView(R.id.home_navigation_top_iv)
    ImageView home_navigation_top_iv;

//    @InjectView(R.id.home_drawer)

    DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String[] lvs = {"新闻","笑话" ,"视频", "音乐", "发现", "我的设置"};

//    private List<String> lvs = new ArrayList<>().toArray("新闻","笑话" ,"视频", "音乐", "发现", "我的设置")
    private ListLeftAdapter adapter;

    private List<Fragment> fragments;
    private FragmentManager fm;

    private ActionBar mActionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragments = new ArrayList<>();
        fm = super.getSupportFragmentManager();

        initView();
    }

    private void initView() {
        initListView();
        initActionBar();
        initDrawerLayout();
        initFragments();
    }


    private void initListView() {
        home_navigation_bottom_lv = (ListView)findViewById(R.id.home_navigation_bottom_lv);
        adapter = new ListLeftAdapter(getApplicationContext(),lvs);

        home_navigation_bottom_lv.setAdapter(adapter);
        home_navigation_bottom_lv.setOnItemClickListener(this);
    }

    private void initActionBar() {
        mActionBar = super.getSupportActionBar();
        mActionBar.setTitle(lvs[0]);
    }

    private void initDrawerLayout() {
        mDrawerLayout = (DrawerLayout)findViewById(R.id.home_drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.open,R.string.close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void initFragments() {
        NewsFragment mNewsFragment = new NewsFragment();
        JokeFragment mJokeFragment = new JokeFragment();
        VideoFragment mVideoFragment = new VideoFragment();
        MusicFragment mMusicFragment = new MusicFragment();
        SearchFragment mSearchFragment = new SearchFragment();
        SettingFragment mSettingFragment = new SettingFragment();

        fragments.add(mNewsFragment);
        fragments.add(mJokeFragment);
        fragments.add(mVideoFragment);
        fragments.add(mMusicFragment);
        fragments.add(mSearchFragment);
        fragments.add(mSettingFragment);

        setFragments(0);
    }

    public void setFragments(int position) {
        fm.beginTransaction().replace(R.id.content_frame,fragments.get(position),"t" + position).commit();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        setFragments(position);
        toggleNav();
        mActionBar.setTitle(lvs[position]);
    }

    private void toggleNav(){
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else{
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }
}
