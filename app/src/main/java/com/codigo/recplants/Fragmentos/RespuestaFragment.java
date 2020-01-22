package com.codigo.recplants.Fragmentos;


import android.Manifest;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codigo.recplants.Actividades.RespuestaActivity;
import com.codigo.recplants.Interfaces.Servicios;
import com.codigo.recplants.MainActivity;
import com.codigo.recplants.R;
import com.codigo.recplants.clases.respuesta;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import java.io.InputStream;
import java.text.SimpleDateFormat;

import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_OK;

public class RespuestaFragment extends Fragment {
    ImageView fragment;
    public RespuestaActivity ra;
    public boolean muestraCamara = false;

    public static final int REQUEST_CODE_TAKE_PHOTO = 0 /*1*/;
    private String mCurrentPhotoPath;
    private Uri photoURI;
    Bitmap bitmap;

    TextView titleTextView;
    TextView causeTextView;
    TextView remediesTextView;

    public RespuestaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_respuesta, container, false);
        fragment = view.findViewById(R.id.Img_fragment);
        titleTextView = view.findViewById(R.id.diseaseTitleTextView);
        causeTextView = view.findViewById(R.id.causeTextView);
        remediesTextView = view.findViewById(R.id.remediesTextView);

        if (getArguments() != null) {
            String texto = getArguments().getString("textFromActivityB");
            String nombre = getArguments().getString("nombreFromActivityB");
            String descripcion = getArguments().getString("descripcionFromActivityB");
            titleTextView.setText(nombre);
            causeTextView.setText(descripcion);
            Glide.with(getContext()).load(texto).into(fragment);
        }


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
        if (resultCode == RESULT_OK) {

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), photoURI);
                fragment.setImageBitmap(bitmap);
                ra.ObtenerImagen(bitmap);
                connectServer(bitmap);
                //fragment.setImageBitmap((Bitmap) data.getExtras().get("data"));
                //ra.ObtenerImagen((Bitmap) data.getExtras().get("data"));
                //final Bitmap photo = (Bitmap) data.getExtras().get("data");

                Uri imagen = getImageUri(getActivity(), bitmap);

                File file = new File(getRealPathFromURI(imagen));

                RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);

                /*MultipartBody.Part part = MultipartBody.Part.createFormData("imagen_usuarioCultivo", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
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
                });*/
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

    void connectServer(Bitmap v) {

        String postUrl = "http://192.168.0.111:8000/";
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        try {
            String selectedImagePath = getPath(getContext(), photoURI);
            Bitmap bitmap = BitmapFactory.decodeFile(selectedImagePath, options);
            v.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            RequestBody postBodyImage = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("image", "androidFlask.jpg", RequestBody.create(MediaType.parse("image/*jpg"), byteArray))
                    .build();
            Toast.makeText(getContext(), "Please wait ...", Toast.LENGTH_SHORT).show();
            postRequest(postUrl, postBodyImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    void postRequest(String postUrl, RequestBody postBody) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(postUrl)
                .post(postBody)
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                call.cancel();
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "Failed to Connect to Server", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(okhttp3.Call call, final okhttp3.Response response) throws IOException {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String disease = response.body().string();
                            Toast.makeText(getContext(), disease, Toast.LENGTH_SHORT).show();
                            Log.e("respuesta", disease);
                            titleTextView.setText(disease);

                            Uri imagen = getImageUri(getActivity(), bitmap);

                            File file = new File(getRealPathFromURI(imagen));


                            RequestBody usuario = RequestBody.create(MediaType.parse("text/plain"), "1");
                            RequestBody enfermedad = RequestBody.create(MediaType.parse("text/plain"), disease);
                            RequestBody cultivo = RequestBody.create(MediaType.parse("text/plain"), "1");
                            RequestBody requestFile = RequestBody.create(MediaType.parse(getContext().getContentResolver().getType(imagen)), file);


                            //The gson builder
                            Gson gson = new GsonBuilder()
                                    .setLenient()
                                    .create();

                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl("http://jalexish54.pythonanywhere.com/")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            Servicios service = retrofit.create(Servicios.class);

                            Call<respuesta> llamada = service.uploadImage(usuario, enfermedad, cultivo, requestFile);
                            llamada.enqueue(new Callback<respuesta>() {
                                @Override
                                public void onResponse(Call<respuesta> call, Response<respuesta> response) {
                                    Log.e("post submitted to API.", response.body().toString());
                                }

                                @Override
                                public void onFailure(Call<respuesta> call, Throwable t) {

                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("causesremedies.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
}
