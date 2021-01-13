package com.example.katalogbuku;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.katalogbuku.model.Buku;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnupdate;
    ImageButton btnedit;
    ListView lvdaftar;
    TextView txnodata, txusername;
    DaftarBukuAdapter adapter;
    List<Buku> DaftarBuku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inisialisasiView();
        loadDataTransaksi();
        setupListview();
    }

    private void inisialisasiView() {
        btnupdate = findViewById(R.id.btntambah);
        btnupdate.setOnClickListener(view -> bukaFormTambahPhone());
        btnedit = findViewById(R.id.btn_change_username);
        btnedit.setOnClickListener(view -> changeUserName());
        lvdaftar = findViewById(R.id.lv_list);
        txnodata = findViewById(R.id.tx_nodata);
        txusername = findViewById(R.id.tx_user_name);
        txusername.setText(SharedPerefernceUtility.getUserName(this));
        txusername = findViewById(R.id.tx_user_name);
    }

    private void setupListview() {
        adapter = new DaftarBukuAdapter(this, DaftarBuku);
        lvdaftar.setAdapter(adapter);

    }

    private void loadDataTransaksi() {
        DaftarBuku = SharedPerefernceUtility.getAllLis(this);
        if (DaftarBuku.size() > 0) {
            txnodata.setVisibility(View.GONE);
        } else {
            txnodata.setVisibility(View.VISIBLE);
        }

    }

    private void refreshListView() {
        adapter.clear();
        loadDataTransaksi();
        adapter.addAll(DaftarBuku);
    }

    private void bukaFormTambahPhone() {
        Intent intent = new Intent(this, FormKatalogBuku.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshListView();
    }

    private void changeUserName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ganti nama");
    }
}