package com.example.hp.interfacegrafic;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;


import com.example.hp.interfacegrafic.DATA.DataApp;
import com.example.hp.interfacegrafic.DATA.UserData;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class VerUbica extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {
    private GoogleMap mMap;
    private MarkerOptions marker;
    private Context root;
    private Marker marcador;
    private Number lat;
    private Number lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = this;

        setContentView(R.layout.activity_ver_ubica);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MapView map = this.findViewById(R.id.mapView);
        if (map != null) {
            map.onCreate(null);
            map.onResume();
            // Set the map ready callback to receive the GoogleMap object
            map.getMapAsync(this);
        }



       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(VerUbica.this,marcador.getPosition().toString(),Toast.LENGTH_LONG).show();

                if (marcador != null) {
                    AsyncHttpClient client = new AsyncHttpClient();
                    RequestParams params = new RequestParams();
                    params.put("lat",marcador.getPosition().latitude);
                    params.put("lon", marcador.getPosition().longitude);
                    client.patch(DataApp.REST_HOME_PATCH + UserData.ID, params, new JsonHttpResponseHandler(){

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Intent formu = new Intent(root, MainActivity.class);
                            root.startActivity(formu);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            super.onFailure(statusCode, headers, responseString, throwable);
                        }
                    });

                }
            }
        });*/
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Toast.makeText(this,marcador.getPosition().toString(),Toast.LENGTH_LONG).show();

        mMap = googleMap;
        detalles(googleMap);
        //Intent intentMap=getIntent();
        //LatLng detallePosCasa=new LatLng(
        //intentMap.getExtras().getDouble("lon"),
        //intentMap.getExtras().getDouble("lat"));

        //Add a marker in Sydney and move the camera
        //LatLng potosi = new LatLng(-19.578297, -65.758633);
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(potosi, 14));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(detallePosCasa, 14));
        //mMap.addMarker(new MarkerOptions().title("Detalle casa").position(detallePosCasa));
        //mi ubicacion
        //if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //return;
        //}
        //mMap.setMyLocationEnabled(true);
        //mMap.getUiSettings().setMyLocationButtonEnabled(false);
    }

    public void detalles(GoogleMap googleMap){
        mMap=googleMap;

        //final LatLng potosi=new LatLng(-19.578297, -65.758633);
        //mMap.addMarker(new MarkerOptions().position(potosi).title("potosi"));
        Intent intentMap=getIntent();
        LatLng detallePosCasa=new LatLng(
                Double.parseDouble(String.valueOf(intentMap.getExtras().getDouble("lat"))),
                Double.parseDouble(String.valueOf(intentMap.getExtras().getDouble("lon"))));
        mMap.addMarker(new MarkerOptions().position(detallePosCasa).title("detalle casa"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(detallePosCasa,14));
    }

    @Override
    public void onMapClick(LatLng latLng) {
    }

   /* @Override
    public void onMapClick(LatLng latLng) {

        mMap.clear();
        marker = new MarkerOptions();
        marker.position(latLng);
        marker.title("Inmueble");
        marker.draggable(true);
        marcador=mMap.addMarker(marker);
    }*/


}
