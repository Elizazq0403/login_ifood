package com.aprendiendo.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class RegistrarProducto extends AppCompatActivity {

    EditText etProducto, etReferences, etCategory, etPrecio, etDescripcion;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_producto);

        etProducto = findViewById(R.id.etProducto);
        etReferences = findViewById(R.id.etReferences);
        etCategory = findViewById(R.id.etCategory);
        etPrecio = findViewById(R.id.etPrecio);
        etDescripcion = findViewById(R.id.etDescripcion);
    }
        public void saveProd (View view){ // metodo boton
            Map<String, Object> user = new HashMap<>();
            //BreakIterator etName;
            String producto = etProducto.getText().toString();
            String references = etReferences.getText().toString();
            String category = etCategory.getText().toString();
            String precio = etPrecio.getText().toString();
            String descripcion = etDescripcion.getText().toString();
            user.put("producto", producto);//first es como la columna. Funciona como clave (pais)-> valor (colombia, ej), cuando yo pregunto por la clave me devuelve el valor
            user.put("references", references); // despues de declarado el objeto (user) puedo agregarle todos los campos que yo quiera
            user.put("category", category); //me agrega cada linea al documento en la coleccion
            user.put("precio", precio);
            user.put("descripcion", descripcion);
            db.collection("users")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(RegistrarProducto.this, "Usuario Registrado correctamente", Toast.LENGTH_SHORT).show();
                            Log.d("firebase", "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegistrarProducto.this, "Hubo un error", Toast.LENGTH_SHORT).show();
                            Log.w("firebase", "Error adding document", e);
                        }
                    });
    }
}