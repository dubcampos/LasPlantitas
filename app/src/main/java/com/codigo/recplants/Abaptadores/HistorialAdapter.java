package com.codigo.recplants.Abaptadores;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.codigo.recplants.R;
import com.codigo.recplants.clases.Historialgeneral;
import com.codigo.recplants.clases.prueba;
import com.codigo.recplants.holders.HistorialItemHolder;

import java.util.List;

public class HistorialAdapter extends RecyclerView.Adapter<HistorialItemHolder>  {
    private HistorialItemHolder.HistorialListener historialListener;

    List<Historialgeneral> datosHistorial;
    LayoutInflater inflater;
    Context context;

    public HistorialAdapter(List<Historialgeneral> datosHistorial, Context context, HistorialItemHolder.HistorialListener historialListener ) {
        inflater = LayoutInflater.from(context);
        this.datosHistorial = datosHistorial;
        this.context = context;
        this.historialListener = historialListener;
    }

    @NonNull
    @Override
    public HistorialItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vista = inflater.inflate(R.layout.view_item_historial,viewGroup,false);
        return new HistorialItemHolder(vista, this, historialListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HistorialItemHolder historialItemHolder, int i) {
        Glide.with(context).load(datosHistorial.get(i).getImagen_usuarioCultivo()).into(historialItemHolder.fotoDiagnostico);
        historialItemHolder.enfermedadDiagnostico.setText(datosHistorial.get(i).getCultivo().getNombre_cultivo());
        historialItemHolder.horaDiagnostico.setText(datosHistorial.get(i).getUsuario().getNombre_usuario());
    }

    @Override
    public int getItemCount() {
        return datosHistorial.size();
    }
}
