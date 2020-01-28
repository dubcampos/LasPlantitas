package com.codigo.recplants.Fragmentos;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codigo.recplants.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrevencionFragment extends Fragment {


    public PrevencionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prevencion, container, false);
        // Inflate the layout for this fragment

        return view;
    }

}
