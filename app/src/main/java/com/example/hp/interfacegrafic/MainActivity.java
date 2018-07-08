package com.example.hp.interfacegrafic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hp.interfacegrafic.DATA.UserData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity
{
    private Context root;

    //private Context btnInmueble;
    //private Context btnInmuebleId;
    GridLayout mainGrid ;
    Spinner filtroEstado;
    Spinner filtroTipo;
    public String valor;
    public String valor2;

    private  Context bntFiltro;

    private Context btnBuscar;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkSessionFile();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        root=this;
        filtroTipo =(Spinner) findViewById(R.id.spinnerCasaTipos);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.tipos,
                    android.R.layout.simple_spinner_item);
        filtroTipo.setAdapter(adapter);
        filtroTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                 valor = parent.getItemAtPosition(position).toString();
                 loadFiltroComponents(valor);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        filtroEstado =(Spinner) findViewById(R.id.spinnerCasaEstado);
        ArrayAdapter<CharSequence> adapterEstado = ArrayAdapter.createFromResource(this,R.array.estado,
                android.R.layout.simple_spinner_item);
        filtroEstado.setAdapter(adapterEstado);
        filtroEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                valor2 = parent.getItemAtPosition(position).toString();
                UserData.FEstado = valor2;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        root=this;
        loadComponents();

        btnBuscar = this;
        loadBtnBuscar();

        bntFiltro = this;


        //btnInmueble = this;
       // loadbtnInmueble();

        //btnInmuebleId = this;
        //loadIdInmueble ();
    }

    private void loadFiltroComponents(final String valor) {
        final String val=valor;
        UserData.FTipo = val;
        Button btnMAs = (Button)this.findViewById(R.id.button5);
        btnMAs.setOnClickListener(new View.OnClickListener(){

           @Override
            public void onClick(View v) {
               Intent ubic = new Intent(bntFiltro, Filtro_casas.class);
               //ubic.putExtra("tipo",va);
               bntFiltro.startActivity(ubic);

            }
        });

    }

   /* private void loadIdInmueble() {
        Button btnB = (Button)this.findViewById(R.id.btnCasaUserid13);
        btnB.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(root,"aqui esta ",Toast.LENGTH_SHORT).show();
                FragmentManager fragment = getSupportFragmentManager();
                //<<<
                UserListaId casa = new UserListaId();

                fragment.beginTransaction().add(R.id.contenedoruserCasaid,casa).commit();


            }
        });
    }*/

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

    /*private void loadbtnInmueble() {
        Button btnI = (Button)this.findViewById(R.id.btnInmuebleAdd);
        btnI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(btnInmueble, FormCasas.class);
                btnInmueble.startActivity(login);
            }
        });
    }*/

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
