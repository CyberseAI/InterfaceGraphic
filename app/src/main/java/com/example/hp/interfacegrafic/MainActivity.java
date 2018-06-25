package com.example.hp.interfacegrafic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private Context root;
    private Context btnInmueble;
    GridLayout mainGrid ;

    private Context btnBuscar;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        root=this;
        loadComponents();

        btnBuscar = this;
        loadBtnBuscar();

        btnInmueble = this;
        loadbtnInmueble();
    }

    private void loadbtnInmueble() {
        Button btnI = (Button)this.findViewById(R.id.btnInmuebleAdd);
        btnI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(btnInmueble, FormCasas.class);
                btnInmueble.startActivity(login);
            }
        });
    }

    private void loadBtnBuscar() {

        Button btnB = (Button)this.findViewById(R.id.btnSearchM);
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(btnBuscar, Casa_mapas.class);
                btnBuscar.startActivity(login);
            }
        });

    }

    private void loadComponents()
    {
        Button btnLogin = (Button)this.findViewById(R.id.btnLoginM);
        Button btnUser = (Button)this.findViewById(R.id.btnProp);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(root, LoginActivity.class);
                root.startActivity(login);
            }
        });

    }

}
