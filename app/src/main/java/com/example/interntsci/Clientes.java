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
import android.widget.ListView;
import android.widget.SearchView;

import com.example.interntsci.Models.Persona;
import com.example.interntsci.Models.pendiente;
import com.example.interntsci.adaptadores.adapterPersona;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Clientes extends AppCompatActivity {
    SearchView searchView;
    ArrayList<Persona> list;
    RecyclerView rv;
    adapterPersona adapter;
    LinearLayoutManager lm;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);


        searchView = findViewById(R.id.buscador);
        rv = findViewById(R.id.lista_de_clientes);
        lm = new LinearLayoutManager(this);
        list = new ArrayList<>();
        adapter = new adapterPersona(list, new adapterPersona.ItemClickListener() {
            @Override
            public void onItemClick(Persona details) {
                Intent siguiente = new Intent(Clientes.this, EditEliCliuente.class);
                siguiente.putExtra("nombre_reci", details.getNombre().toString());
                siguiente.putExtra("ID_reci",details.getId().toString());
                siguiente.putExtra("domi_reci",details.getDomicilio().toString());
                siguiente.putExtra("tel_reci",details.getTelefono().toString());
                siguiente.putExtra("plan_reci",details.getPlan().toString());
                siguiente.putExtra("ob_reci",details.getAdeuOOb().toString());
                startActivity(siguiente);
            }
        });
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);

        inicarFirebase();
        listarDatos();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query){
                    return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                buscar(s);
                return true;
            }
        });
    }

    private void inicarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
    private void listarDatos() {
        list.clear();
        databaseReference.child("Clientes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                list.clear();
                for (DataSnapshot obj : datasnapshot.getChildren()) {
                    Persona p = obj.getValue(Persona.class);
                    list.add(p);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void buscar (String g){
        ArrayList<Persona>mylista = new ArrayList<>();
        for (Persona obj : list){
            if (obj.getCEO().toLowerCase().contains(g.toLowerCase())){
                mylista.add(obj);
            }
            adapterPersona adapter = new adapterPersona(mylista, new adapterPersona.ItemClickListener() {
                @Override
                public void onItemClick(Persona details) {
                    Intent siguiente = new Intent(Clientes.this, EditEliCliuente.class);
                    siguiente.putExtra("nombre_reci", details.getNombre().toString());
                    siguiente.putExtra("ID_reci",details.getId().toString());
                    siguiente.putExtra("domi_reci",details.getDomicilio().toString());
                    siguiente.putExtra("tel_reci",details.getTelefono().toString());
                    siguiente.putExtra("plan_reci",details.getPlan().toString());
                    siguiente.putExtra("ob_reci",details.getAdeuOOb().toString());
                    startActivity(siguiente);
                }
            });
            rv.setAdapter(adapter);
        }
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
                Intent i = new Intent(Clientes.this,AgregarCliente.class);
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