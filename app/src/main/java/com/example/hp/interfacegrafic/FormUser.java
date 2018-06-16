package com.example.hp.interfacegrafic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

public class FormUser extends AppCompatActivity
{
    String avatar,name,email;
    Context root;

    TextView nametxt, emailLC;

    ImageView avatarImg;

    EditText  numeroTelefono, ciudad, direccionActual, password;
    Button guardar;
    HttpClient cliente;
    HttpPost post;
    List<NameValuePair> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_user);

        avatar = this.getIntent().getExtras().getString("avatar");
        name = this.getIntent().getExtras().getString("name");
        email = this.getIntent().getExtras().getString("email");

        avatarImg = (ImageView)this.findViewById(R.id.avatar);
        nametxt = (TextView)this.findViewById(R.id.Nombres);
        emailLC = (TextView)this.findViewById(R.id.email);

        nametxt.setText(name);
        emailLC.setText(email);


        nametxt = (TextView)findViewById(R.id.Nombres);
        emailLC = (TextView)findViewById(R.id.email);

        numeroTelefono = (EditText)findViewById(R.id.txt_Telf);
        ciudad = (EditText)findViewById(R.id.txt_Ciudad);
        direccionActual = (EditText)findViewById(R.id.txt_Direccion);
        guardar = (Button)findViewById(R.id.btn_Guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ciudad.getText().toString().equals("")){
                    Toast.makeText(FormUser.this, "porfabor introdusca su ciudad",
                            Toast.LENGTH_LONG).show();

                }else {
                    new EnviarDatos(FormUser.this).execute();
                }
            }
        });
        /*root=this;*/

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
                        Toast.makeText(contexto, "Datos enviados Existosamente", Toast.LENGTH_SHORT).show();

                        //>>>> condo se guarda los datos esto hace que baya a otra actividad
                        Intent btnG = new Intent(FormUser.this, MenuLogeado.class );
                        FormUser.this.startActivity(btnG);
                        // en este caso yo lo estoy llevando a formcasas

                        //Arreglar con el rescate de datos
                        //>>>>
                        //name.("");
                        //viewApellido.setText("");
                        //viewEmail.setText("");
                        //>>>>
                        nametxt.setText("");
                        emailLC.setText("");
                        numeroTelefono.setText("");
                        ciudad.setText("");
                        direccionActual.setText("");
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
        post = new HttpPost("http://10.10.1.150:7777/api/v1.0/user");
        lista = new ArrayList<NameValuePair>(7);
        //>>>                                               >>>
        /** Cambiar con los datos rescatados
        lista.add(new BasicNameValuePair("nombre", txtnombre.getText().toString().trim()));
        lista.add(new BasicNameValuePair("apellido",viewApellido.getText().toString().trim()));
        lista.add(new BasicNameValuePair("email",viewEmail.getText().toString().trim()));
        */
         //>>>
        lista.add(new BasicNameValuePair("nombre",nametxt.getText().toString().trim()));
        lista.add(new BasicNameValuePair("email",emailLC.getText().toString().trim()));

        lista.add(new BasicNameValuePair("numeroTelefono",numeroTelefono.getText().toString().trim()));
        lista.add(new BasicNameValuePair("ciudad",ciudad.getText().toString().trim()));
        lista.add(new BasicNameValuePair("direccionActual",direccionActual.getText().toString().trim()));
       // lista.add(new BasicNameValuePair("password",password.getText().toString().trim()));
        try{
            post.setEntity(new UrlEncodedFormEntity(lista));
            cliente.execute(post);
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

}
