package com.toufikhasan.fireasoroberdike;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class WellcomeScreen extends AppCompatActivity {
    private TextView textView;
    private ImageView topImage,logo;
    CharSequence charSequence;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome_screen);

        //WellcomeScreen.this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        WellcomeScreen.this.getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.teal_200));

        textView = findViewById(R.id.textView);
        topImage = findViewById(R.id.top_vectorFile);
        logo = findViewById(R.id.logo);

        //Animation animation01 = AnimationUtils.loadAnimation(this,R.anim.animation01);

        animationText("স্বাগতম!");

        Thread timeOut = new Thread(){
          public void run(){
              try {
                  synchronized (this){
                      wait(4000);
                  }
              }catch (InterruptedException e){
                  e.printStackTrace();
              }finally {
                  Intent intent = new Intent(WellcomeScreen.this,MainActivity.class);
                  startActivity(intent);
                  finish();
              }
          }
        };
        timeOut.start();


    }
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            textView.setText(charSequence.subSequence(0,index++));

            if(index <= charSequence.length()){
                new Handler().postDelayed(runnable,100);
            }

        }
    };
    public void animationText(CharSequence cs){
        charSequence = cs;
        index = 0;
        textView.setText("");
        new Handler().removeCallbacks(runnable);
        new Handler().postDelayed(runnable,100);
    }
}