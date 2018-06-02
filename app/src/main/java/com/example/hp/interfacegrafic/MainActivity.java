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

    private Context butonMas;
    GridLayout mainGrid ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        butonMas = this;
        loadConponets();

    }

    private void loadConponets() {
        Button btnMAs = (Button)this.findViewById(R.id.btn_mas);
        btnMAs.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent ubic = new Intent(butonMas, buttonMas.class);
                butonMas.startActivity(ubic);
            }
        });


    }


}
