package com.android.proyectandroid;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ReestablecimientoContrasena extends AppCompatActivity {

    private EditText campoEmail;
    private Button btnRestaurar;
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reestablecimiento_contrasena);
        campoEmail=(EditText)findViewById(R.id.emailReestab);
        btnRestaurar=(Button)findViewById(R.id.btn_restablecer);
        auth = FirebaseAuth.getInstance();
        btnRestaurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reestablecerContrasena();
            }
        });

    }

    public void reestablecerContrasena(){
        if(campoEmail.getText().toString().equals("")||campoEmail==null){
            Toast.makeText(this, "Ingrese su email",Toast.LENGTH_LONG).show();
        }else{
            auth.sendPasswordResetEmail(campoEmail.getText().toString()
            )
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                               Toast.makeText(getApplicationContext(),"Un email se ha enviado a su correo para reestablecer la contraseña",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }
}
