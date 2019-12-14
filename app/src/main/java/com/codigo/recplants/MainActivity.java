package com.codigo.recplants;

import android.Manifest;
import android.content.Intent;
import androidx.annotation.NonNull;

import com.codigo.recplants.Actividades.UsuarioLogueadoActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.codigo.recplants.Actividades.BusquedaActivity;
import com.codigo.recplants.Actividades.CalculadoraActivity;

import com.codigo.recplants.Actividades.HistorialActivity;
import com.codigo.recplants.Actividades.LogingActivity;
import com.codigo.recplants.Actividades.RespuestaActivity;

import java.util.zip.Inflater;


public class MainActivity extends AppCompatActivity {
    FloatingActionButton btn_floating;
    BottomNavigationView BotonNav;
    Toolbar toolbar1;
    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.imageView3);
        BotonNav = findViewById(R.id.button_nav1);
        BotonNav.setOnNavigationItemSelectedListener(navListener);
        btn_floating = findViewById(R.id.flotante);
        toolbar1 = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 2);


        }else {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 2);
        }
        btn_floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, RespuestaActivity.class);
                startActivity(intent);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.compartir,menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.compartimos:
                try {


                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
                    String aux = "Descarga la app\n";
                    aux = aux + "https://play.google.com/store/apps/details?id=com.ea.gp.fifamobile";
                    i.putExtra(Intent.EXTRA_TEXT, aux);
                    startActivity(i);
                }catch (Exception e){

                }
                break;



            case R.id.prueba:
                Intent intent=new Intent(MainActivity.this,LogingActivity.class);
                startActivity(intent);
            case R.id.observar:
                Toast.makeText(this,":V",Toast.LENGTH_SHORT).show();
                break;



        }

        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            int itemId = menuItem.getItemId();
            if (itemId == R.id.opcion_registro) {
                Intent intent = new Intent(MainActivity.this, UsuarioLogueadoActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0,0);
            } else if (itemId == R.id.opcion_historial) {
                Intent intent = new Intent(MainActivity.this, HistorialActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0,0);
            } else if (itemId == R.id.opcion_busqueda) {
                Intent intent = new Intent(MainActivity.this, BusquedaActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0,0);
            } else if (itemId == R.id.opcion_calculadora) {
                Intent intent = new Intent(MainActivity.this, CalculadoraActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0,0);
            }
            return true;
        }
    };


}
