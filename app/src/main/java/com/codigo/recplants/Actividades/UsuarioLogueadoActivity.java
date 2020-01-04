package com.codigo.recplants.Actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codigo.recplants.MainActivity;
import com.codigo.recplants.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class UsuarioLogueadoActivity extends AppCompatActivity  {
    ImageView img_usuario;
    TextView txt_email;
    TextView txt_nombre;
    Button btn_salir;



    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    public static final int RC_SIGN_IN = 1;
    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.EmailBuilder().build(),
            new AuthUI.IdpConfig.PhoneBuilder().build(),
            new AuthUI.IdpConfig.GoogleBuilder().build()
    );


    //svkjnvkjndfkvndknbkdj
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_logueado);

        txt_nombre=findViewById(R.id.txt_nombre);
        txt_email=findViewById(R.id.txt_email);
        img_usuario=findViewById(R.id.imagen_usuario);
        btn_salir=findViewById(R.id.btn_cierre);




        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {

                    Toast.makeText(UsuarioLogueadoActivity.this, "Sus mentiras ", Toast.LENGTH_SHORT).show();
                    txt_email.setText(user.getEmail());
                    txt_nombre.setText(user.getDisplayName());
                    btn_salir.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mFirebaseAuth.signOut();
                            Intent i=new Intent(UsuarioLogueadoActivity.this,MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                    });
                } else {
                    startActivityForResult(
                            AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .setTheme(R.style.LoginTheme)
                            .setLogo(R.drawable.logo3)
                            .setIsSmartLockEnabled(false)
                            .build(), RC_SIGN_IN);
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthListener);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mAuthListener);

onBackPressed();
    }
}











