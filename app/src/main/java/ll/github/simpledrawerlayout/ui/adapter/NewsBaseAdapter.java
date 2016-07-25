package ll.github.simpledrawerlayout.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ll.github.simpledrawerlayout.R;
import ll.github.simpledrawerlayout.bean.NewsDetail;

/**
 * Created by liuwanyouyue on 2016/7/12.
 */
public class NewsBaseAdapter extends RecyclerView.Adapter<NewsBaseAdapter.ViewHolder> {

    List<NewsDetail> datas = new ArrayList<>();
   OnItemClickListener mItemClickListener;

    public interface OnItemClickListener{
        public void onItemClick(View view, NewsDetail data);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
            this.mItemClickListener = listener;
    }

    public NewsBaseAdapter(List<NewsDetail> datas){
         this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sports_news,parent,false);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != mItemClickListener){
                    mItemClickListener.onItemClick(v,(NewsDetail)itemView.getTag());
                }
            }
        });
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsDetail data = datas.get(position);
        holder.bindData(data.getTitle());
        holder.itemView.setTag(data);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mTextView;
//        private ImageView mImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView)itemView.findViewById(R.id.sports_tv);
//            mImageView = (ImageView)itemView.findViewById(R.id.sports_iv);
        }
        private void bindData(String data){
            if(null != data){
                mTextView.setText(data);
            }
        }
    }
}
