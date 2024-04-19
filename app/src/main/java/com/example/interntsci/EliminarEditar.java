package com.example.interntsci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.interntsci.Models.pendiente;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class EliminarEditar extends AppCompatActivity {
    private EditText nombre, descripcion, monto;
    ListView lista_pendientes;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_editar);
        nombre = (EditText)findViewById(R.id.nomPenEE);
        descripcion = (EditText)findViewById(R.id.descripcionPenEE);
        monto = findViewById(R.id.montotextEE);

        lista_pendientes = (ListView)findViewById(R.id.lista_de_pendientes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        inicarFirebase();

        String nom = getIntent().getStringExtra("pendiente_reci");
        String descri = getIntent().getStringExtra("descripcion_reci");
        String mon = getIntent().getStringExtra("monto_reci");
        String id = getIntent().getStringExtra("ID_reci");
        nombre.setText(nom);
        descripcion.setText(descri);
        monto.setText(mon);
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

    private void limpiar() {
        nombre.setText("");
        descripcion.setText("");
    }
    
    public void editar(View V){
        pendiente p = new pendiente();
        p.setId(getIntent().getStringExtra("ID_reci"));
        p.setNompendiente(nombre.getText().toString());
        p.setDescripcion(descripcion.getText().toString().trim());
        p.setMonto(monto.getText().toString());
        databaseReference.child("Pendientes").child(getIntent().getStringExtra("ID_reci")).setValue(p);
        Toast.makeText(this,"Actualizado", Toast.LENGTH_LONG).show();
        limpiar();
        finish();
    }

    public void eliminar(View V){
        pendiente p = new pendiente();
        p.setId(getIntent().getStringExtra("ID_reci"));
        databaseReference.child("Pendientes").child(getIntent().getStringExtra("ID_reci")).removeValue();
        Toast.makeText(this,"Eliminado", Toast.LENGTH_LONG).show();
        limpiar();
        finish();
    }
}