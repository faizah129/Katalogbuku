package com.example.katalogbuku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.katalogbuku.model.Buku;
import com.google.android.material.textfield.TextInputLayout;

public class FormKatalogBuku extends AppCompatActivity {
    Button btnSimpan;
    TextInputLayout tilJudul, tilPengarang, tilTahun;
    Spinner spnJenis;
    final String[] tipeLis = {Buku.CERITA, Buku.NOVEL, Buku.PENDIDIKAN,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_katalog_buku);
        inisialisasiView();
    }

    private void inisialisasiView() {
        btnSimpan = findViewById(R.id.btn_simpan);
        btnSimpan.setOnClickListener(view -> simpan());
        tilJudul = findViewById(R.id.til_jb);
        tilPengarang = findViewById(R.id.til_pg);
        tilTahun = findViewById(R.id.til_th);
        spnJenis = findViewById(R.id.spn_jenis);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                tipeLis
        );
        spnJenis.setAdapter(adapter);
        spnJenis.setSelection(0);
    }

    private void simpan() {
        if (isDataValid()) {
            Buku tr = new Buku();
            tr.setJudul(tilJudul.getEditText().getText().toString());
            tr.setPengarang(tilPengarang.getEditText().getText().toString());
            tr.setJenis(spnJenis.getSelectedItem().toString());
            tr.setTahun(tilTahun.getEditText().getText().toString());
            SharedPerefernceUtility.addLis(this, tr);
            Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();

            // Kembali ke layar sebelumnya setelah 500 ms (0.5 detik)
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 500);


        }
    }

    private boolean isDataValid() {
        if (tilJudul.getEditText().getText().toString().isEmpty()
                || tilPengarang.getEditText().getText().toString().isEmpty()
                        || spnJenis.getSelectedItem().toString().isEmpty()
                             || tilTahun.getEditText().getText().toString().isEmpty()

        ) {
            Toast.makeText(this, "Data tidak bileh ada yang kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}