package ll.github.simpledrawerlayout.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by liuwanyouyue on 2016/7/11.
 */
public class SearchFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView tv = new TextView(getContext());
        tv.setText("发现");
        tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tv.setBackgroundColor(Color.WHITE);
        Calendar cal = Calendar.getInstance();
       long time =  cal.getTime().getTime();
        Log.e("TIME",""+time);
        return tv;
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }
}
