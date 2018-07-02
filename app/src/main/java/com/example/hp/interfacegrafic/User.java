package com.example.hp.interfacegrafic;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AndroidException;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.interfacegrafic.DATA.DataApp;
import com.example.hp.interfacegrafic.ItemMenu.CasaIdDeatalle;
import com.example.hp.interfacegrafic.ItemMenu.UserDetalle;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class User extends AppCompatActivity {

    private String userUrl; // este es el url

    protected TextView NameUser, AddressUser, NumberUser, emailnombre, ciudadNombre;
    protected ImageView imageView2;

    protected UserDetalle Data;
    protected Button call;
    protected User ROOT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ROOT = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        userUrl = this.getIntent().getExtras().getString("user");
        loadViewComponents();
        loadAsinkData();

    }


    private void loadAsinkData() {
        AsyncHttpClient userDeatalle = new AsyncHttpClient();
        userDeatalle.get(DataApp.user_id + "/" + userUrl,
                new JsonHttpResponseHandler() {
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            String nombre = response.getString("nombre");
                            String email = response.getString("email");
                            String numeroTelefono = response.getString("numeroTelefono");
                            String ciudad = response.getString("ciudad");
                            String direccionActual = response.getString("direccionActual");
                            Data = new UserDetalle(nombre, email, numeroTelefono, ciudad, direccionActual);
                            ROOT.informacion();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void informacion() {

        this.NameUser.setText(Data.getNombre());
        this.AddressUser.setText(Data.getDireccionActual());
        this.NumberUser.setText(Data.getNumeroTelefono());
        this.emailnombre.setText(Data.getEmail());

        if (Data.getCiudad() == null) {

            this.ciudadNombre.setText("No se sabe su ciudad");

        } else {
            this.ciudadNombre.setText(Data.getCiudad());
        }

    }

    private void loadViewComponents() {

        this.NameUser = (TextView) this.findViewById(R.id.NameUser);
        this.AddressUser = (TextView) this.findViewById(R.id.AddressUser);
        this.NumberUser = (TextView) this.findViewById(R.id.NumberUser);
        this.emailnombre = (TextView) this.findViewById(R.id.emailnombre);
        this.ciudadNombre = (TextView) this.findViewById(R.id.ciudadNombre);

        this.imageView2 = (ImageView) this.findViewById(R.id.imageView2);


        call = (Button) this.findViewById(R.id.button);
        call.setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.M)
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    if (ActivityCompat.checkSelfPermission(ROOT, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        String num = NumberUser.getText().toString();

                        Uri call2 = Uri.parse("tel:" + num);
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + num));

                        startActivity(intent);

                    }

                    requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE},100);

                }
                else
                {
                    if (ActivityCompat.checkSelfPermission(ROOT, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        String num = NumberUser.getText().toString();

                        Uri call2 = Uri.parse("tel:" + num);
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + num));

                        startActivity(intent);

                    }

                }
            }
        });
    }



}
