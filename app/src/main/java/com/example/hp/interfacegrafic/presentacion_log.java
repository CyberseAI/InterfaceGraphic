package com.example.hp.interfacegrafic;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class presentacion_log extends AppCompatActivity {
    public static final long DURATION_TRANSITION = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentacion_log);
    }

    private Transition transition;
    public void next(View view){
        transition=new Explode();
        ini_second();
    }

    private void ini_second() {
        transition.setDuration(DURATION_TRANSITION);
        transition.setInterpolator(new DecelerateInterpolator());

        Intent intent=new Intent(this,MenuLogeado.class);
        startActivity(intent, ActivityOptionsCompat
                .makeSceneTransitionAnimation(this).toBundle());
    }

}
