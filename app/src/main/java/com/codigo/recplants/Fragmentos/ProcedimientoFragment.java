package com.codigo.recplants.Fragmentos;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codigo.recplants.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProcedimientoFragment extends Fragment {


    public ProcedimientoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_procedimiento, container, false);
    }

}
