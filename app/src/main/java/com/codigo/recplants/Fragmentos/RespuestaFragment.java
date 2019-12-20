package com.codigo.recplants.Fragmentos;


import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RespuestaFragment extends Fragment {
    ImageView fragment;
    public RespuestaActivity ra;
    public boolean muestraCamara = false;

    public static final int REQUEST_CODE_TAKE_PHOTO = 0 /*1*/;
    private String mCurrentPhotoPath;
    private Uri photoURI;

    public RespuestaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_respuesta, container, false);
        fragment = view.findViewById(R.id.Img_fragment);
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 225);
            }
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 226);
            }
        } else {
            dispatchTakePictureIntent();
        }


        return view;
    }

    private void dispatchTakePictureIntent() {
        if (muestraCamara) {
            Intent TomarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //if (TomarFoto.resolveActivity(getActivity().getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
            }
            if (photoFile != null) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "MyPicture");
                values.put(MediaStore.Images.Media.DESCRIPTION, "Photo taken on " + System.currentTimeMillis());

                photoURI = getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

                TomarFoto.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(TomarFoto, REQUEST_CODE_TAKE_PHOTO);
            }
            //}
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Bitmap bitmap;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), photoURI);
                fragment.setImageBitmap(bitmap);
                ra.ObtenerImagen(bitmap);

                //fragment.setImageBitmap((Bitmap) data.getExtras().get("data"));
                //ra.ObtenerImagen((Bitmap) data.getExtras().get("data"));
                //final Bitmap photo = (Bitmap) data.getExtras().get("data");

                Uri imagen = getImageUri(getActivity(), bitmap);

                File file = new File(getRealPathFromURI(imagen));

                RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);

                MultipartBody.Part part = MultipartBody.Part.createFormData("imagen_usuarioCultivo", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
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
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}
