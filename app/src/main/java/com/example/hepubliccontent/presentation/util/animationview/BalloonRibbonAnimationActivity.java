package com.example.hepubliccontent.presentation.util.animationview;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.hubengagesdk.R;
import com.hubengagesdk.base.BaseActivity;

public class BalloonRibbonAnimationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getResourceId() {
        return R.layout.activity_balloon_ribbon_animation;
    }

    @Override
    protected Class getViewModel() {
        return null;
    }

    @Override
    protected ViewModelProvider.Factory getViewModelFactory() {
        return null;
    }

    @Override
    protected void reload() {

    }

    @Override
    protected void socketConnect() {

    }

    @Override
    protected void socketReconnecting() {

    }

    @Override
    protected void enableSearch(boolean enableSearch) {

    }

    @Override
    protected boolean isLoaded() {
        return false;
    }

    @Override
    public void onPermissionsGranted(int requestCode) {

    }
}