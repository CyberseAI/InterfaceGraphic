package com.example.hp.interfacegrafic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hp.interfacegrafic.DATA.DataApp;
import com.example.hp.interfacegrafic.ItemMenu.CasaIdDeatalle;
import com.example.hp.interfacegrafic.ItemMenu.ItemMenuStructure;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ViewCasa extends AppCompatActivity
{
    private Context root;

    public String datalist; //este es el id
    protected TextView detalleTipo, detalleDescripcon, detalleEstado, detalleSuperficie,
            detalleRegion, detalleCantidadCuartos,detallePrecio;
    protected ImageView detalleImg;

    protected ViewCasa ROOT;

    protected CasaIdDeatalle Data;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        ROOT= this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_casa);
        root = this;
        loadComponents();

        datalist = this.getIntent().getExtras().getString("url");
        loadViewcomponets();
        loadAsinkData();
    }

    private void loadAsinkData() {
        AsyncHttpClient casadetalle= new AsyncHttpClient();
        casadetalle.get(DataApp.inmueble_id +"/"+ datalist,
                    new JsonHttpResponseHandler(){
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            try {
                                String tipo = response.getString("tipo");
                                String esatado = response.getString("estado");
                                String precio = response.getString("precio");
                                String region = response.getString("region");
                                String descripcion = response.getString("descripcion");
                                String cantidadCuartos = response.getString("cantidadCuartos");
                                String superficie = response.getString("superficie");
                                String user = response.getString("user");
                                Data = new CasaIdDeatalle(tipo, esatado,precio,region,descripcion,
                                        cantidadCuartos,superficie,user);
                                ROOT.informacion();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
    }

    private void informacion(){

        this.detalleDescripcon.setText(Data.getDescripcion());
        this.detalleTipo.setText(Data.getTipo()); ;
        this.detalleEstado.setText(Data.getEsatado()); ;
        this.detalleSuperficie.setText(Data.getSuperficie());
        this.detalleRegion.setText(Data.getRegion());
        this.detalleCantidadCuartos.setText(Data.getCantidadCuartos()); ;
        this.detallePrecio.setText(Data.getPrecio()); ;

    }

    private void loadViewcomponets() {

        this.detalleDescripcon = (TextView) this.findViewById(R.id.detalleDescripcon);
        this.detalleTipo = (TextView) this.findViewById(R.id.detalleTipo);
        this.detalleEstado = (TextView) this.findViewById(R.id.detalleEstado);
        this.detalleSuperficie = (TextView) this.findViewById(R.id.detalleSuperficie);
        this.detalleRegion = (TextView) this.findViewById(R.id.detalleRegion);
        this.detalleCantidadCuartos = (TextView) this.findViewById(R.id.detalleCantidadCuartos);
        this.detallePrecio = (TextView) this.findViewById(R.id.detallePrecio);

        this.detalleImg = (ImageView)  this.findViewById(R.id.detalleImg);


    }

    private void loadComponents()
    {
        Button btnMap = (Button)this.findViewById(R.id.btnUbic);
        Button btnUser = (Button)this.findViewById(R.id.btnProp);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent map = new Intent(root, Maps.class);
                root.startActivity(map);
            }
        });
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                Intent user = new Intent(root, User.class);
                root.startActivity(user);
            }
        });
    }

}
