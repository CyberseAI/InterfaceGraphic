package com.example.hp.interfacegrafic;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hp.interfacegrafic.DATA.DataApp;
import com.example.hp.interfacegrafic.ItemMenu.ItemMenuStructure;
import com.example.hp.interfacegrafic.ItemMenu.MenuBasaeAdapterUserCasaId;
import com.example.hp.interfacegrafic.ItemMenu.MenuBaseAdapter;
import com.example.hp.interfacegrafic.Utils.OnLoadDataComplete;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class UserListaId extends Fragment implements AdapterView.OnItemClickListener {
    private ArrayList<ItemMenuStructure> ListData;
    private View ROOT;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DataApp.LISTDATA = new ArrayList<ItemMenuStructure>();
        ListData= new ArrayList<ItemMenuStructure>();
        ROOT = inflater.inflate(R.layout.fragment_user_lista_id, container, false);
        loadData();
        return ROOT;

    }

    private void loadData() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(DataApp.HOST + "/api/v1.0/homeid/5b37d7e17f8b572c8516d3b4", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray listData = response.getJSONArray("homes");
                    for (int i = 0; i < listData.length(); i++) {
                        JSONObject obj = listData.getJSONObject(i);
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
                        //String url = DataApp.HOST + (String) obj.getJSONArray("gallery").get(0);
                        JSONArray listGalery= obj.getJSONArray("gallery");
                        ArrayList<String> urlLists = new ArrayList<String>();
                        for (int j = 0; j < listGalery.length(); j++){
                            urlLists.add(DataApp.HOST + listGalery.getString(j));
                        }

                        DataApp.LISTDATA.add(new ItemMenuStructure(tipo, estado, precio, ciudad,
                                "", "", "", cantidadCuartos, cantidadBaños,
                                "", "", lat, lon, correo, "", id, urlLists));
                    }
                    LoadComponents();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        });


    }

    private void LoadComponents() {

        ListView list = (ListView) ROOT.findViewById(R.id.listaCasasIdcontenedor);
        MenuBasaeAdapterUserCasaId adapter = new MenuBasaeAdapterUserCasaId(this.getActivity(), DataApp.LISTDATA);
        list.setAdapter(adapter);

        list.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String datalist = DataApp.LISTDATA.get(position).getUrl();
        //ArrayList<String> size = DataApp.LISTDATA.get(position);
        //UserData.IDCasa = datalist;
        Intent intent = new Intent(this.getActivity(), PatchCasa.class);
        intent.putExtra("size", position);
        intent.putExtra("id", datalist);
        this.getActivity().startActivity(intent);

    }
}
