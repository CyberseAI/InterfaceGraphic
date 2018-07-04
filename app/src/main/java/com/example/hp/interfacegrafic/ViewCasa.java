package com.example.hp.interfacegrafic;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hp.interfacegrafic.DATA.DataApp;
import com.example.hp.interfacegrafic.DATA.UserData;
import com.example.hp.interfacegrafic.ItemMenu.CasaIdDeatalle;
import com.example.hp.interfacegrafic.ItemMenu.HIloImg;
import com.example.hp.interfacegrafic.ItemMenu.ItemMenuStructure;
import com.example.hp.interfacegrafic.ItemMenu.LoaderImg;
import com.example.hp.interfacegrafic.ItemMenu.OnLoadCompleImg;
import com.example.hp.interfacegrafic.ItemMenu.UserDetalle;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ViewCasa extends AppCompatActivity implements OnLoadCompleImg
{
    private Context root;

    private Context btnG; // para el boton de gallery

    public static int size; //este es el cantidad imagenes
    public  String idCasa;   //id de la casa
    //public  String idMapa; ///recuperando id desde mapas

    protected TextView detalleTipo, detalleDescripcon, detalleEstado, detalleSuperficie,
            detalleRegion, detalleCantidadCuartos,detallePrecio;
    protected ImageView detalleImg;

    protected ViewCasa ROOT;

    protected CasaIdDeatalle Data;

    public Context btnuserdatelle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        ROOT= this;
        btnuserdatelle = this;

        btnG = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_casa);
        root = this;
        loadComponents();





        if (this.getIntent().getExtras() != null) {
            idCasa = this.getIntent().getExtras().getString("id");
            size = this.getIntent().getExtras().getInt("size");
            //idMapa = this.getIntent().getExtras().getString("id");
        }

        loadViewcomponets();
        loadAsinkData();

        loadGallery(); // para el gallery

        loadUserDetallecomponents();
    }



    private void loadUserDetallecomponents()  {
        Button btnUser = (Button)this.findViewById(R.id.btnProp);


        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userUrl = Data.getUser().toString(); ///para sacar el usria id
                Intent user = new Intent(btnuserdatelle, User.class);
                user.putExtra("user",userUrl);
                //user.putExtra("user", user);
                //String userUrl = getString();
                //user.putExtra("url",userUrl);

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
                                //String url = DataApp.HOST + (String)response.getJSONArray("gallery").get(0);
                                JSONArray listGalery= response.getJSONArray("gallery");
                                ArrayList<String> urlLists = new ArrayList<String>();
                                for (int j = 0; j < listGalery.length(); j++){
                                    urlLists.add(DataApp.HOST + listGalery.getString(j));
                                }
                                Data = new CasaIdDeatalle(tipo, esatado,precio,region,descripcion,
                                        cantidadCuartos,superficie,user,id,urlLists);
                                ROOT.informacion();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
    }

    private void informacion(){

        this.detalleDescripcon.setText(Data.getDescripcion());
        this.detalleTipo.setText(Data.getTipo());
        this.detalleEstado.setText(Data.getEsatado());
        this.detalleSuperficie.setText(Data.getSuperficie());
        this.detalleRegion.setText(Data.getRegion());
        this.detalleCantidadCuartos.setText(Data.getCantidadCuartos());
        this.detallePrecio.setText(Data.getPrecio());
        /*HIloImg imgLoad = new HIloImg();
        imgLoad.execute(Data.getImg());
        imgLoad.setLoadImage(this.detalleImg, this);*/

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

       /* if (this.Data.getImg() == null) {
            //Load IMG
            LoaderImg loader = new LoaderImg();
            loader.setOnloadCompleteImg(detalleImg , 0,this);
            loader.execute(this.Data.getUrl().get(0));
        } else {
            detalleImg.setImageBitmap(this.Data.getImg().get(0));
        }*/


    }

    private void loadComponents()
    {
        Button btnMap = (Button)this.findViewById(R.id.btnUbic);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent map = new Intent(root, MapsFragment.class);

                root.startActivity(map);
            }
        });
    }

    private void loadGallery() {
        Button btnGaller = (Button)this.findViewById(R.id.btnGallery);


        btnGaller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 ///para sacar el usria id
                Intent iduser = new Intent(btnG, GaleriaIMG.class);
                iduser.putExtra("id",size);
                btnG.startActivity(iduser);
            }
        });


    }


    @Override
    public void setLoadImage(ImageView container, Bitmap img) {
        container.setImageBitmap(img);
    }

    @Override
    public void OnloadCompleteImgResult(ImageView img, int position, Bitmap imgsourceimg) {

    }
}
