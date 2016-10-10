package com.dreamliner.loadingdrawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.dreamliner.loadingdrawable.render.LoadingDrawable;
import com.dreamliner.loadingdrawable.render.LoadingRenderer;
import com.dreamliner.loadingdrawable.render.rotate.LevelLoadingRenderer;


public class LoadingView extends ImageView {

    private LoadingDrawable mLoadingDrawable;

    public LoadingView(Context context) {
        super(context);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        try {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LoadingView);
            int mLoadingColorId = ta.getResourceId(R.styleable.LoadingView_loading_color, 0);


            LevelLoadingRenderer.Builder builder = new LevelLoadingRenderer.Builder(context);
            if (0 != mLoadingColorId) {
                int[] colors = new int[3];
                String[] colorStrs = context.getResources().getStringArray(mLoadingColorId);
                for (int i = 0; i < colorStrs.length; i++) {
                    colors[i] = Color.parseColor(colorStrs[i]);
                }
                builder.setLevelColors(colors);
            }
            setLoadingRenderer(builder.build());

            ta.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLoadingRenderer(LoadingRenderer loadingRenderer) {
        mLoadingDrawable = new LoadingDrawable(loadingRenderer);
        setImageDrawable(mLoadingDrawable);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnimation();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAnimation();
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == View.VISIBLE) {
            startAnimation();
        } else {
            stopAnimation();
        }
    }

    private void startAnimation() {
        if (mLoadingDrawable != null) {
            mLoadingDrawable.start();
        }
    }

    private void stopAnimation() {
        if (mLoadingDrawable != null) {
            mLoadingDrawable.stop();
        }
    }
}
