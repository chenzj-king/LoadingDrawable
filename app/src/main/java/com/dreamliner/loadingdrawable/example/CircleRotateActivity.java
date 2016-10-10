package com.dreamliner.loadingdrawable.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dreamliner.loadingdrawable.LoadingView;

public class CircleRotateActivity extends AppCompatActivity {

    private boolean isShow = true;

    private LoadingView mLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_rotate);

        mLoadingView = (LoadingView) findViewById(R.id.level_view);

        findViewById(R.id.swtich_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShow) {
                    mLoadingView.setVisibility(View.GONE);
                } else {
                    mLoadingView.setVisibility(View.VISIBLE);
                }
                isShow = !isShow;
            }
        });
    }
}
