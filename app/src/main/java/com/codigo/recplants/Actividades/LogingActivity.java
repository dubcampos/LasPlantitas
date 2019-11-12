package com.codigo.recplants.Actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codigo.recplants.MainActivity;
import com.codigo.recplants.R;

public class LogingActivity extends AppCompatActivity {

    Button btng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loging);
        btng=findViewById(R.id.register);
        btng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t=new Intent(LogingActivity.this,RegistrarseActiviity.class);
                startActivity(t);
            }
        });

    }
}
