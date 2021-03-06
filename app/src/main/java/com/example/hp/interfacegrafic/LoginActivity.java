package com.example.hp.interfacegrafic;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    //Variables para Iniciar Sesion
    private Context btnRegistrar1;
    private GoogleApiClient client;
    private int GOOGLE_CODE = 12345;
    private Context root;
    //*************************
    //Variable para crear cuenta

    //PRA REGISTRAR USARIO
    private Context regUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        client = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, options)
                .build();

        btnRegistrar1 = this;
        loadComponents();
        root=this;
        crearCuentaGoogle();

        //para reg usario
        /*regUsuario = this;
        loadRegusario();
        //*/
        
        checkSessionFile();
    }

    private Boolean checkSessionFile() {
        try {
            InputStream inputstream = this.openFileInput("sessionapp.txt");
            if (inputstream != null) {
                InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
                BufferedReader bufferreader = new BufferedReader(inputstreamreader);
                String recive = "";
                StringBuilder cad = new StringBuilder();
                while ((recive = bufferreader.readLine()) != null ) {
                    cad.append(recive);
                }
                String[] resultado =  cad.toString().split("-");
                Intent p = new Intent(this, MenuLogeado.class);
                p.putExtra("name", resultado[0]);
                p.putExtra("email", resultado[1]);
                p.putExtra("id", resultado[2]);
                this.startActivity(p);

            }
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }

       /* private void loadRegusario() {
        Button btnMAs = (Button)this.findViewById(R.id.btnRegistrarUsuario);
        btnMAs.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent ubic = new Intent(regUsuario, FormUser.class);
                regUsuario.startActivity(ubic);
            }
        });

    }*/

    private void crearCuentaGoogle()
    {
        Button btnCC = (Button)this.findViewById(R.id.btnCuentaGoogle);
        btnCC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent crearc = new Intent(root, Web.class);
                String valor = "https://accounts.google.com/signup/v2/webcreateaccount?flowName=GlifWebSignIn&flowEntry=SignUp";
                crearc.putExtra("valor",valor);
                startActivity(crearc);
            }
        });
    }

    private void loadComponents()
    {
        SignInButton googlebtn = (SignInButton)this.findViewById(R.id.googleButton);
        googlebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               Intent intent = Auth.GoogleSignInApi.getSignInIntent(client);
               startActivityForResult(intent, GOOGLE_CODE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GOOGLE_CODE)
        {
            GoogleSignInResult result =  Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess())
            {
                Intent login = new Intent(this, FormUser.class);
                login.putExtra("avatar" , result.getSignInAccount().getPhotoUrl());
                login.putExtra("name", result.getSignInAccount().getDisplayName());
                login.putExtra("email", result.getSignInAccount().getEmail());

                startActivity(login);
                finish();
            }
            else
            {
                Toast.makeText(this, R.string.error_login,
                        Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
    {

    }



}
