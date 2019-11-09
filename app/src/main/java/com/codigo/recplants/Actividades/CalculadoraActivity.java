package com.codigo.recplants.Actividades;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.codigo.recplants.R;

public class CalculadoraActivity extends AppCompatActivity {
    BottomNavigationView BotonNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);
        BotonNav = findViewById(R.id.button_nav1);
        BotonNav.setOnNavigationItemSelectedListener(navListener);
        Menu menu = BotonNav.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            int itemId = menuItem.getItemId();
            if (itemId == R.id.opcion_camara) {
                startActivity(new Intent(CalculadoraActivity.this, CamaraActivity.class));
            } else if (itemId == R.id.opcion_historial) {
                startActivity(new Intent(CalculadoraActivity.this, HistorialActivity.class));
            } else if (itemId == R.id.opcion_busqueda) {
                startActivity(new Intent(CalculadoraActivity.this, BusquedaActivity.class));
            } else if (itemId == R.id.opcion_calculadora) {
                startActivity(new Intent(CalculadoraActivity.this, CalculadoraActivity.class));
            }
            return true;
        }
    };
}
