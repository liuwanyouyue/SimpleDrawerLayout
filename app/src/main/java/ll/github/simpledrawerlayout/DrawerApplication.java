package ll.github.simpledrawerlayout;

import android.app.Application;

import com.bugtags.library.Bugtags;
import com.bugtags.library.BugtagsOptions;

/**
 * Created by liuwanyouyue on 2016/7/12.
 */
public class DrawerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //在这里初始化
        Bugtags.start("cf81a0cc45b27bae943fa2f0884bfe7a", this, Bugtags.BTGInvocationEventBubble);

        BugtagsOptions options = new BugtagsOptions.Builder().
                trackingLocation(true).//是否获取位置，默认 true
                trackingCrashLog(true).//是否收集crash，默认 true
                trackingConsoleLog(true).//是否收集console log，默认 true
                trackingUserSteps(true).//是否收集用户操作步骤，默认 true
                trackingNetworkURLFilter("(.*)").//自定义网络请求跟踪的 url 规则，默认 null
                build();

        Bugtags.start("cf81a0cc45b27bae943fa2f0884bfe7a", this, Bugtags.BTGInvocationEventBubble, options);
    }
}
