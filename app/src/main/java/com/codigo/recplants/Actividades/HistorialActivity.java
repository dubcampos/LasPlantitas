package com.codigo.recplants.Actividades;

import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.codigo.recplants.Abaptadores.HistorialAdapter;
import com.codigo.recplants.MainActivity;
import com.codigo.recplants.R;
import com.codigo.recplants.clases.prueba;
import com.codigo.recplants.holders.HistorialItemHolder;

import java.util.LinkedList;
import java.util.List;

public class HistorialActivity extends AppCompatActivity implements HistorialItemHolder.HistorialListener {
    BottomNavigationView BotonNav;
    RecyclerView historialLista;
    List<prueba> datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        BotonNav = findViewById(R.id.button_nav1);
        historialLista = findViewById(R.id.lista_historial);

        BotonNav.setOnNavigationItemSelectedListener(navListener);

        datos = new LinkedList<>();
        datos.add(new prueba("sup1","su"));
        datos.add(new prueba("sup2","su"));
        datos.add(new prueba("sup2","su"));
        datos.add(new prueba("sup2","su"));
        historialLista.setAdapter(new HistorialAdapter(datos,HistorialActivity.this, this));
        historialLista.setLayoutManager(new GridLayoutManager(this, 1));

        Menu menu = BotonNav.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);




    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            int itemId = menuItem.getItemId();
            if (itemId == R.id.opcion_registro) {
                Intent intent = new Intent(HistorialActivity.this, UsuarioLogueadoActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0,0);
            } else if (itemId == R.id.opcion_historial) {
                Intent intent = new Intent(HistorialActivity.this, HistorialActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0,0);
            } else if (itemId == R.id.opcion_busqueda) {
                Intent intent = new Intent(HistorialActivity.this, BusquedaActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0,0);
            } else if (itemId == R.id.opcion_calculadora) {
                Intent intent = new Intent(HistorialActivity.this, CalculadoraActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0,0);
            }
            return true;
        }
    };

    @Override
    public void historialClick(int position) {
        Toast.makeText(this,"hola", Toast.LENGTH_LONG).show();
        Log.e("error","i");
        datos.get(position);
        Intent intent = new Intent(HistorialActivity.this,RespuestaActivity.class);
        intent.putExtra("abrirCamara", false);
        startActivity(intent);
    }

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
