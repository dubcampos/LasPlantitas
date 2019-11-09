package com.codigo.recplants.Fragmentos;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.codigo.recplants.Actividades.CamaraActivity;
import com.codigo.recplants.Actividades.RespuestaActivity;
import com.codigo.recplants.MainActivity;
import com.codigo.recplants.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RespuestaFragment extends Fragment {
    ImageView fragment;
    public RespuestaActivity ra;

    public RespuestaFragment() {
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_respuesta, container, false);
        fragment = view.findViewById(R.id.img_fragmnet);
        fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TomarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(TomarFoto, 200);
            }

        });
        Intent TomarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(TomarFoto, 200);


        return view;


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fragment.setImageBitmap((Bitmap)data.getExtras().get("data"));
        ra.ObtenerImagen((Bitmap)data.getExtras().get("data"));
        }
    }
