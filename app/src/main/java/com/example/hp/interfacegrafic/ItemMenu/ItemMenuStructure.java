package com.example.hp.interfacegrafic.ItemMenu;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class ItemMenuStructure {

    private String tipo;
    private String estado;
    private String precio;
    private String ciudad;
    private String region;
    private String ubicacion;
    private String descripcion;
    private String cantidadCuartos;
    private String cantidadBaños;
    private String garage;
    private String superficie;
    private double lat;
    private double lon;
    private String correo;
    private String user;
    private ArrayList<String> id; //url
    private ArrayList<Bitmap> img;
    private String url;//id

    public ItemMenuStructure (String tipo, String esatado, String precio, String  ciudad, String region,
                              String ubicacion, String descripcion, String cantidadCuartos,
                              String cantidadBaños, String garage, String superficie,
                              double lat,double lon,String correo, String user,
                              String urlimg, ArrayList<String> id ) {
        this.tipo = tipo;
        this.estado = esatado;
        this.precio = precio;
        this.ciudad = ciudad;
        this.region = region;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.cantidadCuartos = cantidadCuartos;
        this.cantidadBaños = cantidadBaños;
        this.garage = garage;
        this.superficie = superficie;
        this.lat = lat;
        this.lon = lon;
        this.correo = correo;
        this.user = user;
        this.url = urlimg;
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCantidadCuartos() {
        return cantidadCuartos;
    }

    public void setCantidadCuartos(String cantidadCuartos) {
        this.cantidadCuartos = cantidadCuartos;
    }

    public String getCantidadBaños() {
        return cantidadBaños;
    }

    public void setCantidadBaños(String cantidadBaños) {
        this.cantidadBaños = cantidadBaños;
    }

    public String getGarage() {
        return garage;
    }

    public void setGarage(String garage) {
        this.garage = garage;
    }

    public String getSuperficie() {
        return superficie;
    }

    public void setSuperficie(String superficie) {
        this.superficie = superficie;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<Bitmap> getImg() {
        return img;
    }

    public void setImg(ArrayList<Bitmap> img) {
        this.img = img;
    }

    public ArrayList<String> getId() {
        return id;
    }

    public void setId(ArrayList<String> id) {
        this.id = id;
    }
}
