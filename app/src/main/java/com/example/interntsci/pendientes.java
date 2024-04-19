package com.example.interntsci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.interntsci.Models.Persona;
import com.example.interntsci.Models.pendiente;
import com.example.interntsci.adaptadores.adapterNotas;
import com.example.interntsci.adaptadores.adapterPersona;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class pendientes extends AppCompatActivity {
    ArrayList<pendiente> list;
    RecyclerView rv;
    adapterNotas adapter;
    LinearLayoutManager lm;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendientes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        rv = findViewById(R.id.lista_de_pendientes);
        lm = new LinearLayoutManager(this);
        list = new ArrayList<>();
        adapter = new adapterNotas(list, new adapterNotas.ItemClickListener() {
            @Override
            public void onItemClick(pendiente details) {
                Intent siguiente = new Intent(pendientes.this,EliminarEditar .class);
                siguiente.putExtra("pendiente_reci", details.getNompendiente().toString());
                siguiente.putExtra("descripcion_reci",details.getDescripcion().toString());
                siguiente.putExtra("monto_reci",details.getMonto().toString());
                siguiente.putExtra("ID_reci",details.getId().toString());
                startActivity(siguiente);
            }
        });
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
        inicarFirebase();
        listarDatos();
    }

    private void listarDatos() {
        list.clear();
        databaseReference.child("Pendientes").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                    list.clear();
                    for (DataSnapshot obj : datasnapshot.getChildren()) {
                        pendiente p = obj.getValue(pendiente.class);
                        list.add(p);
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
    }

    private void inicarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.munu_pen,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add:
                Intent i = new Intent(pendientes.this,AgregarNota.class);
                startActivity(i);
                break;
            case android.R.id.home:
                finish();
                return true;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        return super.onOptionsItemSelected(item);
    }


}