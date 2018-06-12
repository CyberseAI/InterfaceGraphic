package com.example.hp.interfacegrafic;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class load_scream extends Activity {

    ImageView iv_1, iv_2, iv_delay;
    Animation ani_1, ani_2, ani_delay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_load_scream);

        iv_1= (ImageView) findViewById(R.id.im_casa);
        iv_2 = findViewById(R.id.im_nombre);
        iv_delay = findViewById(R.id.final_delay);

        ani_1 =  AnimationUtils.loadAnimation(this, R.anim.anim_1);
        ani_2 =  AnimationUtils.loadAnimation(this, R.anim.anim_2);
        ani_delay = AnimationUtils.loadAnimation(this, R.anim.final_delay);


        ani_1.setStartTime(Animation.START_ON_FIRST_FRAME);
        iv_1.startAnimation(ani_1);

        ani_2.setStartTime(0);
        iv_2.startAnimation(ani_2);

        ani_delay.setStartTime(0);
        iv_delay.startAnimation(ani_delay);

        ani_delay.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



    }
}
