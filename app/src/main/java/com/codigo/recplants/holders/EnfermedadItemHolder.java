package com.codigo.recplants.holders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.codigo.recplants.R;
import com.codigo.recplants.clases.Enfermedad;

public class EnfermedadItemHolder extends RecyclerView.ViewHolder{
    public TextView nombre_afeccion;
    public CardView cardView;
    Context context;

    public EnfermedadItemHolder(@NonNull View itemView, Context context) {
        super(itemView);
        nombre_afeccion = itemView.findViewById(R.id.tv_nomEnfer);
        cardView = itemView.findViewById(R.id.rv_enfermedad);
        this.context = context;
        //cardView.setOnClickListener(this);
    }

}
