package com.example.milind.fabanimations;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String phone = "213-275-8617";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FloatingActionButton plusFab = (FloatingActionButton) findViewById(R.id.plusButton);
        FloatingActionButton callFab = (FloatingActionButton) findViewById(R.id.callButton);
        FloatingActionButton smsFab = (FloatingActionButton) findViewById(R.id.smsButton);
        final LinearLayout callLayout = (LinearLayout) findViewById(R.id.callLayout);
        final LinearLayout smsLayout = (LinearLayout) findViewById(R.id.messageLayout);
        final Animation show_fab_button = AnimationUtils.loadAnimation(getApplication(), R.anim.show_button);
        final Animation hide_fab_button = AnimationUtils.loadAnimation(getApplication(), R.anim.hide_button);
        final Animation show_layout = AnimationUtils.loadAnimation(getApplication(), R.anim.show_layout);
        final Animation hide_layout = AnimationUtils.loadAnimation(getApplication(), R.anim.hide_layout);

        plusFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(callLayout.getVisibility()== View.VISIBLE && smsLayout.getVisibility()== View.VISIBLE )
                {
                    callLayout.setVisibility(View.GONE);
                    smsLayout.setVisibility(View.GONE);
                    callLayout.startAnimation(hide_layout);
                    smsLayout.startAnimation(hide_layout);
                    plusFab.startAnimation(hide_fab_button);
                }
                else
                {
                    callLayout.setVisibility(View.VISIBLE);
                    smsLayout.setVisibility(View.VISIBLE);
                    callLayout.startAnimation(show_layout);
                    smsLayout.startAnimation(show_layout);
                    plusFab.startAnimation(show_fab_button);
                }
            }
        });

        callFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                callLayout.setVisibility(View.GONE);
                smsLayout.setVisibility(View.GONE);
                callLayout.startAnimation(hide_layout);
                smsLayout.startAnimation(hide_layout);
                plusFab.startAnimation(hide_fab_button);
                Intent mIntent = new Intent(Intent.ACTION_DIAL);
                mIntent.setData(Uri.parse("tel:" + phone));
                if(mIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(mIntent);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"There is no app that support this action",Toast.LENGTH_SHORT).show();
                }
            }
        });

        smsFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                callLayout.setVisibility(View.GONE);
                smsLayout.setVisibility(View.GONE);
                callLayout.startAnimation(hide_layout);
                smsLayout.startAnimation(hide_layout);
                plusFab.startAnimation(hide_fab_button);
                Intent mIntent = new Intent(Intent.ACTION_VIEW);
                mIntent.setData(Uri.parse("sms:" + phone));
                if(mIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(mIntent);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"There is no app that support this action",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
