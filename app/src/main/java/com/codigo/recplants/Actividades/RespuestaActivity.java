package com.codigo.recplants.Actividades;

import android.content.Intent;
import android.graphics.Bitmap;
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
import com.codigo.recplants.MainActivity;
import com.codigo.recplants.R;

public class RespuestaActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    BottomNavigationView BotonNav;


    public void ObtenerImagen(Bitmap imagen){
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("foto", imagen);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respuesta);
        tabLayout = findViewById(R.id.PestanaRespuesta);
        viewPager = findViewById(R.id.paginasRespuesta);
        Intent i = getIntent();
        RespuestaFragment rf = new RespuestaFragment();
        RespuestaTabAdapter adapter = new RespuestaTabAdapter(getSupportFragmentManager(), this);
        if (i.getBooleanExtra("abrirCamara",true)) {
            rf.muestraCamara = true;
        }else{
            rf.muestraCamara = false;
        }
        rf.ra = this;
        adapter.addFragment(rf, "Diagnostico");
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
            if (itemId == R.id.opcion_registro) {
                Intent intent = new Intent(RespuestaActivity.this,LogingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0,0);
            } else if (itemId == R.id.opcion_historial) {
                Intent intent = new Intent(RespuestaActivity.this, HistorialActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0,0);
            } else if (itemId == R.id.opcion_busqueda) {
                Intent intent = new Intent(RespuestaActivity.this, BusquedaActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0,0);
            } else if (itemId == R.id.opcion_calculadora) {
                Intent intent = new Intent(RespuestaActivity.this, CalculadoraActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0,0);
            }
            return true;
        }
    };
}
