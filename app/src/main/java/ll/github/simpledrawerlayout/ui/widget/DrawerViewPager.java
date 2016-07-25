package ll.github.simpledrawerlayout.ui.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by liuwanyouyue on 2016/7/5.
 */
public class DrawerViewPager extends ViewPager {


    private boolean isEnableScroll = true;

    public boolean isEnableScroll() {
        return isEnableScroll;
    }

    public void setEnableScroll(boolean enableScroll) {
        isEnableScroll = enableScroll;
    }

    public DrawerViewPager(Context context) {
        super(context);
    }

    public DrawerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if(!isEnableScroll){
            return false;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        if(!isEnableScroll){
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
