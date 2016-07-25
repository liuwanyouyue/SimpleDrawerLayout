package ll.github.simpledrawerlayout.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijing on 2016/7/12.
 */
public class DataSource {

    public static final List<String> generateData(int size)
    {
        if (size <= 0)
            return null;
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < size; i++)
        {
            datas.add("这是列表数据"+i);
        }
        return datas;
    }
}
