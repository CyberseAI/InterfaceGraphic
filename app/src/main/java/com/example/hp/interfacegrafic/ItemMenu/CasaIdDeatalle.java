package com.example.hp.interfacegrafic.ItemMenu;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class CasaIdDeatalle {

    private String tipo;
    private String esatado;
    private String precio;
    private String region ;
    private String descripcion ;
    private String cantidadCuartos ;
    private String superficie;
    private String user;
    private Number lat;
    private Number lon;
    private String id;
    private ArrayList<String> url;
    private ArrayList<Bitmap> img;


    public CasaIdDeatalle (String tipo,
                           String estado,
                           String precio,
                           String region,
                           String descripcion,
                           String cantidadCuartos,
                           String superficie,
                           String user,
                           Number lat,
                           Number lon,
                           String id,
                           ArrayList<String> url){

        this.tipo = tipo;
        this.esatado = estado;
        this.precio = precio;
        this.region = region;
        this.descripcion = descripcion;
        this.cantidadCuartos = cantidadCuartos;
        this.superficie = superficie;
        this.user = user;
        this.lat=lat;
        this.lon=lon;
        this.id = id;
        this.url = url;
    }

    public Number getLat() {
        return lat;
    }

    public void setLat(Number lat) {
        this.lat = lat;
    }

    public Number getLon() {
        return lon;
    }

    public void setLon(Number lon) {
        this.lon = lon;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEsatado() {
        return esatado;
    }

    public void setEsatado(String esatado) {
        this.esatado = esatado;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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

    public String getSuperficie() {
        return superficie;
    }

    public void setSuperficie(String superficie) {
        this.superficie = superficie;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Bitmap> getImg() {
        return img;
    }

    public void setImg(ArrayList<Bitmap> img) {
        this.img = img;
    }

    public ArrayList<String> getUrl() {
        return url;
    }

    public void setUrl(ArrayList<String> url) {
        this.url = url;
    }
}
