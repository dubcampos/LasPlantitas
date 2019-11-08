package com.codigo.recplants.Actividades;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.codigo.recplants.R;

public class Camara extends AppCompatActivity {
    ImageView img_camara;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);
        img_camara=findViewById(R.id.img_camara);

        img_camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TomarFoto=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(TomarFoto,200);

            }
        });
        Intent TomarFoto=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(TomarFoto,200);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("CargarImagen","CargarImagen");
        img_camara.setImageBitmap((Bitmap)data.getExtras().get("data"));
    }
}
