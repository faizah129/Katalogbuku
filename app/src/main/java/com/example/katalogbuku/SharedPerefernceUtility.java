package com.example.katalogbuku;

import android.content.Context;
import android.util.Log;

import com.example.katalogbuku.model.Buku;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SharedPerefernceUtility {

    private static final String PREF_FILE = "com.example.katalogbuku.DATA";
    private static final String TRANS_KEY = "TRANS";
    private static final String USER_NAME_KEY = "USERNAME";

    private static android.content.SharedPreferences getSharedPref(Context ctx) {
        android.content.SharedPreferences sharedPref = ctx.getSharedPreferences(
                PREF_FILE, Context.MODE_PRIVATE);
        return sharedPref;
    }
    /*
       Ambil data username dari Shared Preference
    */
    public static String getUserName(Context ctx) {
        return getSharedPref(ctx).getString(USER_NAME_KEY, "NO NAME");
    }

    /*
        Simpan data username ke Shared Preference
     */
    public static void saveUserName(Context ctx, String userName) {
        Log.d("SH PREF", "Change user name to" + userName);
        getSharedPref(ctx).edit().putString(USER_NAME_KEY, userName).apply();
    }

    /*
        Ambil data transaksi dari Shared Preference
        Perhatikan bahwa data disimpan dalam format JSON String
     */
    public static List<Buku> getAllLis(Context ctx) {
        String jsonString = getSharedPref(ctx).getString(TRANS_KEY, null);
        List<Buku> trs = new ArrayList<Buku>();
        if (jsonString != null) {
            Log.d("SH PREF", "json string is:" + jsonString);
            try {
                JSONArray jsonArray = new JSONArray(jsonString);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    trs.add(Buku.fromJSONObject(obj));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(trs, (transaksi, t1) -> {
            return transaksi.getId().compareTo(t1.getId());
        }); // urutkan transaksi berdasarkan id
        return trs;
    }

    /*
        Simpan data transaksi ke Shared Preference
        Perhatikan bahwa data disimpan dalam format JSON String
     */
    private static void saveAllTransaksi(Context ctx, List<Buku> trs) {
        List<JSONObject> jsonTrs = new ArrayList<JSONObject>();
        JSONArray jsonArr = new JSONArray();
        for ( Buku tr : trs) {
            jsonArr.put(tr.toJSONObject());
        }
        getSharedPref(ctx).edit().putString(TRANS_KEY, jsonArr.toString()).apply();
    }

    /*
        Tambah data transaksi baru ke Shared Preference
     */
    public static void addLis(Context ctx, Buku tr) {
        List<Buku> trs = getAllLis(ctx);
        trs.add(tr);
        saveAllTransaksi(ctx, trs);
    }
}
