package ll.github.simpledrawerlayout.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import ll.github.simpledrawerlayout.R;


/**
 * Created by liuwanyouyue on 2016/7/13.
 */
public class ListLeftAdapter extends BaseAdapter {

    Context context;
//    List<String> datas;
   String[] datas;

    public ListLeftAdapter(Context context, String[] datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_main, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv.setText(datas[position]);

        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView tv;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv = (TextView) rootView.findViewById(R.id.tv);
        }

    }
}
