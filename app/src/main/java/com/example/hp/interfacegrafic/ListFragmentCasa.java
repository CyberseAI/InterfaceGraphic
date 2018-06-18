package com.example.hp.interfacegrafic;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.hp.interfacegrafic.ItemMenu.ItemMenuStructure;
import com.example.hp.interfacegrafic.ItemMenu.MenuBaseAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ListFragmentCasa extends Fragment {
    private ArrayList<ItemMenuStructure> LISTDATA;
    private View ROOT;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LISTDATA = new ArrayList<ItemMenuStructure>();
        ROOT = inflater.inflate(R.layout.activity_list_fragment_casa, container, false);
        loadData();
        return ROOT;
    }

    private void loadData() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://10.10.1.150:7777/api/v1.0/inmuebles_ecp", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray listData = response.getJSONArray("docs");
                    for (int i = 0; i < listData.length(); i++) {
                        JSONObject obj = listData.getJSONObject(i);
                        String tipo = obj.getString("tipo");
                        String estado = obj.getString("estado");
                        String precio = obj.getString("precio");
                        String ciudad = obj.getString("ciudad");
                        String cantidadCuartos = obj.getString("cantidadCuartos");
                        String cantidadBaños = obj.getString("cantidadBaños");
                        String correo = obj.getString("correo");
                        String id = obj.getString("_id");
                        String url = (String)obj.getJSONArray("gallery").get(0);
                        LISTDATA.add(new ItemMenuStructure(tipo,estado, precio, ciudad , "","","",cantidadCuartos,
                                cantidadBaños,"","",correo,"",id,url));
                    }
                    LoadComponents();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void LoadComponents () {
        ListView list = (ListView) ROOT.findViewById(R.id.super_lista);
        MenuBaseAdapter adapter = new MenuBaseAdapter(this.getActivity(),LISTDATA);
        list.setAdapter(adapter);
    }
}
