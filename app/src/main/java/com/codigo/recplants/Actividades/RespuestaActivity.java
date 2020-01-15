package com.codigo.recplants.Actividades;

import android.content.Intent;
import android.graphics.Bitmap;
import androidx.annotation.NonNull;

import com.codigo.recplants.clases.Historialgeneral;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
    Bundle bundle;


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

        bundle = getIntent().getExtras();
        if (bundle != null)
        {
            // Obtienes el texto
            String imagen = bundle.getString("imagen");
            String nombre = bundle.getString("nombre");
            String descripcion = bundle.getString("descripcion");
            // Creamos un nuevo Bundle
            Bundle args = new Bundle();

            // Colocamos el String
            args.putString("textFromActivityB", imagen);
            args.putString("nombreFromActivityB", nombre);
            args.putString("descripcionFromActivityB", descripcion);
            rf.setArguments(args);
        }
        rf.ra = this;
        adapter.addFragment(rf, "Diagnostico");
        adapter.addFragment(new ProcedimientoFragment(), "Porcedimiento");
        //adapter.addFragment(new PrevencionFragment(), "Prevencion");
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
                Intent intent = new Intent(RespuestaActivity.this,UsuarioLogueadoActivity.class);
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
            }
            return true;
        }
    };
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, 0);
            overridePendingTransition(0,0);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
