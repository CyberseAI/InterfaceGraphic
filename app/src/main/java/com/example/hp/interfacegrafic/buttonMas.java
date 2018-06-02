package com.example.hp.interfacegrafic;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class buttonMas extends AppCompatActivity {

    private Context btniniciarSecion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_mas);

        btniniciarSecion = this;
        loadComponets();
    }

    private void loadComponets() {
        /*Button btnMAs = (Button)this.findViewById(R.id.btn_mas);
        btnMAs.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent ubic = new Intent(btniniciarSecion, pestaniaMas.class);
                btniniciarSecion.startActivity(ubic);
            }
        });*/

    }
}
