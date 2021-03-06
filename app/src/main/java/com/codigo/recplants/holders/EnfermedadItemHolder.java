package com.codigo.recplants.holders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.codigo.recplants.R;

public class EnfermedadItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public ImageView imagenEnfermedad;
    public TextView nombre_afeccion;
    public CardView cardView;
    Context context;
    EnfermedadListener enfermedadListener;

    public EnfermedadItemHolder(@NonNull View itemView, Context context,EnfermedadListener enfermedadListener) {
        super(itemView);
        imagenEnfermedad = itemView.findViewById(R.id.iv_enfermedad);
        nombre_afeccion = itemView.findViewById(R.id.tv_nomEnfer);
        cardView = itemView.findViewById(R.id.cv_enfermedad);
        this.context = context;
        cardView.setOnClickListener(this);
        this.enfermedadListener = enfermedadListener;
    }

    @Override
    public void onClick(View view) {
        enfermedadListener.EnfermedadClick(getAdapterPosition());
    }

    public interface EnfermedadListener{
        void EnfermedadClick(int position);
    }
}
