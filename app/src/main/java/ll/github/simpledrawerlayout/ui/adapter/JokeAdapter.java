package ll.github.simpledrawerlayout.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ll.github.simpledrawerlayout.R;
import ll.github.simpledrawerlayout.bean.JokeDetail;
import ll.github.simpledrawerlayout.utils.ImageUtils;


/**
 * Created by liuwanyouyue on 2016/7/14.
 */
public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.ViewHolder> {

    private List<JokeDetail> details = new ArrayList<>();
    private Context mContext;


    public JokeAdapter(Context mContext, List<JokeDetail> details) {
        this.details = details;
        this.mContext = mContext;
    }

    OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        public void onItemClick(View view, JokeDetail data);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_joke, null);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != mItemClickListener){
                    mItemClickListener.onItemClick(v,(JokeDetail) itemView.getTag());
                }
            }
        });
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        JokeDetail detail = details.get(position);

        holder.bindData(detail.getContent());

        if(TextUtils.isEmpty(detail.getPicUrl())){
            holder.mImageView.setVisibility(View.GONE);
        }else{
            holder.mImageView.setVisibility(View.VISIBLE);
            holder.bindImageUrl(detail.getPicUrl());
        }

        holder.itemView.setTag(detail);
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.joke_iv);
            mTextView = (TextView) itemView.findViewById(R.id.joke_tv);
        }

        private void bindData(String data){
            if(null != data){
                mTextView.setText(data);
            }
        }

        private void bindImageUrl(String imageUrl) {
            if (null != imageUrl) {
                //加载网络图片
//                        Glide
//                        .with(mContext)
//                        .load(imageUrl)
//                        .centerCrop()
//                        .placeholder(R.mipmap.ic_launcher)
//                        .crossFade()
//                        .into(mImageView);
                ImageUtils.displayImage(mContext,mImageView,imageUrl);
            }
        }
    }

}
