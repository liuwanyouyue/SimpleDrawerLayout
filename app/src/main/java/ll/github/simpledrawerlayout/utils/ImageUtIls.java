package ll.github.simpledrawerlayout.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import ll.github.simpledrawerlayout.R;

/**
 * Created by lijing on 2016/8/10.
 */
public class ImageUtils {
    public static void displayImage(Context context,ImageView imageView, String url){
        Glide
                .with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(imageView);
    }

    public static void displayTitleImage(Context context,ImageView imageView, Object url){
        Glide
                .with(context)
                .load(url)
                .centerCrop()
                .crossFade()
                .into(imageView);
    }

}
