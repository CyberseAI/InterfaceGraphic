package com.example.hp.interfacegrafic;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class MenuLogeado extends AppCompatActivity
{
    String arrayname []={"Vender una casa",
            "Mis ofertas",
            "Buscar casas",
            "Agregar un vecindario"};

    //private Context root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_logeado);

        CircleMenu circleMenu = (CircleMenu)findViewById(R.id.circulo);
        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"),R.drawable.icon_menu,R.drawable.icon_cancel)
                .addSubMenu(Color.parseColor("#e8f73c"), R.drawable.vender)
                .addSubMenu(Color.parseColor("#258cff"), R.drawable.my_ofert)
                .addSubMenu(Color.parseColor("#258cff"), R.drawable.buscar)
                .addSubMenu(Color.parseColor("#258cff"), R.drawable.vecindario);

        circleMenu.setOnMenuSelectedListener(new OnMenuSelectedListener() {
                                                 @Override
                                                 public void onMenuSelected(int index) {
                                                     switch (index){
                                                         case 0:
                                                             Toast.makeText(MenuLogeado.this, "Deceas "+arrayname[index], Toast.LENGTH_SHORT).show();
                                                             startActivity(new Intent(MenuLogeado.this, FormCasas.class));
                                                             break;
                                                         case 1:
                                                             Toast.makeText(MenuLogeado.this, "Revisar "+arrayname[index], Toast.LENGTH_SHORT).show();
                                                             startActivity(new Intent(MenuLogeado.this, FormCasas.class));
                                                             break;
                                                         case 2:
                                                             Toast.makeText(MenuLogeado.this, "Deceas "+arrayname[index], Toast.LENGTH_SHORT).show();
                                                             startActivity(new Intent(MenuLogeado.this, FormCasas.class));

                                                             break;
                                                         case 3:
                                                             Toast.makeText(MenuLogeado.this, "Deceas "+arrayname[index], Toast.LENGTH_SHORT).show();
                                                             startActivity(new Intent(MenuLogeado.this, FormCasas.class));

                                                             break;

                                                     }

                                                 }
                                             });

        //root=this;
        //loadcomponents();
    }

    /*private void loadcomponents()
    {
        Button btn = (Button)this.findViewById(R.id.btnInmueble);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent menu = new Intent(root, FormCasas.class);
                root.startActivity(menu);
            }
        });
    }*/
}
