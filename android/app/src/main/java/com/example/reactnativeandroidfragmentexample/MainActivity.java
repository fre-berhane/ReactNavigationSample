package com.example.reactnativeandroidfragmentexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.facebook.react.ReactFragment;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;

public class MainActivity extends FragmentActivity implements DefaultHardwareBackBtnHandler {
    private Button mButton;
    private Button mActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mButton = findViewById(R.id.button);
        mActivityButton = findViewById(R.id.buttonTest);

        mActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        SecondActivity.class);
                startActivity(intent);
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment reactNativeFragment = new ReactFragment.Builder()
                        .setComponentName("HelloWorld")
                        .setLaunchOptions(getLaunchOptions("test message"))
                        .build();

                // pass the id from the <FrameLayout> and the name of the Fragment reference we created
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.reactNativeFragment, reactNativeFragment)
                        .commit();

            }
        });
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