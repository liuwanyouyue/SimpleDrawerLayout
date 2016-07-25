package ll.github.simpledrawerlayout.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liuwanyouyue on 2016/7/14.
 */
public class NewsDetail implements Parcelable {
    public String title;
    public String source;
    public String article_url;
    public int publish_time;
    public long behot_time;
    public int create_time;
    public int digg_count;
    public int bury_count;
    public int repin_count;
    public String group_id;

    public NewsDetail(){

    }
    protected NewsDetail(Parcel in) {
        title = in.readString();
        source = in.readString();
        article_url = in.readString();
        publish_time = in.readInt();
        behot_time = in.readLong();
        create_time = in.readInt();
        digg_count = in.readInt();
        bury_count = in.readInt();
        repin_count = in.readInt();
        group_id = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(source);
        dest.writeString(article_url);
        dest.writeInt(publish_time);
        dest.writeLong(behot_time);
        dest.writeInt(create_time);
        dest.writeInt(digg_count);
        dest.writeInt(bury_count);
        dest.writeInt(repin_count);
        dest.writeString(group_id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NewsDetail> CREATOR = new Creator<NewsDetail>() {
        @Override
        public NewsDetail createFromParcel(Parcel in) {
            return new NewsDetail(in);
        }

        @Override
        public NewsDetail[] newArray(int size) {
            return new NewsDetail[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getArticle_url() {
        return article_url;
    }

    public void setArticle_url(String article_url) {
        this.article_url = article_url;
    }

    public int getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(int publish_time) {
        this.publish_time = publish_time;
    }

    public long getBehot_time() {
        return behot_time;
    }

    public void setBehot_time(long behot_time) {
        this.behot_time = behot_time;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public int getDigg_count() {
        return digg_count;
    }

    public void setDigg_count(int digg_count) {
        this.digg_count = digg_count;
    }

    public int getBury_count() {
        return bury_count;
    }

    public void setBury_count(int bury_count) {
        this.bury_count = bury_count;
    }

    public int getRepin_count() {
        return repin_count;
    }

    public void setRepin_count(int repin_count) {
        this.repin_count = repin_count;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }
}