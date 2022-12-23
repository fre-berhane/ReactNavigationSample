package com.example.reactnativeandroidfragmentexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.facebook.react.ReactFragment;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;

public class SecondActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Fragment reactNativeFragment = new ReactFragment.Builder()
                .setComponentName("HelloWorld")
                .setLaunchOptions(getLaunchOptions("test message"))
                .build();

        // pass the id from the <FrameLayout> and the name of the Fragment reference we created
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.reactNativeActivity, reactNativeFragment)
                .commit();
    }
    private Bundle getLaunchOptions(String message) {
        Bundle initialProperties = new Bundle();
        initialProperties.putString("message", message);
        return initialProperties;
    }
    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }
}