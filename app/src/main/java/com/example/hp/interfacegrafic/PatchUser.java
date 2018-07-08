package com.example.hp.interfacegrafic;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.interfacegrafic.DATA.DataApp;
import com.example.hp.interfacegrafic.ItemMenu.OnLoadCompleImg;
import com.example.hp.interfacegrafic.ItemMenu.UserDetalle;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPatch;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

public class PatchUser extends AppCompatActivity implements OnLoadCompleImg {

    private String userUrl; // este es el url

    protected TextView NameUser, AddressUser, NumberUser, emailnombre, ciudadNombre;
    protected ImageView imageView2;
    protected UserDetalle Data;
    protected PatchUser ROOT;
    HttpClient cliente;
    HttpPatch patch;
    List<NameValuePair> lista;
    Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ROOT = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patch_user);

        if (this.getIntent().getExtras() != null) {

            userUrl = this.getIntent().getExtras().getString("user");
        }

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
        this.NameUser = (TextView) this.findViewById(R.id.Nombres1);
        this.AddressUser = (TextView) this.findViewById(R.id.txt_Direccion1);
        this.NumberUser = (TextView) this.findViewById(R.id.txt_Telf1);
        this.emailnombre = (TextView) this.findViewById(R.id.email1);
        this.ciudadNombre = (TextView) this.findViewById(R.id.txt_Ciudad1);

        this.imageView2 = (ImageView) this.findViewById(R.id.avatar1);

        guardar = (Button)findViewById(R.id.btn_Guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ciudadNombre.getText().toString().equals("")){
                    Toast.makeText(PatchUser.this, "porfabor introdusca su ciudad",
                            Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(PatchUser.this, "exito",Toast.LENGTH_LONG).show();
                    new PatchUser.EnviarDatos(PatchUser.this).execute();
                }
            }
        });
    }

    class EnviarDatos extends AsyncTask<String, String, String >
    {


        private Activity contexto;
        EnviarDatos(Activity context){
            this.contexto = context;
        }
        @Override
        protected String doInBackground(String... strings) {

            if(datos ()){
                contexto.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(contexto, "Datos Actualizados", Toast.LENGTH_SHORT).show();

                        //>>>> condo se guarda los datos esto hace que baya a otra actividad
                        Intent btnG = new Intent(PatchUser.this, MainActivity.class );
                        PatchUser.this.startActivity(btnG);
                        // en este caso yo lo estoy llevando a formcasas

                        //Arreglar con el rescate de datos
                        //>>>>
                        //name.("");
                        //viewApellido.setText("");
                        //viewEmail.setText("");
                        //>>>>
                        NameUser.setText("");
                        AddressUser.setText("");
                        NumberUser.setText("");
                        emailnombre.setText("");
                        ciudadNombre.setText("");
                        // password.setText("");
                    }
                });


            }
            else{
                contexto.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(contexto, "Datos no enviados", Toast.LENGTH_SHORT).show();

                    }
                });
            }

            return null;

        }

    }

    private boolean datos ()
    {

        cliente = new DefaultHttpClient();
        patch = new HttpPatch(DataApp.HOST+ "/api/v1.0/user_patch/" + userUrl );
        lista = new ArrayList<NameValuePair>(7);
        //>>>                                               >>>
        /** Cambiar con los datos rescatados
         lista.add(new BasicNameValuePair("nombre", txtnombre.getText().toString().trim()));
         lista.add(new BasicNameValuePair("apellido",viewApellido.getText().toString().trim()));
         lista.add(new BasicNameValuePair("email",viewEmail.getText().toString().trim()));
         */
        //>>>
        lista.add(new BasicNameValuePair("nombre",NameUser.getText().toString().trim()));
        lista.add(new BasicNameValuePair("email",emailnombre.getText().toString().trim()));

        lista.add(new BasicNameValuePair("numeroTelefono",NumberUser.getText().toString().trim()));
        lista.add(new BasicNameValuePair("ciudad",ciudadNombre.getText().toString().trim()));
        lista.add(new BasicNameValuePair("direccionActual",AddressUser.getText().toString().trim()));
        // lista.add(new BasicNameValuePair("password",password.getText().toString().trim()));
        try{
            patch.setEntity(new UrlEncodedFormEntity(lista));
            cliente.execute(patch);
            return true;

        }catch (UnsupportedEncodingException e){

            e.printStackTrace();

        }catch (ClientProtocolException e){

            e.printStackTrace();

        }catch (IOException e){

            e.printStackTrace();
        }

        return false;

    }


    @Override
    public void setLoadImage(ImageView container, Bitmap img) {

    }

    @Override
    public void OnloadCompleteImgResult(ImageView img, int position, Bitmap imgsourceimg) {

    }
}
