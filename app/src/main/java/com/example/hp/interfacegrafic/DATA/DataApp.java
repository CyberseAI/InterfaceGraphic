package com.example.hp.interfacegrafic.DATA;


import com.example.hp.interfacegrafic.ItemMenu.ItemMenuStructure;

import java.util.ArrayList;

public class DataApp {
    static public ArrayList<ItemMenuStructure> LISTDATA;
    static public String HOST = "http://192.168.43.150:7777";
    static public String inmueble_id = HOST + "/api/v1.0/inmuebles_id";
    static public String  user_id = HOST + "/api/v1.0/user";
}
