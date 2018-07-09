package com.example.hp.interfacegrafic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hp.interfacegrafic.DATA.DataApp;
import com.example.hp.interfacegrafic.DATA.UserData;
import com.example.hp.interfacegrafic.ItemMenu.ItemMenuStructure;
import com.example.hp.interfacegrafic.ItemMenu.MenuBaseAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Filtro_casas extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView LIST;
    private ArrayList<ItemMenuStructure> LISTINFO;
    private Context root;
    public String TIPO;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().build();
        StrictMode.setThreadPolicy( policy );

        root = this;

        LISTINFO = new ArrayList<ItemMenuStructure>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro_casas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        loadInitialRestData();

        //if (this.getIntent().getExtras() != null) {
            //TIPO = this.getIntent().getExtras().getString("tipo");
        //}
    }

    private void loadInitialRestData(){
        AsyncHttpClient client = new AsyncHttpClient( );
        client.get("http://192.168.43.150:7777/api/v1.0/filtro_tipo/?tipo="+ UserData.FTipo + "&estado=" +
                        UserData.FEstado,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray list = (JSONArray) response.get("info");
                    for (int i = 0; i<list.length(); i++){
                        JSONObject obj = list.getJSONObject( i );
                        String tipo = obj.getString("tipo");
                        String estado = obj.getString("estado");
                        String precio = obj.getString("precio");
                        String ciudad = obj.getString("ciudad");
                        String cantidadCuartos = obj.getString("cantidadCuartos");
                        String cantidadBaños = obj.getString("cantidadBanios");
                        double lat = obj.getDouble("lat");
                        double lon = obj.getDouble("lon");
                        String correo = obj.getString("correo");
                        String id = obj.getString("_id");
                        JSONArray listGalery= obj.getJSONArray("gallery");
                        ArrayList<String> urlLists = new ArrayList<String>();
                        for (int j = 0; j < listGalery.length(); j++){
                            urlLists.add("http://192.168.43.150:7777" + listGalery.getString(j));
                        }

                        LISTINFO.add(new ItemMenuStructure(tipo, estado, precio, ciudad,
                                "", "", "", cantidadCuartos, cantidadBaños,
                                "", "", lat, lon, correo, "", id, urlLists));
                    }
                    loadConponents();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response){

            }
        });
    }
    private void loadConponents() {
        LIST = (ListView) this.findViewById(R.id.Filtrodecasas124);
        //LISTINFO.add( new ItemList( "https://koreaboo-cdn.storage.googleapis.com/2017/06/yoona-2015.jpg", "prueva", "159", "move" ));
        //EditText search = (EditText)this.findViewById( R.id.searchmovie );
        //eventos
        MenuBaseAdapter adapter = new MenuBaseAdapter(this, LISTINFO);
        LIST.setAdapter(adapter);
        LIST.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String datalist = LISTINFO.get(position).getUrl();
        //UserData.IDCasa = datalist;
        Intent intent = new Intent(this, FiltroCasasView.class);
        intent.putExtra("size", position);
        intent.putExtra("id", datalist);
        this.startActivity(intent);
    }
}
