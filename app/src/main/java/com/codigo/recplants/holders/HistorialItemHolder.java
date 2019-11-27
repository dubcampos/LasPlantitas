package com.codigo.recplants.holders;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codigo.recplants.Abaptadores.HistorialAdapter;
import com.codigo.recplants.R;

public class HistorialItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public ImageView fotoDiagnostico;
    public TextView enfermedadDiagnostico;
    public TextView horaDiagnostico;
    public CardView cardView;
    HistorialListener historialListener;

    public HistorialItemHolder(@NonNull View itemView, HistorialAdapter historialAdapter, HistorialListener historialListener) {
        super(itemView);
        fotoDiagnostico = itemView.findViewById(R.id.imageHistorial);
        enfermedadDiagnostico = itemView.findViewById(R.id.enfermedadHistorial);
        horaDiagnostico = itemView.findViewById(R.id.HoraHistorial);
        cardView = itemView.findViewById(R.id.card);
        this.historialListener = historialListener;
        cardView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        historialListener.historialClick(getAdapterPosition());
    }

    public interface HistorialListener{
        void historialClick(int position);
    }
}
