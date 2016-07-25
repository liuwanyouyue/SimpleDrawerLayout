package ll.github.simpledrawerlayout.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liuwanyouyue on 2016/7/14.
 */
public class JokeDetail implements Parcelable {

    public long id;
    public String xhid;
    public String author;
    public String content;
    public String picUrl;
    public String status;

    public JokeDetail() {
    }

    protected JokeDetail(Parcel in) {
        id = in.readLong();
        xhid = in.readString();
        author = in.readString();
        content = in.readString();
        picUrl = in.readString();
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(xhid);
        dest.writeString(author);
        dest.writeString(content);
        dest.writeString(picUrl);
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<JokeDetail> CREATOR = new Creator<JokeDetail>() {
        @Override
        public JokeDetail createFromParcel(Parcel in) {
            return new JokeDetail(in);
        }

        @Override
        public JokeDetail[] newArray(int size) {
            return new JokeDetail[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getXhid() {
        return xhid;
    }

    public void setXhid(String xhid) {
        this.xhid = xhid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
