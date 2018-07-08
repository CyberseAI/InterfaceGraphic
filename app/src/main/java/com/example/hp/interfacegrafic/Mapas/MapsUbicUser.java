package com.example.hp.interfacegrafic.Mapas;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.hp.interfacegrafic.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsUbicUser extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public static Double lat;
    public static Double lon;
    private String tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_ubic_user);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (this.getIntent().getExtras() != null) {

            lat = this.getIntent().getExtras().getDouble("lat");
            lon = this.getIntent().getExtras().getDouble("lon");
            tipo = this.getIntent().getExtras().getString("tipo");

        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Double Latitude = lat;
        Double longitude = lon;
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng potosi = new LatLng(Latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(potosi).title(tipo));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(potosi, 14));
    }
}
