package com.toufikhasan.fireasoroberdike;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.toufikhasan.fireasoroberdike.AdsControl.AdsController;
import com.toufikhasan.fireasoroberdike.Setting.InternetCheck;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class ShowText extends AppCompatActivity {
    public static final String FILE_NAME = "FILE_NAME";
    public static final String TITLE_NAME = "TITLE_NAME";
    private String filename;

    private AdView mAdView;
    private CountDownTimer countDownTimer;

    AdsController adsController;
    LinearLayout bannerAdsLayout;
    InternetCheck internetCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_text);

        // Banner Ads
        MobileAds.initialize(this, initializationStatus -> {
        });

        bannerAdsLayout = findViewById(R.id.bannerAdsLayout);
        mAdView = findViewById(R.id.adView);

        adsController = new AdsController(this);

        internetCheck = new InternetCheck(getApplicationContext());
        if (internetCheck.isConnected()) {
            if (mAdView != null) {
                adsController.loadBannerAds(mAdView);
                mAdView.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        bannerAdsLayout.setVisibility(View.VISIBLE);
                    }
                });
            } else {
                bannerAdsLayout.setVisibility(View.GONE);
            }

            if (countDownTimer == null) {
                imageAdsLoadAfterSomeTime(5000);
                countDownTimer.start();
            }
        }


        Intent intent = getIntent();
        filename = intent.getStringExtra(FILE_NAME);
        String titleName = intent.getStringExtra(TITLE_NAME);

        // Title Change
        Objects.requireNonNull(getSupportActionBar()).setTitle(titleName);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fileRead();
    }

    public void imageAdsLoadAfterSomeTime(final long milliseconds) {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(milliseconds, 50) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                adsController.loadInterstitialAds();
            }
        };
    }

    @Override
    public void onBackPressed() {
        adsController.showInterstitialAds();
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    public void fileRead() {
        TextView showText = findViewById(R.id.showText);
        // Font Change code start
        showText.setTypeface(Typeface.createFromAsset(getAssets(), "font/Bangla_Font.ttf"));
        // Font Change code start

        String text = "";
        try {
            InputStream x = getAssets().open("text/" + filename);
            int size = x.available();
            byte[] buffer = new byte[size];
            x.read(buffer);
            x.close();
            text = new String(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        showText.setText(text);
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    /**
     * Called when returning to the activity
     */
    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    /**
     * Called before the activity is destroyed
     */
    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}