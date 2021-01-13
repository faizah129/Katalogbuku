package com.example.katalogbuku.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

public class Buku {

    public static final String NOVEL = "NOVEL";
    public static final String PENDIDIKAN = "PENDIDIKAN";
    public static final String CERITA = "CERITA";


    private String id;
    private String judul;
    private String pengarang;
    private String jenis;
    private String tahun;

    public Buku() { this.id = UUID.randomUUID().toString();}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public static Buku fromJSONObject(JSONObject obj) {
        Buku tr = new Buku();
        try {
            tr.setId(obj.getString("id"));
            tr.setJudul(obj.getString("judul"));
            tr.setPengarang(obj.getString("pengarang"));
            tr.setJenis(obj.getString("jenis"));
            tr.setTahun(obj.getString("tahun"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tr;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("id", this.id);
            jsonObj.put("judul", this.judul);
            jsonObj.put("pengarang", this.pengarang);
            jsonObj.put("jenis", this.jenis);
            jsonObj.put("tahun", this.tahun);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj;
    }
}
