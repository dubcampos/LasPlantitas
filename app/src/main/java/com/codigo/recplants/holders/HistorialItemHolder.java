package com.codigo.recplants.holders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codigo.recplants.Abaptadores.HistorialAdapter;
import com.codigo.recplants.R;

public class HistorialItemHolder extends RecyclerView.ViewHolder{
    public ImageView fotoDiagnostico;
    public TextView enfermedadDiagnostico;
    public TextView horaDiagnostico;

    public HistorialItemHolder(@NonNull View itemView, HistorialAdapter historialAdapter) {
        super(itemView);
        fotoDiagnostico = itemView.findViewById(R.id.imageHistorial);
        enfermedadDiagnostico = itemView.findViewById(R.id.enfermedadHistorial);
        horaDiagnostico = itemView.findViewById(R.id.HoraHistorial);
    }
}
