package ll.github.simpledrawerlayout.ui.fragment.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import ll.github.animationlibrary.adapter.ScaleInAnimatorAdapter;
import ll.github.animationlibrary.adapter.SlideInBottomAnimatorAdapter;
import ll.github.simpledrawerlayout.Constants;
import ll.github.simpledrawerlayout.R;
import ll.github.simpledrawerlayout.bean.NewsDetail;
import ll.github.simpledrawerlayout.ui.activity.NewsDetailActivity;
import ll.github.simpledrawerlayout.ui.adapter.NewsBaseAdapter;
import ll.github.simpledrawerlayout.ui.widget.BGARefreshLayoutWithLoadView;

/**
 * Created by liuwanyouyue on 2016/7/14.
 */
public class NewsBaseFragment extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate {

    private RecyclerView mRecyclerView;
    private BGARefreshLayout mBGARefreshLayout;

    private Context mContext;

    //设置刷新和加载更多
    private BGARefreshLayoutWithLoadView mBGARfereshLayoutWithLoadView = null;

    private boolean isFirst = true;

    //一次加载新闻的条数
    private int itemCount = 30;
    Calendar cal = Calendar.getInstance();

    //请求一次拿到的新闻数据，一次最多30条
    private List<NewsDetail> details = new ArrayList<>();
    //请求所有次数拿到的新闻数据，主要指加载更多的时候
    private List<NewsDetail> detailAll = new ArrayList<>();

    private boolean isLoadMore = false;
    private int count;//手动分批记载的时候，需要纪录进行过加载更多的次数

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mContext = getContext();
        View view = inflater.inflate(R.layout.fragment_push_and_load, null);
        initView(view);

        setBGARefreshLayout();
        setRecyclerView();
        if(isFirst){

            handler.sendEmptyMessage(0);
            isFirst = false;
        }

        ViewGroup mViewGroup = (ViewGroup)view.getParent();
        if(mViewGroup != null) mViewGroup.removeView(view);
        return view;
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mBGARefreshLayout = (BGARefreshLayout) view.findViewById(R.id.bgrefreshlayout);
        //设置刷新和加载的监听
        mBGARefreshLayout.setDelegate(this);
    }

    private void setBGARefreshLayout() {
        mBGARfereshLayoutWithLoadView = new BGARefreshLayoutWithLoadView(getContext(),true,true);
        mBGARefreshLayout.setRefreshViewHolder(mBGARfereshLayoutWithLoadView);
        mBGARfereshLayoutWithLoadView.updateLoadingMoreText("加载更多");
    }

    private void setRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mBGARfereshLayoutWithLoadView.updateLoadingMoreText("上拉加载更多。。。");
        mBGARfereshLayoutWithLoadView.showLoadingMoreImage();
        handler.sendEmptyMessageDelayed(0,2000);
        isLoadMore = false;
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        //如果最后一次请求的结果不满30条，说明已经拿到了最底部的新闻数据
        if((detailAll.size()%itemCount) != 0){
            mBGARfereshLayoutWithLoadView.updateLoadingMoreText("没有更多数据啦。。。");
            mBGARfereshLayoutWithLoadView.hideLoadingMoreImage();
            handler.sendEmptyMessage(2);
            isLoadMore = false;
            return true;
        }

        isLoadMore = true;
        handler.sendEmptyMessageDelayed(0,1000);
        return true;
    }


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    if(!isLoadMore){
                        detailAll.clear();
                    }
                    setData();
                    break;
                case 2:
                    mBGARefreshLayout.endLoadingMore();
                    break;
                case 4:
                    mBGARefreshLayout.endRefreshing();
                    break;
                default:
                    break;
            }
        }
    };

    public void setData(){

        details.clear();
        HttpParams params = new HttpParams();
        if(!isLoadMore){
            count = 0;
            params.put("max_behot_time",cal.getTime().getTime()+"");
        }else {
            count++;
            params.put("max_behot_time",detailAll.get(itemCount*count -1).getBehot_time()+"");
        }

        params.put("size",itemCount+"");
        HttpCallback callback = new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                if(isLoadMore){
                    handler.sendEmptyMessage(2);
                }else{
                    handler.sendEmptyMessage(4);
                }

                try {

                    JSONObject obj = new JSONObject(t);

                    if("000000".equals(obj.getString("status"))) {
                        JSONArray array = obj.getJSONArray("detail");
                        for(int i = 0;i<array.length();i++){
                            JSONObject objStr = new JSONObject(array.getString(i));
                            String title = objStr.getString("title");
                            String article_url =  objStr.getString("article_url");
                            long behot_time = objStr.getLong("behot_time");

                            NewsDetail detail = new NewsDetail();
                            detail.setTitle(title);
                            detail.setArticle_url(article_url);
                            detail.setBehot_time(behot_time);
                            details.add(detail);
                        }
                    }

                    detailAll.addAll(details);
                    recycleSetAdapter();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                if(isLoadMore){
                    handler.sendEmptyMessage(2);
                }else{
                    handler.sendEmptyMessage(4);
                }
                super.onFailure(errorNo, strMsg);
                Toast.makeText(getContext(),"内容："+strMsg, Toast.LENGTH_SHORT).show();
            }
        };

        new RxVolley.Builder()
                .url(Constants.newsUrl)
                .httpMethod(RxVolley.Method.GET)
                .contentType(RxVolley.ContentType.JSON)
                .params(params)
                .callback(callback)
                .encoding("UTF-8")
                .doTask();
    }

    private NewsBaseAdapter mBaseAdapter;
    private ScaleInAnimatorAdapter scaleInRecyclerViewAnimationAdapter;
    private SlideInBottomAnimatorAdapter mSlideInBottomAnimatorAdapter;
    private void recycleSetAdapter() {
        if(!isLoadMore){
            mBaseAdapter = new NewsBaseAdapter(detailAll);

//       scaleInRecyclerViewAnimationAdapter = new ScaleInAnimatorAdapter(mSportsAdapter, mRecyclerView);
//        mRecyclerView.setAdapter(scaleInRecyclerViewAnimationAdapter);
            mSlideInBottomAnimatorAdapter = new SlideInBottomAnimatorAdapter(mBaseAdapter,mRecyclerView);
            mRecyclerView.setAdapter(mSlideInBottomAnimatorAdapter);
        }else{
            mSlideInBottomAnimatorAdapter.notifyDataSetChanged();
        }

        if(!isLoadMore){
            mRecyclerView.scrollToPosition(0);
        }

        mBaseAdapter.setOnItemClickListener(new NewsBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, NewsDetail data) {
                Intent intent = new Intent(getContext(), NewsDetailActivity.class);
                intent.putExtra("detail", data);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStop() {
        super.onStop();
        isFirst = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isFirst = true;
    }
}
