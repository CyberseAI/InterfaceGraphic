package com.example.hp.interfacegrafic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.interfacegrafic.DATA.DataApp;
import com.example.hp.interfacegrafic.DATA.UserData;
import com.example.hp.interfacegrafic.ItemMenu.CasaIdDeatalle;
import com.example.hp.interfacegrafic.ItemMenu.OnLoadCompleImg;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
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

public class PatchCasa extends AppCompatActivity implements OnLoadCompleImg {

    private Context root;
    Button guardar;

    private Context btnG; // para el boton de gallery

    public static int size; //este es el cantidad imagenes
    public  String idCasa;   //id de la casa
    //public  String idMapa; ///recuperando id desde mapas
    protected TextView detalleTipo,detalleEstado;
    protected EditText detalleDescripcon, detalleSuperficie,
            detalleRegion, detalleCantidadCuartos,detallePrecio;
    protected Spinner Spinnert,SpinnerE ;
    protected ImageView detalleImg;

    protected PatchCasa ROOT;

    protected CasaIdDeatalle Data;

    public Context btnuserdatelle;

    HttpClient cliente;
    HttpPatch patch;
    List<NameValuePair> lista;

    public  String Tipo;
    public  String Estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ROOT= this;
        btnuserdatelle = this;

        btnG = this;
        root = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patch_casa);

        if (this.getIntent().getExtras() != null) {
            size = this.getIntent().getExtras().getInt("size");
            idCasa = this.getIntent().getExtras().getString("id");
        }
        loadComponents();

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
                Intent user = new Intent(btnuserdatelle, PatchUser.class);
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
                            Data = new CasaIdDeatalle(tipo, esatado,precio,region,descripcion,
                                    cantidadCuartos,superficie,user,id,lat,lon,urlLists);

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
    }

    private void loadViewcomponets() {
        //<<<<<<<<<<<<<<<<<<<<<<
        this.detalleDescripcon = (EditText) this.findViewById(R.id.edidTexDescripcion12);
        this.detalleTipo = (TextView) this.findViewById(R.id.edidtextTipo12);
        this.detalleEstado = (TextView) this.findViewById(R.id.edidtextEstado12);

       this.Spinnert = (Spinner) this.findViewById(R.id.SpineerTipo12);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.tipos,
                android.R.layout.simple_spinner_item);
        Spinnert.setAdapter(adapter);
        Spinnert.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                detalleTipo.setText(parent.getItemAtPosition(position).toString());
                //parent.setTag(Tipo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        this.SpinnerE = (Spinner) this.findViewById(R.id.SpinnerEstado);
        ArrayAdapter<CharSequence> adapterEstado = ArrayAdapter.createFromResource(this,R.array.estado,
                android.R.layout.simple_spinner_item);
        SpinnerE.setAdapter(adapterEstado);
        SpinnerE.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                detalleEstado.setText(parent.getItemAtPosition(position).toString());
                //parent.setTag(Estado);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        this.detalleSuperficie = (EditText) this.findViewById(R.id.edidtextTamaño12);
        this.detalleRegion = (EditText) this.findViewById(R.id.edidtextRegion12);
        this.detalleCantidadCuartos = (EditText) this.findViewById(R.id.edidtexthabitaciones12);
        this.detallePrecio = (EditText) this.findViewById(R.id.edidtextprecio12);

        this.detalleImg = (ImageView)  this.findViewById(R.id.detalleImg1);

        guardar = (Button)findViewById(R.id.guardarBtn);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (detallePrecio.getText().toString().equals("")){
                    Toast.makeText(PatchCasa.this, "Precio no puede ir vacio",
                            Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(PatchCasa.this,"Exito",Toast.LENGTH_LONG).show();
                    new PatchCasa.EnviarDatos(PatchCasa.this).execute();
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
                        Intent btnG = new Intent(PatchCasa.this, MainActivity.class );
                        PatchCasa.this.startActivity(btnG);
                        // en este caso yo lo estoy llevando a formcasas

                        //Arreglar con el rescate de datos
                        //>>>>
                        //name.("");
                        //viewApellido.setText("");
                        //viewEmail.setText("");
                        //>>>>
                        detalleDescripcon.setText("");
                        detalleTipo.setText("");
                        detalleEstado.setText("");
                        detalleSuperficie.setText("");
                        detalleRegion.setText("");
                        detalleCantidadCuartos.setText("");
                        detallePrecio.setText("");
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
        patch = new HttpPatch(DataApp.HOST+"/api/v1.0/inmueble_patch/"+ idCasa);
        lista = new ArrayList<NameValuePair>(7);
        //>>>                                               >>>
        /** Cambiar con los datos rescatados
         lista.add(new BasicNameValuePair("nombre", txtnombre.getText().toString().trim()));
         lista.add(new BasicNameValuePair("apellido",viewApellido.getText().toString().trim()));
         */
        //>>>
        lista.add(new BasicNameValuePair("tipo",detalleTipo.getText().toString().trim()));
        lista.add(new BasicNameValuePair("estado",detalleEstado.getText().toString().trim()));
        lista.add(new BasicNameValuePair("precio",detallePrecio.getText().toString().trim()));
        lista.add(new BasicNameValuePair("region",detalleRegion.getText().toString().trim()));
        lista.add(new BasicNameValuePair("descripcion",detalleDescripcon.getText().toString().trim()));
        lista.add(new BasicNameValuePair("cantidadCuartos",detalleCantidadCuartos.getText().toString().trim()));
        lista.add(new BasicNameValuePair("superficie",detalleSuperficie.getText().toString().trim()));
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




    private void loadGallery() {
        Button btnGaller = (Button)this.findViewById(R.id.btnGalleryPacht);


        btnGaller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String id = Data.getUrl().get(0);
                Intent iduser = new Intent(btnG, GaleriaIMG.class);
                iduser.putExtra("id",size);
                btnG.startActivity(iduser);
            }
        });

    }

    private void loadComponents()
    {
        Button btnMap = (Button)this.findViewById(R.id.btnUbic);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idm = Data.getId();
                UserData.ID = idm;
                Intent map = new Intent(root, LatLonMaps.class);

                root.startActivity(map);
            }
        });
    }



    @Override
    public void setLoadImage(ImageView container, Bitmap img) {

    }

    @Override
    public void OnloadCompleteImgResult(ImageView img, int position, Bitmap imgsourceimg) {

    }
}
