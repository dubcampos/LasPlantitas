package com.codigo.recplants.Actividades;

import android.content.Intent;
import androidx.annotation.NonNull;

import com.codigo.recplants.Abaptadores.EnfermedadAdapter;
import com.codigo.recplants.Interfaces.Servicios;
import com.codigo.recplants.clases.Enfermedad;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.codigo.recplants.MainActivity;
import com.codigo.recplants.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BusquedaActivity extends AppCompatActivity {
    BottomNavigationView BotonNav;
    RecyclerView recyclerView;
    SearchView searchView;
    List<Enfermedad> ListEnfermedad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        BotonNav = findViewById(R.id.button_nav1);
        BotonNav.setOnNavigationItemSelectedListener(navListener);
        recyclerView = findViewById(R.id.rv_enfermedad);
        searchView = findViewById(R.id.buscador);


        Menu menu = BotonNav.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);


        ListaEnfermedades();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<Enfermedad> listTemp = new ArrayList<>();
                for(Enfermedad enfermedad:ListEnfermedad){
                    if (enfermedad.getNombre_afeccion().contains(s)){
                        listTemp.add(enfermedad);
                    }
                }
                EnfermedadAdapter enfermedad_adaptador = new EnfermedadAdapter(BusquedaActivity.this,R.layout.view_item_enfermedad,listTemp);
                recyclerView.setAdapter(enfermedad_adaptador);
                return false;
            }
        });


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            int itemId = menuItem.getItemId();
            if (itemId == R.id.opcion_registro) {
                Intent intent = new Intent(BusquedaActivity.this,UsuarioLogueadoActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0,0);
            } else if (itemId == R.id.opcion_historial) {
                Intent intent = new Intent(BusquedaActivity.this, HistorialActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0,0);
            } else if (itemId == R.id.opcion_busqueda) {
                Intent intent = new Intent(BusquedaActivity.this, BusquedaActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0,0);
            } else if (itemId == R.id.opcion_calculadora) {
                Intent intent = new Intent(BusquedaActivity.this, CalculadoraActivity.class);
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

    void ListaEnfermedades(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jalexish54.pythonanywhere.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Servicios inter = retrofit.create(Servicios.class);
        Call<List<Enfermedad>> lista= inter.ObtenerEnfermedades();
        lista.enqueue(new Callback<List<Enfermedad>>() {
            @Override
            public void onResponse(Call<List<Enfermedad>> call, Response<List<Enfermedad>> response) {
                Log.e("lisya", String.valueOf(response.body().get(1).getNombre_afeccion()));
                switch (response.code()){
                    case 200:
                        ListEnfermedad = response.body();
                        Log.e("lisya", String.valueOf(response.body().get(1).getNombre_afeccion()));
                        EnfermedadAdapter Enfermedad_adaptador = new EnfermedadAdapter(BusquedaActivity.this,R.layout.view_item_enfermedad,ListEnfermedad);
                        recyclerView.setAdapter(Enfermedad_adaptador    );
                        recyclerView.setLayoutManager(new LinearLayoutManager(BusquedaActivity.this));
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Enfermedad>> call, Throwable t) {

            }
        });
    }
}
