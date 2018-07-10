package com.example.hp.interfacegrafic;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

public class FiltroCasa_Precio extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView LIST;
    private Context root;
    public String TIPO;
    public TextView tex1;
    public TextView tex2;
    public String min;
    public String max;
    Spinner spn1;
    Spinner spn2;
    max as = new max();
    int n1;
    int n2;
    EditText m;
    EditText n;
    Button search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DataApp.LISTDATA = new ArrayList<ItemMenuStructure>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().build();
        StrictMode.setThreadPolicy( policy );
        root = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro_casa__precio);

        n = (EditText)findViewById(R.id.min);
        m = (EditText)findViewById(R.id.max);
        search = (Button)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String mn = n.getText().toString();
                String mx = m.getText().toString();

                if(mn.isEmpty() || mx.isEmpty())
                {
                    Toast.makeText(root,"Llene campos man >:v", Toast.LENGTH_LONG);
                }
                else
                {
                    int maxi = Integer.parseInt(mx);

                    int mini = Integer.parseInt(mn);

                    AsyncHttpClient client = new AsyncHttpClient( );
                    client.get(DataApp.HOST + "/api/v1.0/filtro_precio/?max="+ mini + "&min="+ maxi ,new JsonHttpResponseHandler(){
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
                                        urlLists.add(DataApp.HOST + listGalery.getString(j));
                                    }

                                    DataApp.LISTDATA.add(new ItemMenuStructure(tipo, estado, precio, ciudad,
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

            }
        });


    }



    private void loadInitialRestData(int n1, int n2)
    {

    }
    private void loadConponents() {
        LIST = (ListView) this.findViewById(R.id.liastacasaprecio);
        MenuBaseAdapter adapter = new MenuBaseAdapter(this, DataApp.LISTDATA);
        LIST.setAdapter(adapter);
        LIST.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String datalist = DataApp.LISTDATA.get(position).getUrl();
        Intent intent = new Intent(this, FiltroCasasView.class);
        intent.putExtra("size", position);
        intent.putExtra("id", datalist);
        this.startActivity(intent);

    }

}
