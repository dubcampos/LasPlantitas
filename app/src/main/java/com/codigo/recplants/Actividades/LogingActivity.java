package com.codigo.recplants.Actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codigo.recplants.R;

public class LogingActivity extends AppCompatActivity {
    Button btn_iniciar;
    Button btn_registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loging);
        btn_iniciar=findViewById(R.id.btn_iniciar);
        btn_registro=findViewById(R.id.btn_registro);
        btn_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LogingActivity.this,RegistrarseActiviity.class);
                startActivity(intent);
            }
        });
    }
}
