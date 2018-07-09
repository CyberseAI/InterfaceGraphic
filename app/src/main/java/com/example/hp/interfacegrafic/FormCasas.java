package com.example.hp.interfacegrafic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.interfacegrafic.DATA.DataApp;
import com.example.hp.interfacegrafic.DATA.UserData;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

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
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

public class FormCasas extends AppCompatActivity implements View.OnClickListener
{
    private Context root;
    public  String Tipo;
    public String Estado;
    Spinner tipo;
    Spinner estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        root = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_casas);
        loadComponents();
        tipo  = (Spinner) this.findViewById(R.id.txt_tipo);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.tipos,
                android.R.layout.simple_spinner_item);
        tipo.setAdapter(adapter);
        tipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Tipo = parent.getItemAtPosition(position).toString();
                parent.setTag(Tipo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        estado = (Spinner)this.findViewById(R.id.txt_estado);
        ArrayAdapter<CharSequence> adapterEstado = ArrayAdapter.createFromResource(this,R.array.estado,
                android.R.layout.simple_spinner_item);
        estado.setAdapter(adapterEstado);
        estado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Estado = parent.getItemAtPosition(position).toString();
                parent.setTag(Estado);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void loadComponents()
    {
        Button btnImg = (Button)this.findViewById(R.id.btn_enviarcasa);
        btnImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //TextView street = (TextView)this.findViewById(R.id.street);


        EditText precio= (EditText)this.findViewById(R.id.txt_precio);
        EditText ciudad = (EditText)this.findViewById(R.id.txt_ciudad);
        EditText region = (EditText)this.findViewById(R.id.txt_Region);
        EditText ubicacion = (EditText) this.findViewById(R.id.txtViewUbucacionget);
        EditText descripcion = (EditText)this.findViewById(R.id.txt_Descripcion);
        EditText cantidadCuartos = (EditText)this.findViewById(R.id.txt_cuartos);
        EditText cantidadBaños = (EditText)this.findViewById(R.id.txt_banio);
        EditText garage = (EditText)this.findViewById(R.id.txt_garje);
        EditText superficie = (EditText)this.findViewById(R.id.txt_superficie);
        TextView lat = (TextView)this.findViewById(R.id.txtLatView);
        TextView lon = (TextView)this.findViewById(R.id.txtLonView);
        EditText correo = (EditText) this.findViewById(R.id.txt_correo);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        //params.put("street", street.getText());

        params.put("tipo", tipo.getTag());
        params.put("estado",estado.getTag());
        params.put("precio",precio.getText());
        params.put("ciudad",ciudad.getText());
        params.put("region",region.getText());
        params.put("ubicacion",ubicacion.getText());
        params.put("descripcion",descripcion.getText());
        params.put("cantidadCuartos",cantidadCuartos.getText());
        params.put("cantidadBanios",cantidadBaños.getText());
        params.put("garage",garage.getText());
        params.put("superficie",superficie.getText());
        params.put("lat", lat.getText());
        params.put("lon", lon.getText());
        params.put("correo",correo.getText());

        client.post(DataApp.HOST+"/api/v1.0/inmuebles", params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String msn = response.getString("msn");
                    String id = response.getString("id");
                    UserData.ID = id;
                    if (msn != null) {

                        Intent camera = new Intent(root, LoadImage.class);
                        root.startActivity(camera);
                    } else {
                        Toast.makeText(root, "ERROR AL enviar los datos", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONObject) was not overriden, but callback was received");
            }
        });

    }
}
