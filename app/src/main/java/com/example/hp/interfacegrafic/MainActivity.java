package com.example.hp.interfacegrafic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity


{

    private Context root;
    GridLayout mainGrid ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        root = this;
        loadConponets();

    }

    private void loadConponets() {
       Button btnMAs = (Button)this.findViewById(R.id.btnLogin);
        btnMAs.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent logear = new Intent(root, LoginActivity.class);
                root.startActivity(logear);
            }
        });


    }


}
