package com.example.hp.interfacegrafic;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MenuLogeado extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener
{
    String arrayname []={"Vender una casa",
            "Mis ofertas",
            "Buscar casas",
            "Agregar un vecindario"};

    //private Context root;

    private ImageView photoImageView;
    private TextView nameTextView;
    private TextView emailTextView;
    private TextView idTextView;

    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fade fadeIN =new Fade(Fade.IN);
        fadeIN.setDuration(presentacion_log.DURATION_TRANSITION);
        fadeIN.setInterpolator(new DecelerateInterpolator());

        getWindow().setEnterTransition(fadeIN);
        setContentView(R.layout.activity_menu_logeado);

        photoImageView = (ImageView) findViewById(R.id.foto);
        nameTextView = (TextView) findViewById(R.id.names);
        emailTextView = (TextView) findViewById(R.id.correos);
        idTextView = (TextView) findViewById(R.id.ids);

        if (this.getIntent().getExtras() != null) {

            String name = this.getIntent().getExtras().getString("name");
            String email = this.getIntent().getExtras().getString("email");
            String id = this.getIntent().getExtras().getString("id");
            loadData(name, email, id);
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();








        CircleMenu circleMenu = (CircleMenu)findViewById(R.id.circulo);
        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"),R.drawable.icon_menu,R.drawable.icon_cancel)
                .addSubMenu(Color.parseColor("#f9305a"), R.drawable.vender)
                .addSubMenu(Color.parseColor("#f9ff52"), R.drawable.my_ofert)
                .addSubMenu(Color.parseColor("#3dfe3d"), R.drawable.buscar);
                //.addSubMenu(Color.parseColor("#258cff"), R.drawable.vecindario);

        circleMenu.setOnMenuSelectedListener(new OnMenuSelectedListener() {
                                                 @Override
                                                 public void onMenuSelected(int index) {
                                                     switch (index){
                                                         case 0:
                                                             Toast.makeText(MenuLogeado.this, "Deseas "+arrayname[index], Toast.LENGTH_SHORT).show();
                                                             startActivity(new Intent(MenuLogeado.this, FormCasas.class));
                                                             break;
                                                         case 1:
                                                             Toast.makeText(MenuLogeado.this, "Revisar "+arrayname[index], Toast.LENGTH_SHORT).show();
                                                             startActivity(new Intent(MenuLogeado.this, UserListaId.class));
                                                             break;
                                                         case 2:
                                                             Toast.makeText(MenuLogeado.this, "Deseas "+arrayname[index], Toast.LENGTH_SHORT).show();
                                                             startActivity(new Intent(MenuLogeado.this, Casa_mapas.class));

                                                             break;
                                                         /*case 3:
                                                             Toast.makeText(MenuLogeado.this, "Deseas "+arrayname[index], Toast.LENGTH_SHORT).show();
                                                             //startActivity(new Intent(MenuLogeado.this, FormCasas.class));

                                                             break;*/

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


    private void loadData(String name, String email, String id) {
        nameTextView.setText(name);
        emailTextView.setText(email);
        idTextView.setText(id);
    }

    @Override
    protected void onStart(){

        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()) {
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {

            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {

                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    private void saveSession(String name, String email, String id) {
        String data = name + "-" + email + "-" + id;
        try{
            OutputStreamWriter stream = new OutputStreamWriter(this.openFileOutput("sessionapp.txt", Context.MODE_PRIVATE));

            stream.write(data);
            stream.close();

        }catch (FileNotFoundException e) {

        }catch (IOException e) {

        }
    }
    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            nameTextView.setText(account.getDisplayName());
            emailTextView.setText(account.getEmail());
            idTextView.setText(account.getId());
            saveSession(account.getDisplayName(), account.getEmail(), account.getId());
            Glide.with(this).load(account.getPhotoUrl()).into(photoImageView);


        } else {
            goLogInScreen();
        }
    }

    private void goLogInScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void logOut(View view){

        this.deleteFile("sessionapp.txt");

        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    goLogInScreen();
                } else {
                    Toast.makeText(MenuLogeado.this, "no se pudo ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
