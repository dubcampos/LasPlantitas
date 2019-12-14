package com.codigo.recplants.Fragmentos;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;

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
import com.codigo.recplants.clases.HistorialGeneralRegistro;
import com.codigo.recplants.clases.Historialgeneral;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
    public boolean muestraCamara = false;

    public RespuestaFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_respuesta, container, false);
        fragment = view.findViewById(R.id.Img_fragment);


        if (muestraCamara) {
            Intent TomarFoto = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(TomarFoto, 200);
            Uri imagen = TomarFoto.getData();
            File file = new File(getRealPathFromURI(imagen));
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part part = MultipartBody.Part
                    .createFormData("imagen_usuarioCultivo", file.getName(), body);
            RequestBody usuario = RequestBody
                    .create(MediaType.parse("text/plain"), "1");

            RequestBody cultivo = RequestBody
                    .create(MediaType.parse("text/plain"), "1");

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://jalexish54.pythonanywhere.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Servicios service = retrofit.create(Servicios.class);
            Call<HistorialGeneralRegistro> llamada = service.historialRegistro(part, usuario, cultivo);
            llamada.enqueue(new Callback<HistorialGeneralRegistro>() {
                @Override
                public void onResponse(Call<HistorialGeneralRegistro> call, Response<HistorialGeneralRegistro> response) {
                    Log.e("Exito", "Exito");
                }

                @Override
                public void onFailure(Call<HistorialGeneralRegistro> call, Throwable t) {
                    Log.e("Error", t.toString());
                }
            });
        }
        return view;


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            fragment.setImageBitmap((Bitmap) data.getExtras().get("data"));
            ra.ObtenerImagen((Bitmap) data.getExtras().get("data"));
        } else {
            startActivity(new Intent(getContext(), MainActivity.class));
        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};

        CursorLoader loader = new CursorLoader(getContext(), contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;

    }
}
