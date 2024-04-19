package com.example.interntsci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class leerQR extends AppCompatActivity {
    TextView Texresul;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leer_qr);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Texresul = findViewById(R.id.textoedit);
        boton = findViewById(R.id.escanear);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrador = new IntentIntegrator(leerQR.this);
                integrador.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrador.setPrompt("Lector - CDP");
                integrador.setCameraId(0);
                integrador.setBeepEnabled(true);
                integrador.setBarcodeImageEnabled(true);
                integrador.initiateScan();
            }
        });
    }
    protected void onActivityResult(int requesResult, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requesResult,resultCode,data);
        if(result != null){
            if (result.getContents()== null){
                Toast.makeText(leerQR.this,"Lectura cancelada :(", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(leerQR.this,result.getContents(), Toast.LENGTH_SHORT).show();
                Texresul.setText(result.getContents());
            }
        }
        else {
            super.onActivityResult(requesResult,resultCode,data);
        }
    }

    @Override
    public
    boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}