package com.toufikhasan.fireasoroberdike;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public final int UPDATE_IN_APP_CODE = 2787;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    GridLayout gridLayout;
    ReviewManager manager;
    ReviewInfo reviewInfo;

    @NonNull
    private static Intent getShareIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "He jubok, Fire aso rober dike App contact");
        String shareMassage = "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\nওয়েবসাইটঃ https://toufikhasan.com\nকম্পানির ওয়েবসাইটঃ\nhttp://hilfulfujul.com.bd\n\nThanks for viewing.";
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMassage);
        return shareIntent;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Drawer Layout
        drawerLayout = findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.navication);
        navigationView.setNavigationItemSelectedListener(this);

        //version show in nav_header.xml file
        View header = navigationView.getHeaderView(0);
        TextView versionText = header.findViewById(R.id.versionCode);
        versionText.setText("Version: " + BuildConfig.VERSION_CODE + " || " + BuildConfig.VERSION_NAME);

        //Grid Layout on Item On Click
        gridLayout = findViewById(R.id.gridLayoutId);

        onClickCardItem();
    }

    private void onClickCardItem() {
        int gridChild = gridLayout.getChildCount();
        for (int item = 0; item < gridChild; item++) {
            CardView cardView = (CardView) gridLayout.getChildAt(item);
            int index = item;
            cardView.setOnClickListener(view -> {
                // your click code here
                if (index == 0) {
                    startActivityFile("অনুবাদকের কথা", "01.txt");
                } else if (index == 1) {
                    startActivityFile("হে যুবক! ফিরে এসো রবের দিকে", "02.txt");
                } else if (index == 2) {
                    startActivityFile("তারুণ্য উম্মাহর প্রাণশক্তি", "03.txt");
                } else if (index == 3) {
                    startActivityFile("তরুণ সাহাবি হযরত মুসআব ইবনে উমায়ের রা.", "04.txt");
                } else if (index == 4) {
                    startActivityFile("তারুণ্য উম্মাহর আশার প্রদীপ", "05.txt");
                } else if (index == 5) {
                    startActivityFile("তরুণ প্রজন্মকে ভ্রষ্টতা থেকে বেরিয়ে আসতে হবে", "06.txt");
                } else if (index == 6) {
                    startActivityFile("নামাজের প্রতি যুবকদের যত্নবান হতে হবে", "07.txt");
                } else if (index == 7) {
                    startActivityFile("জীবনের প্রকৃত মাকসাদ", "08.txt");
                } else if (index == 8) {
                    startActivityFile("প্রয়োজন আত্মজিজ্ঞাসা", "09.txt");
                } else if (index == 9) {
                    startActivityFile("নামাজের প্রতি যত্নবান না হওয়ার কারণ", "10.txt");
                } else if (index == 10) {
                    startActivityFile("উদাসীনতা এক ভয়ংকর রোগ", "11.txt");
                } else if (index == 11) {
                    startActivityFile("গাফলতের নিদর্শন", "12.txt");
                } else if (index == 12) {
                    startActivityFile("সালাফদের সতর্কতা", "13.txt");
                } else if (index == 13) {
                    startActivityFile("উদাসীনতা মানুষকে সঠিক পথ থেকে বিরত রাখে", "14.txt");
                } else if (index == 14) {
                    startActivityFile("কতিপয় হৃদয়বিদারক ঘটনা", "15.txt");
                } else if (index == 15) {
                    startActivityFile("মৃত্যুর সময় ভুলে গেছে কালিমা", "16.txt");
                } else if (index == 16) {
                    startActivityFile("নামাজ না পড়া তরুণের করুণ পরিণতি", "17.txt");
                } else if (index == 17) {
                    startActivityFile("মৃত্যুর সময় কুরআন পড়ছিল এক যুবক", "18.txt");
                } else if (index == 18) {
                    startActivityFile("যুবকের সৌভাগ্যের মৃত্যু", "19.txt");
                } else if (index == 19) {
                    startActivityFile("তরুণ প্রজন্মের হৃদয়ে ঈমানের পরিচর্যা", "20.txt");
                } else if (index == 20) {
                    startActivityFile("যুবকদের জ্ঞান অর্জন", "21.txt");
                } else if (index == 21) {
                    startActivityFile("জ্ঞান অর্জনের ফজিলত", "22.txt");
                } else if (index == 22) {
                    startActivityFile("জ্ঞান অর্জনকারীর গুণাবলি", "23.txt");
                } else if (index == 23) {
                    startActivityFile("রাতের বেলা ইবাদত করা", "24.txt");
                } else if (index == 24) {
                    startActivityFile("যুবকদের মর্যাদা", "25.txt");
                } else if (index == 25) {
                    startActivityFile("যুবকদের প্রতি জান্নাতের হাতছানি", "26.txt");
                } else if (index == 26) {
                    startActivityFile("যুবসমাজের অবক্ষয় ও তার পরিবর্তন", "27.txt");
                } else if (index == 27) {
                    startActivityFile("হে যুবক! এসো আত্মশুদ্ধির মোহনায়", "28.txt");
                } else if (index == 28) {
                    startActivityFile("সৎ ব্যক্তিদের সংস্পর্শ", "29.txt");
                } else if (index == 29) {
                    startActivityFile("নীড়ে ফেরার গল্প", "30.txt");
                } else if (index == 30) {
                    startActivityFile("আত্মশুদ্ধির গল্প", "31.txt");
                } else if (index == 31) {
                    startActivityFile("এ অবস্থা থেকে মুসলিম তরুণ প্রজন্মের উত্তোরণের পথ কী?", "32.txt");
                } else if (index == 32) {
                    startActivityFile("অনুতপ্ত অশ্রু", "33.txt");
                } else if (index == 33) {
                    startActivityFile("হে তরুণ! উম্মাহ ডাকছে তোমায়", "34.txt");
                } else if (index == 34) {
                    startActivityFile("খালিদ বিন ওয়ালিদ রা.", "35.txt");
                } else if (index == 35) {
                    startActivityFile("হযরত খালিদ বিন ওয়ালিদ রা.-এর ইসলাম গ্রহণ", "36.txt");
                } else if (index == 36) {
                    startActivityFile("রোমানদের বিরুদ্ধে যুদ্ধ", "37.txt");
                } else if (index == 37) {
                    startActivityFile("মক্কা বিজয় ও খালিদ বিন ওয়ালিদ রা:", "38.txt");
                } else if (index == 38) {
                    startActivityFile("রাসুলের মৃত্যু-পরবর্তী সৃষ্ট ফেতনার মোকাবেলা", "39.txt");
                } else if (index == 39) {
                    startActivityFile("ফের নতুন যুদ্ধের ডাক", "40.txt");
                } else if (index == 40) {
                    startActivityFile("সেনাপতির দায়িত্ব থেকে অব্যাহতির কারণ", "41.txt");
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();
            exitAlertBox();
        }
    }

    public void exitAlertBox() {
        AlertDialog.Builder exitAlert = new AlertDialog.Builder(MainActivity.this);
        // Title set
        exitAlert.setTitle("Alert Dialog.");
        // Massage Set
        exitAlert.setMessage("আপনি কি অ্যাপ থেকে বের হতে চান?");
        // Icon set
        exitAlert.setIcon(R.drawable.ic_question);

        // Positive Button
        exitAlert.setCancelable(false);

        exitAlert.setPositiveButton("হ্যাঁ", (dialogInterface, i) -> finish());
        exitAlert.setNegativeButton("না", (dialogInterface, i) -> dialogInterface.cancel());

        AlertDialog alertDialog = exitAlert.create();
        alertDialog.show();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        } else if (item.getItemId() == R.id.writter) {
            startActivityFile("লেখক পরিচিতি", "writter_info.txt");
        } else if (item.getItemId() == R.id.about_us) {
            startActivityFile("বই সম্পর্কে", "bookinfo.txt");
        } else if (item.getItemId() == R.id.privacy) {
            gotoUrl("http://toufikhasan.com/android-apk/book/fire-aso-rober-dike/privacy-policy.html");
        } else if (item.getItemId() == R.id.update_app) {
            IN_APP_UPDATE();
        } else if (item.getItemId() == R.id.abar_vinno_kisu_hok) {
            gotoUrl("https://play.google.com/store/apps/details?id=com.toufikhasan.abarvinnokichuhok");
        } else if (item.getItemId() == R.id.ahobban_app) {
            gotoUrl("https://play.google.com/store/apps/details?id=com.toufikhasan.ahobban");
        } else if (item.getItemId() == R.id.moreApp) {
            gotoUrl("https://play.google.com/store/apps/dev?id=5871408368342725724");
        } else if (item.getItemId() == R.id.ratting) {
            IN_APP_REVIEW();
        } else if (item.getItemId() == R.id.contact_us) {
            startActivityFile("যোগাযোগ পেইজ", "contact_us.txt");
        } else if (item.getItemId() == R.id.facebook_page) {
            gotoUrl("https://www.facebook.com/toufik.bd.official");
        } else if (item.getItemId() == R.id.facebook_group) {
            gotoUrl("https://www.facebook.com/groups/books.my.friend");
        } else if (item.getItemId() == R.id.youtube) {
            gotoUrl("https://www.youtube.com/channel/UCJWmYNTgEvJsDm0zqj3lIxw");
        } else if (item.getItemId() == R.id.youtube2) {
            gotoUrl("https://www.youtube.com/channel/UCSw15OyHP_dEzEyHQALwjzw");
        } else if (item.getItemId() == R.id.linkedin) {
            gotoUrl("https://www.linkedin.com/in/ownertoufikhasan/");
        } else if (item.getItemId() == R.id.website) {
            gotoUrl("http://toufikhasan.com");
            Toast.makeText(MainActivity.this, "আমার ওয়েবসাইট", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.shair) {
            Intent shareIntent = getShareIntent();

            startActivity(Intent.createChooser(shareIntent, "ShareVia"));
        }
        return false;
    }

    private void startActivityFile(String title, String fileName) {
        Intent intent = new Intent(MainActivity.this, ShowText.class);
        intent.putExtra(ShowText.TITLE_NAME, title);
        intent.putExtra(ShowText.FILE_NAME, fileName);
        startActivity(intent);
    }

    private void gotoUrl(String link) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
    }


    private void IN_APP_UPDATE() {
        AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(this);

// Returns an intent object that you use to check for an update.
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();

// Checks that the platform will allow the specified type of update.
        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    // This example applies an immediate update. To apply a flexible update
                    // instead, pass in AppUpdateType.FLEXIBLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                // Request the update.

                try {
                    appUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.IMMEDIATE, this, UPDATE_IN_APP_CODE);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }


            } else {
                Toast.makeText(this, "এখনো আপডেট আসে নাই!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void IN_APP_REVIEW() {

        manager = ReviewManagerFactory.create(this);

        Task<ReviewInfo> request = manager.requestReviewFlow();

        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                reviewInfo = task.getResult();
                assert reviewInfo != null;
                Task<Void> voidTask = manager.launchReviewFlow(this, reviewInfo);

                voidTask.addOnSuccessListener(unused -> Toast.makeText(this, "রেটিং দেওয়ার জন্য ধন্যবাদ.", Toast.LENGTH_SHORT).show());
            } else {
                Toast.makeText(this, "Something else...", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == UPDATE_IN_APP_CODE) {
            Toast.makeText(this, "Updating...", Toast.LENGTH_SHORT).show();
        }


    }
}