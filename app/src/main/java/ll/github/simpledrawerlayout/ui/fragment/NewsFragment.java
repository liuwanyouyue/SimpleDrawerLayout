package ll.github.simpledrawerlayout.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ll.github.simpledrawerlayout.R;
import ll.github.simpledrawerlayout.ui.adapter.NewsFragmentPagerAdapter;
import ll.github.simpledrawerlayout.ui.fragment.news.NewsBaseFragment;
import ll.github.simpledrawerlayout.ui.fragment.news.NewsChannelFragment;


/**
 * Created by liuwanyouyue on 2016/7/11.
 */
public class NewsFragment extends Fragment {

    private View view;
    private ViewPager mViewPager;

    private List<String> tabs;
    private List<Fragment> fragmentList;

    private NewsFragmentPagerAdapter mPagerAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragment_news,null);
            mViewPager = (ViewPager) view.findViewById(R.id.news_viewpager);
            initViewPager();
        }
        ViewGroup mViewGroup = (ViewGroup)view.getParent();
        if(mViewGroup != null) mViewGroup.removeView(view);

        return view;
    }

    private void initViewPager() {
        setTabs();
        setFragments();
        mPagerAdapter = new NewsFragmentPagerAdapter(super.getActivity().getSupportFragmentManager(),fragmentList,tabs);
        mViewPager.setAdapter(mPagerAdapter);
    }

    private void setTabs() {
        tabs = new ArrayList<>();
        tabs.add("头条");
        tabs.add("经济");
        tabs.add("政治");
        tabs.add("科技");
        tabs.add("娱乐");
        tabs.add("体育");
    }

    private void setFragments() {
        fragmentList = new ArrayList<>();

        NewsBaseFragment mNewsBaseFragment = new NewsBaseFragment();
        fragmentList.add(mNewsBaseFragment);

        for(int i = 1;i<tabs.size();i++) {
            NewsChannelFragment mNewsChannelFragment = new NewsChannelFragment();
            Bundle mBundle = new Bundle();
            mBundle.putString("title", tabs.get(i) + "区域");
            mNewsChannelFragment.setArguments(mBundle);
            fragmentList.add(mNewsChannelFragment);
        }
    }
}
