package com.codigo.recplants;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.codigo.recplants.Actividades.BusquedaActivity;
import com.codigo.recplants.Actividades.CalculadoraActivity;
import com.codigo.recplants.Actividades.Camara;
import com.codigo.recplants.Actividades.HistorialActivity;
import com.codigo.recplants.Fragmentos.BusquedaFragment;
import com.codigo.recplants.Fragmentos.CalculadoraFragment;
import com.codigo.recplants.Fragmentos.HistorialFragment;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView BotonNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BotonNav = findViewById(R.id.button_nav1);
        BotonNav.setOnNavigationItemSelectedListener(navListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            int itemId = menuItem.getItemId();
            if (itemId == R.id.opcion_camara) {
                Intent intent=new Intent(MainActivity.this,Camara.class);
                startActivity(intent);

            } else if (itemId == R.id.opcion_historial) {
                startActivity(new Intent(MainActivity.this, HistorialActivity.class));
            } else if (itemId == R.id.opcion_busqueda) {
                startActivity(new Intent(MainActivity.this, BusquedaActivity.class));
            } else if (itemId == R.id.opcion_calculadora) {
                startActivity(new Intent(MainActivity.this, CalculadoraActivity.class));
            }
            return true;
        }
    };
}
