package com.example.interntsci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.interntsci.Models.pendiente;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class AgregarNota extends AppCompatActivity {


    private EditText nombre, descripcion, monto;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_nota);

        nombre = (EditText)findViewById(R.id.nombrePen);
        descripcion = (EditText)findViewById(R.id.descripcionPen);
        monto = findViewById(R.id.montotext);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        inicarFirebase();

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void inicarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void agregarnota(View v){
        String nom = nombre.getText().toString();
        String des = descripcion.getText().toString();
        String mon = monto.getText().toString();
        if (nom.length()!=0 && des.length()!=0){
            pendiente objPen = new pendiente();
            objPen.setId(UUID.randomUUID().toString());
            objPen.setNompendiente(nom);
            objPen.setDescripcion(des);
            objPen.setMonto(mon);
            databaseReference.child("Pendientes").child(objPen.getId()).setValue(objPen);
            Toast.makeText(this, "Pendiente agregado con exito", Toast.LENGTH_LONG).show();
            limpiar();
            finish();
        }
        else{
            Toast.makeText(this, "Agregue los campos", Toast.LENGTH_LONG).show();
            nombre.setError("Nombre del pendiente vacio");
            descripcion.setError("Descripcion vacia");
        }
    }

    private void limpiar() {
        nombre.setText("");
        descripcion.setText("");
    }
}