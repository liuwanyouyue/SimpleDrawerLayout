package ll.github.simpledrawerlayout.ui.fragment;

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
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import ll.github.animationlibrary.adapter.ScaleInAnimatorAdapter;
import ll.github.simpledrawerlayout.Constants;
import ll.github.simpledrawerlayout.R;
import ll.github.simpledrawerlayout.bean.JokeDetail;
import ll.github.simpledrawerlayout.ui.activity.JokeDetailActivity;
import ll.github.simpledrawerlayout.ui.adapter.JokeAdapter;
import ll.github.simpledrawerlayout.ui.widget.BGARefreshLayoutWithLoadView;

/**
 * Created by liuwanyouyue on 2016/7/13.
 */
public class JokeFragment extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate{


    private RecyclerView mRecyclerView;
    private BGARefreshLayout mBGARefreshLayout;

    //设置刷新和加载更多
    private BGARefreshLayoutWithLoadView mBGARfereshLayoutWithLoadView = null;

    private boolean isLoadMore = false;

    private List<JokeDetail> details = new ArrayList<>();
    private List<JokeDetail> detailAll = new ArrayList<>();

    private boolean isFirst = true;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_push_and_load,null);
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
//        mRecyclerView.setHasFixedSize(true);
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
        if((detailAll.size() % itemCount) != 0){
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
                        setData("0");
                    }else{
                        lastPageSize++;
                        setData(String.valueOf(lastPageSize));
                    }

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

    private int count;
    private int itemCount = 30;
    private int lastPageSize;
    public void setData(String lastPageSize){
        details.clear();
        HttpParams params = new HttpParams();

        params.put("size",itemCount+"");
        params.put("page",lastPageSize);
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
                            String author = objStr.getString("author");
                            String content =  objStr.getString("content");
                            String picUrl =  objStr.getString("picUrl");

                            JokeDetail detail = new JokeDetail();
                            detail.author = author;
                            detail.content = content;
                            detail.picUrl = picUrl;

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
                .url(Constants.jokeUrl)
                .httpMethod(RxVolley.Method.GET)
                .contentType(RxVolley.ContentType.JSON)
                .params(params)
                .callback(callback)
                .encoding("UTF-8")
                .doTask();

    }
    private JokeAdapter mJokeAdapter;
    private ScaleInAnimatorAdapter scaleInRecyclerViewAnimationAdapter;
//    private SlideInBottomAnimatorAdapter mSlideInBottomAnimatorAdapter;
    private void recycleSetAdapter() {
        if(!isLoadMore){
            mJokeAdapter = new JokeAdapter(getContext(),detailAll);

       scaleInRecyclerViewAnimationAdapter = new ScaleInAnimatorAdapter(mJokeAdapter, mRecyclerView);
        mRecyclerView.setAdapter(scaleInRecyclerViewAnimationAdapter);

//            mSlideInBottomAnimatorAdapter = new SlideInBottomAnimatorAdapter(mJokeAdapter,mRecyclerView);
//            mRecyclerView.setAdapter(mSlideInBottomAnimatorAdapter);
        }else{
//            mSlideInBottomAnimatorAdapter.notifyDataSetChanged();
            scaleInRecyclerViewAnimationAdapter.notifyDataSetChanged();
        }

        if(!isLoadMore){
            mRecyclerView.scrollToPosition(0);
        }

        mJokeAdapter.setOnItemClickListener(new JokeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, JokeDetail data) {
//                Toast.makeText(getContext(),"内容：" + data.content, Toast.LENGTH_LONG).show();

//                if(data.getPicUrl() == null || "".equals(data.getPicUrl())){
//                    Toast.makeText(getContext(),"内容：" + data.content, Toast.LENGTH_LONG).show();
//                }else{
                    Intent intent = new Intent(getActivity(), JokeDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(Constants.JOKE_DETAIL,data);
                    intent.putExtras(bundle);
                    startActivity(intent);
//                }
            }
        });

    }


    @Override
    public void onStop() {
        super.onStop();
        isFirst = true;
    }
}
