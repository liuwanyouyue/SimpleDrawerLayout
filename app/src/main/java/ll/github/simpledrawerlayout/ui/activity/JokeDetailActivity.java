package ll.github.simpledrawerlayout.ui.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ll.github.simpledrawerlayout.Constants;
import ll.github.simpledrawerlayout.R;
import ll.github.simpledrawerlayout.bean.JokeDetail;
import ll.github.simpledrawerlayout.utils.ImageUtils;

public class JokeDetailActivity extends AppCompatActivity {

    private static final String TAG = JokeDetailActivity.class.getSimpleName();
    @InjectView(R.id.image)
    ImageView image;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @InjectView(R.id.content_tv)
    TextView contentTv;

    private JokeDetail data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_detail);
        ButterKnife.inject(this);
        initIntent();
        initData();
    }

    private void initIntent() {
        Bundle bundle = getIntent().getExtras();
        data = bundle.getParcelable(Constants.JOKE_DETAIL);
//        Log.e(TAG, "data.getPicUrl()>>> " + data.getPicUrl());
    }

    private void initData() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbar.setTitle(data.getAuthor());
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.accent_material_dark));

        if (data.getPicUrl() == null || "".equals(data.getPicUrl())) {

            ImageUtils.displayTitleImage(this, image, R.mipmap.ic_launcher);
        } else {
            ImageUtils.displayTitleImage(this, image, data.getPicUrl());
        }

        contentTv.setText(data.getContent());

        //设置返回导航按钮
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
