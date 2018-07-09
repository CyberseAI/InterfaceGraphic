package com.example.hp.interfacegrafic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.interfacegrafic.DATA.DataApp;
import com.example.hp.interfacegrafic.ItemMenu.CasaIdDeatalle;
import com.example.hp.interfacegrafic.Mapas.MapsUbicUser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class FiltroCasasView extends AppCompatActivity {

    private Context root;

    private Context btnG; // para el boton de gallery

    public static int size; //este es el cantidad imagenes
    public  String idCasa;   //id de la casa
    //public  String idMapa; ///recuperando id desde mapas

    protected TextView detalleTipo1, detalleDescripcon1, detalleEstado1, detalleSuperficie1,
            detalleRegion1, detalleCantidadCuartos1,detallePrecio1;
    protected ImageView detalleImg12;

    protected FiltroCasasView ROOT;

    protected CasaIdDeatalle DataFiltro;

    public Context btnuserdatelle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ROOT= this;
        btnuserdatelle = this;

        btnG = this;
        root = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro_casas_view);

        if (this.getIntent().getExtras() != null) {
            idCasa = this.getIntent().getExtras().getString("id");

            size = this.getIntent().getExtras().getInt("size");
            //idMapa = this.getIntent().getExtras().getString("id");
        }
        loadComponents();
        loadViewcomponets();
        loadAsinkData();

        loadGallery(); // para el gallery

        loadUserDetallecomponents();

    }

    private void loadUserDetallecomponents()  {
        Button btnUser = (Button)this.findViewById(R.id.btnProp4);
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userUrl = DataFiltro.getUser().toString(); ///para sacar el usria id
                Intent user = new Intent(btnuserdatelle, User.class);
                user.putExtra("user",userUrl);
                btnuserdatelle.startActivity(user);
            }
        });

    }
    private void loadAsinkData() {
        AsyncHttpClient casadetalle= new AsyncHttpClient();
        casadetalle.get(DataApp.inmueble_id +"/"+ idCasa,
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
                            String id = response.getString("_id");
                            Number lat = response.getDouble("lat");
                            Number lon = response.getDouble("lon");
                            //String url = DataApp.HOST + (String)response.getJSONArray("gallery").get(0);
                            JSONArray listGalery= response.getJSONArray("gallery");
                            ArrayList<String> urlLists = new ArrayList<String>();
                            for (int j = 0; j < listGalery.length(); j++){
                                urlLists.add(DataApp.HOST + listGalery.getString(j));
                            }
                            DataFiltro = new CasaIdDeatalle(tipo, esatado,precio,region,descripcion,
                                    cantidadCuartos,superficie,user,id,lat,lon,urlLists);
                            ROOT.informacion();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
    private void informacion(){

        this.detalleDescripcon1.setText(DataFiltro.getDescripcion());
        this.detalleTipo1.setText(DataFiltro.getTipo());
        this.detalleEstado1.setText(DataFiltro.getEsatado());
        this.detalleSuperficie1.setText(DataFiltro.getSuperficie());
        this.detalleRegion1.setText(DataFiltro.getRegion());
        this.detalleCantidadCuartos1.setText(DataFiltro.getCantidadCuartos());
        this.detallePrecio1.setText(DataFiltro.getPrecio());
        /*HIloImg imgLoad = new HIloImg();
        imgLoad.execute(Data.getImg());
        imgLoad.setLoadImage(this.detalleImg, this);*/

    }
    private void loadViewcomponets() {

        this.detalleDescripcon1 = (TextView) this.findViewById(R.id.detalleDescripcon4);
        this.detalleTipo1 = (TextView) this.findViewById(R.id.detalleTipo4);
        this.detalleEstado1 = (TextView) this.findViewById(R.id.detalleEstado4);
        this.detalleSuperficie1 = (TextView) this.findViewById(R.id.detalleSuperficie4);
        this.detalleRegion1 = (TextView) this.findViewById(R.id.detalleRegion4);
        this.detalleCantidadCuartos1 = (TextView) this.findViewById(R.id.detalleCantidadCuartos4);
        this.detallePrecio1 = (TextView) this.findViewById(R.id.detallePrecio4);

        this.detalleImg12 =  (ImageView)  this.findViewById(R.id.detalleImg4);
    }
    public void loadComponents()
    {
        Button btnMap = (Button)this.findViewById(R.id.btnUbic4);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double lat = (Double) DataFiltro.getLat();
                Double lon = (Double) DataFiltro.getLon();
                String tipo = DataFiltro.getTipo();
                Intent map = new Intent(root, MapsUbicUser.class);
                map.putExtra("lat",lat);
                map.putExtra("lon",lon);
                map.putExtra("tipo",tipo);
                root.startActivity(map);
            }
        });
    }
    private void loadGallery() {
        Button btnGaller = (Button)this.findViewById(R.id.btnGallery4);


        btnGaller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///para sacar el usria id
                Intent iduser = new Intent(btnG, GaleriaIMG.class);
                iduser.putExtra("id", size);
                btnG.startActivity(iduser);
            }
        });


    }
}
