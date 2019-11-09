package com.codigo.recplants.Actividades;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.codigo.recplants.Abaptadores.RespuestaTabAdapter;
import com.codigo.recplants.Fragmentos.PrevencionFragment;
import com.codigo.recplants.Fragmentos.ProcedimientoFragment;
import com.codigo.recplants.Fragmentos.RespuestaFragment;
import com.codigo.recplants.R;

public class RespuestaActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    BottomNavigationView BotonNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respuesta);
        tabLayout = findViewById(R.id.PestanaRespuesta);
        viewPager = findViewById(R.id.paginasRespuesta);

        RespuestaTabAdapter adapter = new RespuestaTabAdapter(getSupportFragmentManager(), this);
        adapter.addFragment(new RespuestaFragment(), "Diagnostico");
        adapter.addFragment(new ProcedimientoFragment(), "Porcedimiento");
        adapter.addFragment(new PrevencionFragment(), "Prevencion");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        BotonNav = findViewById(R.id.button_nav1);
        BotonNav.setOnNavigationItemSelectedListener(navListener);
        Menu menu = BotonNav.getMenu();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            int itemId = menuItem.getItemId();
            if (itemId == R.id.opcion_camara) {
                startActivity(new Intent(RespuestaActivity.this, CamaraActivity.class));
            } else if (itemId == R.id.opcion_historial) {
                startActivity(new Intent(RespuestaActivity.this, HistorialActivity.class));
            } else if (itemId == R.id.opcion_busqueda) {
                startActivity(new Intent(RespuestaActivity.this, BusquedaActivity.class));
            } else if (itemId == R.id.opcion_calculadora) {
                startActivity(new Intent(RespuestaActivity.this, CalculadoraActivity.class));
            }
            return true;
        }
    };
}
