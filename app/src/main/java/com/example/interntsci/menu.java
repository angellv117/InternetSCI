package com.example.interntsci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
    public void pendientes(View v){
        Intent i = new Intent(menu.this, pendientes.class);
        startActivity(i);
    }

    public void clientes(View v){
        Intent i = new Intent(menu.this, Clientes.class);
        startActivity(i);
    }
    public void leerQR(View v){
        Intent i = new Intent(menu.this, leerQR.class);
        startActivity(i);
    }
}