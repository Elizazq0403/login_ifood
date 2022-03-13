package com.aprendiendo.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistrarUsuarios extends AppCompatActivity {

    EditText etName, etIdentif, etCargo, ettextEmail;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuarios);

            etName = findViewById(R.id.etName);
            etIdentif = findViewById(R.id.etIdentif);
            etCargo = findViewById(R.id.etCargo);
            ettextEmail = findViewById(R.id.textEmail);
    }
    //Metodo boton Continuar
    public void Menuppl (View view){
        Intent continuar = new Intent(this, Inicio.class);
        startActivity(continuar);
    }

    public void saveUser(View view){ // metodo boton
        Map<String, Object> user = new HashMap<>();
        //BreakIterator etName;
        String name = etName.getText().toString();
        String identif = etIdentif.getText().toString();
        String cargo = etCargo.getText().toString();
        String email = ettextEmail.getText().toString();
        user.put("name", name);//first es como la columna. Funciona como clave (pais)-> valor (colombia, ej), cuando yo pregunto por la clave me devuelve el valor
        user.put("identif", identif); // despues de declarado el objeto (user) puedo agregarle todos los campos que yo quiera
        user.put("cargo", cargo); //me agrega cada linea al documento en la coleccion
        user.put("email", email);
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(RegistrarUsuarios.this, "Usuario Registrado correctamente", Toast.LENGTH_SHORT).show();
                        Log.d("firebase", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegistrarUsuarios.this, "Hubo un error", Toast.LENGTH_SHORT).show();
                        Log.w("firebase", "Error adding document", e);
                    }
                });
    }



}