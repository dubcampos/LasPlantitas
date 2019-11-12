package com.codigo.recplants;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.codigo.recplants.Actividades.BusquedaActivity;
import com.codigo.recplants.Actividades.CalculadoraActivity;

import com.codigo.recplants.Actividades.CamaraActivity;
import com.codigo.recplants.Actividades.HistorialActivity;
import com.codigo.recplants.Actividades.LogingActivity;
import com.codigo.recplants.Actividades.RespuestaActivity;
import com.codigo.recplants.Fragmentos.RespuestaFragment;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView BotonNav;
    Button btn;

    ImageView img;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.button4);
        img=findViewById(R.id.imageView3);
        BotonNav = findViewById(R.id.button_nav1);
        BotonNav.setOnNavigationItemSelectedListener(navListener);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten=new Intent(MainActivity.this,LogingActivity.class);
                startActivity(inten);

            }
        });



    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            int itemId = menuItem.getItemId();
            if (itemId == R.id.opcion_camara) {
                Intent intent = new Intent(MainActivity.this,CamaraActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0,0);
            } else if (itemId == R.id.opcion_historial) {
                Intent intent = new Intent(MainActivity.this, HistorialActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0,0);
            } else if (itemId == R.id.opcion_busqueda) {
                Intent intent = new Intent(MainActivity.this, LogingActivity.class);
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
