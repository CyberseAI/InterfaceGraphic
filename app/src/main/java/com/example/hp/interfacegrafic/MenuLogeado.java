package com.example.hp.interfacegrafic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuLogeado extends AppCompatActivity
{
    private Context root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_logeado);

        root=this;
        loadcomponents();
    }

    private void loadcomponents()
    {
        Button btn = (Button)this.findViewById(R.id.btnInmueble);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent menu = new Intent(root, FormCasas.class);
                root.startActivity(menu);
            }
        });
    }
}
