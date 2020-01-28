package com.codigo.recplants.Abaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codigo.recplants.clases.afeccion;
import com.codigo.recplants.holders.EnfermedadItemHolder;

import java.util.List;

public class EnfermedadAdapter extends RecyclerView.Adapter<EnfermedadItemHolder> {
   private EnfermedadItemHolder.EnfermedadListener listener;
    Context context;
    int layout;
    List<afeccion> datos;
    LayoutInflater layoutInflater;

    public EnfermedadAdapter(Context context, int layout, List<afeccion> datos,EnfermedadItemHolder.EnfermedadListener listener) {
        this.context = context;
        this.layout = layout;
        this.datos = datos;
        this.listener = listener;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public EnfermedadItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(layout,parent,false);
        return new EnfermedadItemHolder(v, context,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull EnfermedadItemHolder holder, int position) {
        Glide.with(context).load(datos.get(position).getImagen_afeccion()).into(holder.imagenEnfermedad);
        holder.nombre_afeccion.setText(datos.get(position).getNombre_afeccion());

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }
}
