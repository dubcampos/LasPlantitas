package com.codigo.recplants.Actividades;

import android.content.Intent;
import androidx.annotation.NonNull;

import com.codigo.recplants.Interfaces.Servicios;
import com.codigo.recplants.clases.Historialgeneral;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.codigo.recplants.Abaptadores.HistorialAdapter;
import com.codigo.recplants.MainActivity;
import com.codigo.recplants.R;
import com.codigo.recplants.holders.HistorialItemHolder;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistorialActivity extends AppCompatActivity implements HistorialItemHolder.HistorialListener {
    BottomNavigationView BotonNav;
    RecyclerView historialLista;
    List<Historialgeneral> datos;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        BotonNav = findViewById(R.id.button_nav1);
        historialLista = findViewById(R.id.lista_historial);

        BotonNav.setOnNavigationItemSelectedListener(navListener);
        toolbar = findViewById(R.id.tb);
        setSupportActionBar(toolbar);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jalexish54.pythonanywhere.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Servicios service = retrofit.create(Servicios.class);
        final Call<List<Historialgeneral>>  historial = service.obtenerHistorialID(1);
        historial.enqueue(new Callback<List<Historialgeneral>>() {
            @Override
            public void onResponse(Call<List<Historialgeneral>> call, Response<List<Historialgeneral>> response) {
                Log.e("xxxx", response.body().get(0).toString());
                switch (response.code()){
                    case 200:
                        datos = response.body();
                        historialLista.setAdapter(new HistorialAdapter(datos,HistorialActivity.this, HistorialActivity.this));
                        historialLista.setLayoutManager(new GridLayoutManager(HistorialActivity.this, 1));
                        //historialLista.setLayoutManager(new LinearLayoutManager(HistorialActivity.this,LinearLayoutManager.HORIZONTAL,false));
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Historialgeneral>> call, Throwable t) {

            }
        });

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
            }
            return true;
        }
    };

    @Override
    public void historialClick(int position) {
        //Toast.makeText(this,"hola", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "cod + " + position, Toast.LENGTH_LONG).show();
        //Historialgeneral historialgeneral = datos.get(position);
        Intent intent = new Intent(HistorialActivity.this,RespuestaActivity.class);
        intent.putExtra("imagen", datos.get(position).imagen_usuarioCultivo);
        intent.putExtra("nombre", datos.get(position).getDiagnostico().getAfeccion().getNombre_afeccion());
        intent.putExtra("descripcion", datos.get(position).getDiagnostico().getAfeccion().getDescripcion_efeccion());
        intent.putExtra("causa", datos.get(position).getDiagnostico().getAfeccion().getCausa_afeccion());
        intent.putExtra("prevencion", datos.get(position).getDiagnostico().getAfeccion().getPrevencion_afeccion());
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
