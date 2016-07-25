package ll.github.simpledrawerlayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuwanyouyue on 2016/7/13.
 */
public class Constants {
    public static final List<String> imagesUrls = new ArrayList<>();
    public List<String> getImagesUrls(){
        imagesUrls.add("http://pic42.nipic.com/20140607/18358876_140318247306_2.jpg");
        imagesUrls.add("http://pic1.nipic.com/2008-12-25/2008122510134038_2.jpg");
        imagesUrls.add("http://pic39.nipic.com/20140226/18071023_162553457000_2.jpg");
        imagesUrls.add("http://pic10.nipic.com/20101103/5063545_000227976000_2.jpg");
        imagesUrls.add("http://img1.3lian.com/2015/w7/90/d/1.jpg");
        imagesUrls.add("http://pic39.nipic.com/20140226/18071023_165220584000_2.jpg");
        imagesUrls.add("http://img.taopic.com/uploads/allimg/120906/219077-120Z616330677.jpg");
        imagesUrls.add("http://pic24.nipic.com/20121003/10754047_140022530392_2.jpg");
        imagesUrls.add("http://pic14.nipic.com/20110527/2531170_101932834000_2.jpg");
        imagesUrls.add("http://pic39.nipic.com/20140226/18071023_165640097000_2.jpg");
        imagesUrls.add("http://img.61gequ.com/allimg/2011-4/201142614314278502.jpg");
        return imagesUrls;
    }

    public static String newsUrl = "http://api.1-blog.com/biz/bizserver/news/list.do";
    public static String jokeUrl = "http://api.1-blog.com/biz/bizserver/xiaohua/list.do";
}
