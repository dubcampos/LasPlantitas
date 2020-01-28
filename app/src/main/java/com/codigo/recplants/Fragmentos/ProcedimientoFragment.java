package com.codigo.recplants.Fragmentos;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.codigo.recplants.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProcedimientoFragment extends Fragment {


    TextView remediesTextView;
    public ProcedimientoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_procedimiento, container, false);
        remediesTextView = view.findViewById(R.id.remediesTextView);
        // Inflate the layout for this fragment
        if (getArguments() != null) {
            String texto = getArguments().getString("prevencionFromActivityB");
            Toast.makeText(getContext(),texto,Toast.LENGTH_LONG).show();
            remediesTextView.setText(texto);
        }
        return view;
    }

}
