package com.margsapp.messenger.dp_view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.margsapp.messenger.Main.MessageActivity;
import com.margsapp.messenger.R;

import java.util.Objects;

public class personal_dpActivity extends AppCompatActivity {

    ImageView dpView;

    String imageurl;

    String userid;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_personal_dp);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        intent = getIntent();
        userid = intent.getStringExtra("userid");
        imageurl = intent.getStringExtra("data");

        toolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(personal_dpActivity.this, MessageActivity.class);
            Pair[] pairs = new Pair[1];
            pairs[0] = new Pair<View, String>(dpView, "imageTransition");
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(personal_dpActivity.this, pairs);
            intent.putExtra("userid",userid);
            startActivity(intent, options.toBundle());
        });

        dpView = findViewById(R.id.dpview);



        if(imageurl.equals("default"))
        {
            dpView.setImageResource(R.drawable.user);

        }
        else {
            Glide.with(getApplicationContext()).load(imageurl).into(dpView);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(personal_dpActivity.this, MessageActivity.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(dpView, "imageTransition");
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(personal_dpActivity.this, pairs);
        startActivityIfNeeded(intent,0, options.toBundle());
    }
}