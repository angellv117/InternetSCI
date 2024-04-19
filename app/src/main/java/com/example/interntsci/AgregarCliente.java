package com.example.interntsci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.interntsci.Models.Persona;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AgregarCliente extends AppCompatActivity {
    private EditText ID,nombre, direccion, telefono, plan, observacioes;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cliente);
        ID = (EditText)findViewById(R.id.IDEE);
        nombre = (EditText)findViewById(R.id.nombreCEE);
        direccion = (EditText)findViewById(R.id.direcCEE);
        telefono = (EditText)findViewById(R.id.telCEE);
        plan = (EditText)findViewById(R.id.planCEE);
        observacioes = (EditText)findViewById(R.id.AdeuCEE);
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

    public void agregarCliente(View v){
        String nom = nombre.getText().toString();
        String dire = direccion.getText().toString();
        String id = ID.getText().toString();
        String tell = telefono.getText().toString();
        String plann = plan.getText().toString();
        String obser = observacioes.getText().toString();
        if (nom.length()!=0 && id.length()!=0 ){
            Persona objPen = new Persona();
            objPen.setId(id);
            objPen.setNombre(nom);
            objPen.setDomicilio(dire);
            objPen.setTelefono(tell);
            objPen.setPlan(plann);
            objPen.setAdeuOOb(obser);
            objPen.setCEO(id,nom,dire,tell,obser,plann);
            databaseReference.child("Clientes").child(objPen.getId()).setValue(objPen);
            Toast.makeText(this, "Cliente agregado con exito", Toast.LENGTH_LONG).show();
            limpiar();
            finish();
        }
        else{
            Toast.makeText(this, "Agregue los campos", Toast.LENGTH_LONG).show();
            nombre.setError("Nombre del pendiente vacio");
            ID.setError("ID vacia");
        }
    }

    private void limpiar() {
        nombre.setText("");
        direccion.setText("");
        ID.setText("");
        telefono.setText("");
        plan.setText("");
        observacioes.setText("");
    }
}