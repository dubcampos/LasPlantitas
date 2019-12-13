package com.codigo.recplants.Fragmentos;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.codigo.recplants.Actividades.RespuestaActivity;
import com.codigo.recplants.Interfaces.Servicios;
import com.codigo.recplants.MainActivity;
import com.codigo.recplants.R;
import com.codigo.recplants.clases.Historialgeneral;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class RespuestaFragment extends Fragment {
    ImageView fragment;
    public RespuestaActivity ra;
    public boolean muestraCamara=false;

    public RespuestaFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_respuesta, container, false);
        fragment = view.findViewById(R.id.Img_fragment);
        if(muestraCamara) {
            Intent TomarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(TomarFoto, 200);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://jalexish54.pythonanywhere.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Servicios service = retrofit.create(Servicios.class);
            Call<Historialgeneral> historial = service.historialRegistro(1,1);
            historial.enqueue(new Callback<Historialgeneral>() {
                                  @Override
                                  public void onResponse(Call<Historialgeneral> call, Response<Historialgeneral> response) {
                                      Log.e("nooo", String.valueOf(response.code()));
                                      Toast.makeText(getContext(),"voy por buen camino",Toast.LENGTH_LONG).show();
                                  }

                                  @Override
                                  public void onFailure(Call<Historialgeneral> call, Throwable t) {

                                  }
                              }
            );
        }
        return view;


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (data != null){
                fragment.setImageBitmap((Bitmap)data.getExtras().get("data"));
                ra.ObtenerImagen((Bitmap)data.getExtras().get("data"));
            }else{
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        }
    }
