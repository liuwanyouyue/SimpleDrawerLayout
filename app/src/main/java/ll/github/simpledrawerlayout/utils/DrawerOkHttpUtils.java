package ll.github.simpledrawerlayout.utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lijing on 2016/7/12.
 */
public class DrawerOkHttpUtils {

    public static void getOnNetWork(){
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://api.1-blog.com/biz/bizserver/news/list.do ").build();

        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                response.body().toString();
            }
        });

        try {
            Response response = mOkHttpClient.newCall(request).execute();
            if(response.isSuccessful()){
                String responseStr = response.body().toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void postOnNetWork(){
        OkHttpClient client = new OkHttpClient();



    }
    String post(String url, String json){
//        RequestBody requestBody = new FormEncodingBuilder
        return "";
    }
}
