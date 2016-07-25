package ll.github.simpledrawerlayout.bean;

import java.util.List;

/**
 * Created by liuwanyouyue on 2016/7/14.
 */
public class Joke{
    public String status;
    public String desc;
    public List<JokeDetail> detail;

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

    public List<JokeDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<JokeDetail> detail) {
        this.detail = detail;
    }
}
