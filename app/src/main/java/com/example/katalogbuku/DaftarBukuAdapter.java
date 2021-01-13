package com.example.katalogbuku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.katalogbuku.model.Buku;

import java.util.List;

public class DaftarBukuAdapter extends ArrayAdapter<Buku> {
    Context context;

    public DaftarBukuAdapter(@NonNull Context context, @NonNull List<Buku> objects) {
        super(context, R.layout.row_buku, objects);
        this.context = context;
    }

    class ViewHolder {
        TextView txJudul;
        TextView txPengarang;
        TextView txJenis;
        TextView txTahun;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Buku tr = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_buku, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txJudul = convertView.findViewById(R.id.row_jd);
            viewHolder.txPengarang = convertView.findViewById(R.id.row_pg);
            viewHolder.txTahun = convertView.findViewById(R.id.row_th);
            viewHolder.txJenis = convertView.findViewById(R.id.row_js);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txJudul.setText(tr.getJudul());
        viewHolder.txPengarang.setText(tr.getPengarang());
        viewHolder.txTahun.setText(tr.getTahun());
        if (tr.getJenis().equals(Buku.CERITA)) {
            viewHolder.txJenis.setText("CERITA");
        } else if (tr.getJenis().equals(Buku.NOVEL)) {
            viewHolder.txJenis.setText("NOVEL");
        } else if (tr.getJenis().equals(Buku.PENDIDIKAN)) {
            viewHolder.txJenis.setText("PENDIDIKAN");
        } else {
            viewHolder.txJenis.setText("UMUM");
        }
        return convertView;
    }
}
