package ll.github.simpledrawerlayout.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;
import ll.github.simpledrawerlayout.R;

/**
 * Created by liuwanyouyue on 2016/7/14.
 */
public class BGARefreshLayoutWithLoadView extends BGARefreshViewHolder{

    private boolean mIsLoadingMoreEnabled = true;
    private boolean mIsRefreshEnabled = true;

    private String mPullDownRefreshText = "下拉刷新!!!";
    private String mReleaseRefreshText = "释放刷新!!!";
    private String mRefreshingText = "加载中!!!!";
    private String mEndRefreshing = "刷新完成!!!";

    private TextView mHeaderStatusTv;
    private ImageView mHeaderArrowIv,mHeaderChrysanthemumIv;
    private AnimationDrawable mHeaderChrysanthemumAd;

    public BGARefreshLayoutWithLoadView(Context context, boolean isLoadingMoreEnabled, boolean isRefreshEnabled) {
        super(context, isLoadingMoreEnabled);
        this.mIsLoadingMoreEnabled = isLoadingMoreEnabled;
        this.mIsRefreshEnabled = isRefreshEnabled;
    }

    public void setmPullDownRefreshText(String mPullDownRefreshText) {
        this.mPullDownRefreshText = mPullDownRefreshText;
    }

    public void setmReleaseRefreshText(String mReleaseRefreshText) {
        this.mReleaseRefreshText = mReleaseRefreshText;
    }

    public void setmRefreshingText(String mRefreshingText) {
        this.mRefreshingText = mRefreshingText;
    }


    @Override
    /**
     * 定义刷新
     */
    public View getRefreshHeaderView() {

        if(!mIsRefreshEnabled){
            return null;
        }

        if(this.mRefreshHeaderView == null){
            this.mRefreshHeaderView = View.inflate(this.mContext, R.layout.header_bga,null);
            this.mRefreshHeaderView.setBackgroundColor(0);

            if(this.mRefreshViewBackgroundColorRes != -1){
                this.mRefreshHeaderView.setBackgroundResource(this.mRefreshViewBackgroundColorRes);
            }

            if(this.mRefreshViewBackgroundDrawableRes != -1){
                this.mRefreshHeaderView.setBackgroundResource(this.mRefreshViewBackgroundDrawableRes);
            }

            this.mHeaderStatusTv = (TextView) this.mRefreshHeaderView.findViewById(R.id.header_load_textview);

            this.mHeaderArrowIv = (ImageView)this.mRefreshHeaderView.findViewById(R.id.header_animation_imageview1);
            this.mHeaderChrysanthemumIv = (ImageView)this.mRefreshHeaderView.findViewById(R.id.header_animation_imageview2);

            this.mHeaderChrysanthemumAd = (AnimationDrawable)this.mHeaderChrysanthemumIv.getDrawable();
            this.mHeaderStatusTv.setText(this.mPullDownRefreshText);
        }
        return this.mRefreshHeaderView;
    }

    @Override
    public void handleScale(float scale, int moveYDistance) {

    }

    @Override
    public void changeToIdle() {

    }

    @Override
    //开始下拉
    public void changeToPullDown() {
        this.mHeaderStatusTv.setText(mPullDownRefreshText);
        this.mHeaderChrysanthemumIv.setVisibility(View.GONE);
        this.mHeaderChrysanthemumAd.stop();
        this.mHeaderArrowIv.setVisibility(View.VISIBLE);
    }

    @Override
    //下拉到一定程度可以刷新
    public void changeToReleaseRefresh() {
        this.mHeaderStatusTv.setText(mReleaseRefreshText);
        this.mHeaderChrysanthemumIv.setVisibility(View.GONE);
        this.mHeaderChrysanthemumAd.stop();
        this.mHeaderArrowIv.setVisibility(View.VISIBLE);
    }

    @Override
    //已经开始刷新
    public void changeToRefreshing() {
        this.mHeaderStatusTv.setText(mRefreshingText);
        this.mHeaderChrysanthemumIv.clearAnimation();
        this.mHeaderArrowIv.setVisibility(View.GONE);
        this.mHeaderChrysanthemumIv.setVisibility(View.VISIBLE);
        this.mHeaderChrysanthemumAd.start();
    }

    @Override
    //结束刷新
    public void onEndRefreshing() {
        this.mHeaderStatusTv.setText(mEndRefreshing);
        this.mHeaderChrysanthemumIv.setVisibility(View.GONE);
        this.mHeaderChrysanthemumAd.stop();
        this. mHeaderArrowIv.setVisibility(View.VISIBLE);
    }


    @Override
    public View getLoadMoreFooterView() {
        if(!mIsLoadingMoreEnabled){
            return null;
        }

        if(this.mLoadMoreFooterView == null){
            this.mLoadMoreFooterView = View.inflate(mContext,R.layout.footer_bga,null);
            this.mLoadMoreFooterView.setBackgroundColor(Color.TRANSPARENT);

            this.mFooterStatusTv = (TextView)this.mLoadMoreFooterView.findViewById(R.id.footer_load_textview);
            this.mFooterChrysanthemumIv = (ImageView)this.mLoadMoreFooterView.findViewById(R.id.footer_animation_imageview);
            this.mFooterChrysanthemumAd = (AnimationDrawable) this.mFooterChrysanthemumIv.getDrawable();

            this.mFooterStatusTv.setText(this.mLodingMoreText);
        }
        return this.mLoadMoreFooterView;
    }

    public void updateLoadingMoreText(String text){
        this.mFooterStatusTv.setText(text);
    }

    public void hideLoadingMoreImage(){
        this.mFooterChrysanthemumIv.setVisibility(View.GONE);
    }

    public void showLoadingMoreImage(){
        this.mFooterChrysanthemumIv.setVisibility(View.VISIBLE);
    }
}
