package ll.github.simpledrawerlayout.bean;

import java.util.List;

/**
 * Created by liuwanyouyue on 2016/7/12.
 */
public class News {
    public String status;
    public String desc;
    public List<NewsDetail> detail;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<NewsDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<NewsDetail> detail) {
        this.detail = detail;
    }


}
