package com.wakwa.coversampleapp;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wakwa.cover.Cover;
import com.wakwa.coversampleapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.buttonShowCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cover.with(getApplicationContext()).put(R.layout.cover_sample).on(MainActivity.this, new Cover.OnCoverClickListener() {
                    @Override
                    public void onCoverViewClickListener(View view, Cover cover) {
                        cover.dismiss(500);
                    }
                }, 500);
            }
        });
    }
}
