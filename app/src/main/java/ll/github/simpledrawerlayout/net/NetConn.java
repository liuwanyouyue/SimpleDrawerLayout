package ll.github.simpledrawerlayout.net;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;


import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.List;

import ll.github.simpledrawerlayout.bean.NewsDetail;
import okhttp3.Call;

/**
 * Created by liuwanyouyue on 2016/7/14.
 */
public class NetConn {

    Calendar cal = Calendar.getInstance();
    List<NewsDetail> details;
    public void onConn(final List<NewsDetail> details, final SwipeRefreshLayout mSwipeRefreshLayout, String url, final Context context){

        this.details = details;
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
        OkHttpUtils
                .get()
                .url(url)
                .addParams("max_behot_time",cal.getTime().getTime()+"")
                .addParams("size",100+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mSwipeRefreshLayout.post(new Runnable() {
                            @Override
                            public void run() {
                                mSwipeRefreshLayout.setRefreshing(false);
                            }
                        });
                        Toast.makeText(context,"请求失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mSwipeRefreshLayout.post(new Runnable() {
                            @Override
                            public void run() {
                                mSwipeRefreshLayout.setRefreshing(false);
                            }
                        });
                        String str = response;

                        try {
                            JSONObject obj = new JSONObject(response);
                            if("000000".equals(obj.getString("status"))) {

                                JSONArray array = obj.getJSONArray("detail");
                                for(int i = 0;i<array.length();i++){
                                    NewsDetail detail = new NewsDetail();
                                    JSONObject objStr = new JSONObject(array.getString(i));
                                    String title = (String) objStr.get("title");
                                    detail.setTitle(title);
                                    details.add(detail);
                                }
                            }
//                            recycleSetAdapter();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
    }

}
