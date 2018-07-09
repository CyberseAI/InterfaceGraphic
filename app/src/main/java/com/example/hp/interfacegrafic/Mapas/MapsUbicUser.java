package com.example.hp.interfacegrafic.Mapas;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.hp.interfacegrafic.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Double Latitude = lat;
        Double longitude = lon;
        mMap = googleMap;
        hospitBrac(googleMap);
        caja(googleMap);
        //seguroUni(googleMap);
        berrios(googleMap);
        medinaceli(googleMap);
        litoral(googleMap);
        straPotosi(googleMap);
        pich(googleMap);
        pablo2(googleMap);
        stAna(googleMap);
        stMaria(googleMap);
        bancario(googleMap);
        crisMaestro(googleMap);
        stSucre(googleMap);
        stRosa(googleMap);
        modOmi(googleMap);
        calero(googleMap);
        francis(googleMap);
        simeon(googleMap);
        diviMaes(googleMap);
        copaca(googleMap);
        octu(googleMap);
        uniAto(googleMap);
        uniSavio(googleMap);
        andresAn(googleMap);
        norA(googleMap);
        ciu(googleMap);
        humIpo(googleMap);
        ervi(googleMap);
        meji(googleMap);

        // Add a marker in Sydney and move the camera
        LatLng potosi = new LatLng(Latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(potosi).title(tipo));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(potosi, 14));
    }
    public void hospitBrac(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.5828234,-65.7679582);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Daniel Bracamonte").icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital)));
    }
    public void caja(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.5826805,-65.7604084);
        mMap.addMarker(new MarkerOptions().position(potosi).title("caja nacional").icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital)));
    }
    /*public void seguroUni(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.5812642,-65.7592799);
        mMap.addMarker(new MarkerOptions().position(potosi).title("seguro universitario").icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital)));
    }*/

    public void berrios(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.5804884,-65.7639563);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Jose David Berrios").icon(BitmapDescriptorFactory.fromResource(R.drawable.cole)));
    }
    public void medinaceli(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.5821275,-65.7597858);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Carlos Medinaceli").icon(BitmapDescriptorFactory.fromResource(R.drawable.cole)));
    }
    public void litoral(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.5816281,-65.7570416);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Litoral").icon(BitmapDescriptorFactory.fromResource(R.drawable.cole)));
    }
    public void straPotosi(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.5853397,-65.7555717);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Liceo de señoritas Potosi").icon(BitmapDescriptorFactory.fromResource(R.drawable.cole)));
    }
    public void pich(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.5887907,-65.7552119);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Pichincha").icon(BitmapDescriptorFactory.fromResource(R.drawable.cole)));
    }
    public void pablo2(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.5747644,-65.7682921);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Juan Pablo II").icon(BitmapDescriptorFactory.fromResource(R.drawable.cole)));
    }
    public void stAna(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.5816344,-65.7605237);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Santa Ana").icon(BitmapDescriptorFactory.fromResource(R.drawable.cole)));
    }
    public void stMaria(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.5829548,-65.760684);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Particular Santa Maria").icon(BitmapDescriptorFactory.fromResource(R.drawable.cole)));
    }
    public void bancario(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.5845898,-65.7540227);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Bancario").icon(BitmapDescriptorFactory.fromResource(R.drawable.cole)));
    }
    public void crisMaestro(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.5845898,-65.7540227);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Unidad Educativa Cristo Maestro").icon(BitmapDescriptorFactory.fromResource(R.drawable.cole)));
    }
    public void stSucre(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.5879659,-65.7520057);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Liceo Sucre").icon(BitmapDescriptorFactory.fromResource(R.drawable.cole)));
    }
    public void stRosa(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.5889899,-65.7567967);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Liceo Santa Rosa").icon(BitmapDescriptorFactory.fromResource(R.drawable.cole)));
    }
    public void modOmi(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.5896289,-65.7560938);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Modesto Omiste").icon(BitmapDescriptorFactory.fromResource(R.drawable.cole)));
    }
    public void calero(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.5881008,-65.7532295);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Juan Manuel Calero").icon(BitmapDescriptorFactory.fromResource(R.drawable.cole)));
    }
    public void francis(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.5909666,-65.7547411);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Particular Franciscano").icon(BitmapDescriptorFactory.fromResource(R.drawable.cole)));
    }
    public void simeon(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.5794005,-65.7533277);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Simeon Roncal").icon(BitmapDescriptorFactory.fromResource(R.drawable.cole)));
    }
    public void diviMaes(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.565521,-65.7710625);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Unidad Educativa Divino Maestro").icon(BitmapDescriptorFactory.fromResource(R.drawable.cole)));
    }
    public void copaca(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.587849,-65.7590546);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Unidad Educativa Copacabana").icon(BitmapDescriptorFactory.fromResource(R.drawable.cole)));
    }
    public void octu(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.5828559,-65.7622397);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Unidad Educativa 31 de Octubre").icon(BitmapDescriptorFactory.fromResource(R.drawable.cole)));
    }
    public void uniAto(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.584154,-65.7588667);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Universidad Autonoma Tomas Frias").icon(BitmapDescriptorFactory.fromResource(R.drawable.universidad)));
    }
    public void uniSavio(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.5688582,-65.7666385);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Universidad Privada Domingo Savio").icon(BitmapDescriptorFactory.fromResource(R.drawable.universidad)));
    }
    public void andresAn(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.5723004,-65.7581628);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Mariscal Andres de Santa Cruz").icon(BitmapDescriptorFactory.fromResource(R.drawable.cole)));
    }
    public void norA(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.5718849,-65.7543473);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Normal Eduardo Avaroa").icon(BitmapDescriptorFactory.fromResource(R.drawable.universidad)));
    }
    public void ciu(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.5575442,-65.7653738);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Ciudadela Universitaria").icon(BitmapDescriptorFactory.fromResource(R.drawable.universidad)));
    }
    public void humIpo(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.5679102,-65.750094);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Unidad Educativa Humberto Iporre Salinas").icon(BitmapDescriptorFactory.fromResource(R.drawable.cole)));
    }
    public void ervi(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.5748746,-65.763558);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Unidad Educativa Enrique Viaña").icon(BitmapDescriptorFactory.fromResource(R.drawable.cole)));
    }
    public void meji(GoogleMap googleMap){
        mMap=googleMap;
        final LatLng potosi=new LatLng(-19.5862223,-65.7459071);
        mMap.addMarker(new MarkerOptions().position(potosi).title("Unidad Educativa Mejillones").icon(BitmapDescriptorFactory.fromResource(R.drawable.cole)));
    }
}
