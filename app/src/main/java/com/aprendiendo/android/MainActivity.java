package com.aprendiendo.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText etName, etLastname;//, //etYears, etIdentificacion;
    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.Botton4);
        etLastname = findViewById(R.id.Botton5);
        //etYears = findViewById(R.id.etYears);
        //etIdentificacion = findViewById(R.id.etIdentificacion);

        // Create a new user with a first and last name
    }


    //Metodo boton Registrarse
    public void Registrarse (View view){
        Intent registrarse = new Intent(this, RegistrarUsuarios.class);
        startActivity(registrarse);
    }

    // Metodo boton Loggin provicional
    public void Login (View view){
        Intent login = new Intent(this, Inicio.class);
        startActivity(login);
    }



    public void registrerUser(View view){ // metodo boton
            String Name = etName.getText().toString();
            String Lastname = etLastname.getText().toString();
            mAuth.createUserWithEmailAndPassword(Name, Lastname)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(MainActivity.this, "Usuario registrado", Toast.LENGTH_SHORT).show(); //aca se coloca el activity intent para pasar al activity que quiero que llegue despues del registro
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(MainActivity.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

// Add a new document with a generated ID

    }
