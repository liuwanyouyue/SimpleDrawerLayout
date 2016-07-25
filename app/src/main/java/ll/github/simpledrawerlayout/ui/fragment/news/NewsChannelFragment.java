package ll.github.simpledrawerlayout.ui.fragment.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ll.github.simpledrawerlayout.R;


/**
 * Created by liuwanyouyue on 2016/7/11.
 */
public class NewsChannelFragment extends Fragment {


    private String title;
    private View view;

//    SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    public void setArguments(Bundle args) {
        title = args.getString("title");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragment_news_channel,null);
            ((TextView)view.findViewById(R.id.title_tv)).setText(title);
//            mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refresh_layout);
        }

        ViewGroup mViewGroup = (ViewGroup)view.getParent();
        if(mViewGroup != null) mViewGroup.removeView(view);
//        initRefresh();
        return view;
    }

}
