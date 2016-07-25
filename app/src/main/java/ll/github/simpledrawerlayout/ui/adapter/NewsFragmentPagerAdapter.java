package ll.github.simpledrawerlayout.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by liuwanyouyue on 2016/7/11.
 */
public class NewsFragmentPagerAdapter extends FragmentStatePagerAdapter {
    List<Fragment> fragemntList;
    List<String> tabs;


    public NewsFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragemntList, List<String> tabs) {
        super(fm);
        this.fragemntList = fragemntList;
        this.tabs = tabs;
    }

    @Override
    public Fragment getItem(int position) {
        return fragemntList.get(position);
    }

    @Override
    public int getCount() {
        return fragemntList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position);
    }


}
