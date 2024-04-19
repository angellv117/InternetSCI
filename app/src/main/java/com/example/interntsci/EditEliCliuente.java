package com.example.interntsci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.interntsci.Models.Persona;
import com.example.interntsci.Models.pendiente;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditEliCliuente extends AppCompatActivity {
    private EditText ID,nombre, direccion, telefono, plan, observacioes;
    private ImageView imagen;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_eli_cliuente);
        ID = (EditText)findViewById(R.id.IDEE);
        nombre = (EditText)findViewById(R.id.nombreCEE);
        direccion = (EditText)findViewById(R.id.direcCEE);
        telefono = (EditText)findViewById(R.id.telCEE);
        plan = (EditText)findViewById(R.id.planCEE);
        observacioes = (EditText)findViewById(R.id.AdeuCEE);
        imagen = findViewById(R.id.imageView5);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        inicarFirebase();

        String id = getIntent().getStringExtra("ID_reci");
        String nom = getIntent().getStringExtra("nombre_reci");
        String dire = getIntent().getStringExtra("domi_reci");
        String tell = getIntent().getStringExtra("tel_reci");
        String pla = getIntent().getStringExtra("plan_reci");
        String obser = getIntent().getStringExtra("ob_reci");
        ID.setText(id);
        nombre.setText(nom);
        direccion.setText(dire);
        telefono.setText(tell);
        plan.setText(pla);
        observacioes.setText(obser);

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent siguiente = new Intent(EditEliCliuente.this, crearQR.class);
                siguiente.putExtra("nombre_reci", nombre.getText().toString());
                siguiente.putExtra("ID_reci",ID.getText().toString());
                siguiente.putExtra("domi_reci",direccion.getText().toString());
                siguiente.putExtra("tel_reci",telefono.getText().toString());
                siguiente.putExtra("plan_reci",plan.getText().toString());
                siguiente.putExtra("ob_reci",observacioes.getText().toString());
                startActivity(siguiente);
            }
        });

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

    public void editar(View V){
        Persona p = new Persona();
        p.setId(ID.getText().toString());
        p.setNombre(nombre.getText().toString());
        p.setDomicilio(direccion.getText().toString().trim());
        p.setTelefono(telefono.getText().toString().trim());
        p.setPlan(plan.getText().toString().trim());
        p.setAdeuOOb(observacioes.getText().toString().trim());
        p.setCEO(ID.getText().toString(),nombre.getText().toString(),direccion.getText().toString(),plan.getText().toString(),telefono.getText().toString(),observacioes.getText().toString());
        databaseReference.child("Clientes").child(getIntent().getStringExtra("ID_reci")).setValue(p);
        Toast.makeText(this,"Actualizado", Toast.LENGTH_LONG).show();
        limpiar();
        finish();
    }

    public void eliminar(View V){
        Persona p = new Persona();
        p.setId(getIntent().getStringExtra("ID_reci"));
        databaseReference.child("Clientes").child(ID.getText().toString()).removeValue();
        Toast.makeText(this,"Eliminado", Toast.LENGTH_LONG).show();
        limpiar();
        finish();
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