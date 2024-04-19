package com.example.interntsci;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class crearQR extends AppCompatActivity {
    private TextView idd,nom, direc, tel, plann, obs, monto;
    private EditText mes;
    private ImageView imagen;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_qr);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        idd = findViewById(R.id.id);
        nom = findViewById(R.id.nombre);
        direc = findViewById(R.id.domicilio);
        tel = findViewById(R.id.tell);
        plann =findViewById(R.id.zona);
        mes =  findViewById(R.id.mesTx);
        obs = findViewById(R.id.adeu);
        monto = findViewById(R.id.montoTx);
        imagen= findViewById(R.id.verQR);
        button = findViewById(R.id.buttonQR);



        String id = getIntent().getStringExtra("ID_reci");
        String nomb = getIntent().getStringExtra("nombre_reci");
        String dire = getIntent().getStringExtra("domi_reci");
        String tell = getIntent().getStringExtra("tel_reci");
        String pla = getIntent().getStringExtra("plan_reci");
        String obser = getIntent().getStringExtra("ob_reci");
        String mess = mes.getText().toString();
        idd.setText(id);
        nom.setText(nomb);
        direc.setText(dire);
        tel.setText(tell);
        plann.setText(pla);
        obs.setText(obser);

        //creamos el QR
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
                String mess = mes.getText().toString();
                String mon = monto.getText().toString();
                if (mess.length()!=0 || mon.length()!=0){
                    MultiFormatWriter writer = new MultiFormatWriter();
                    try {
                        BitMatrix  matrix = writer.encode("ID : "+id+"\n Nombre : "+nomb+"\n Direccion : "
                                +dire+"\n Telefono : "+tell+"\n Zona : "+pla+"\n Observaaciones  : "+
                                obser+"\nPagó el mes de: "+mess+"\nMonto a pagar: "+mon+"\nFecha de pago: "+timeStamp, BarcodeFormat.QR_CODE, 280,280);
                        BarcodeEncoder encoder = new BarcodeEncoder();
                        Bitmap bitmap = encoder.createBitmap(matrix);
                        imagen.setImageBitmap(bitmap);

                    } catch (WriterException e) {

                    }
                }
                else {
                    mes.setError("Coloque el mes");
                    monto.setError("Coloque el monto a pagar");
                }


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


}