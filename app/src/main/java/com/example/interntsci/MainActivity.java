package com.example.interntsci;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

import java.text.BreakIterator;

import static android.service.controls.ControlsProviderService.TAG;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    public EditText correo, contra;
    public Button inicio; //registro, olvidar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        correo = (EditText)findViewById(R.id.correo);
        contra = (EditText)findViewById(R.id.contraseña);

        inicio = findViewById(R.id.inicio);

        mAuth = FirebaseAuth.getInstance();


    }
    public void updateUI(FirebaseUser account){

        if(account != null){
            Toast.makeText(this,"Acceso consedido",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,menu.class));

        }else {
            Toast.makeText(this,"No se encuentra registrado",Toast.LENGTH_LONG).show();
        }

    }

        public void iniciar(View view) {
            String email = correo.getText().toString();
            String password = contra.getText().toString();
            try {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                                    dameToastdeerror(errorCode);

                                }
                            }
                        });
            }
            catch (RuntimeException e){
                Toast.makeText(MainActivity.this, "Lena todos los campos", Toast.LENGTH_LONG).show();
                correo.setError("Correo vacio");
                contra.setError("Contraseña Vacia");
            }

        }


 private void dameToastdeerror(String error) {

        switch (error) {

            case "ERROR_INVALID_CUSTOM_TOKEN":
                Toast.makeText(MainActivity.this, "El formato del token personalizado es incorrecto. Por favor revise la documentación", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_CUSTOM_TOKEN_MISMATCH":
                Toast.makeText(MainActivity.this, "El token personalizado corresponde a una audiencia diferente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_CREDENTIAL":
                Toast.makeText(MainActivity.this, "La credencial de autenticación proporcionada tiene un formato incorrecto o ha caducado.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_EMAIL":
                Toast.makeText(MainActivity.this, "La dirección de correo electrónico está mal formateada.", Toast.LENGTH_LONG).show();
                correo.setError("La dirección de correo electrónico está mal formateada.");
                correo.requestFocus();
                break;

            case "ERROR_WRONG_PASSWORD":
                Toast.makeText(MainActivity.this, "La contraseá±a no es válida o el usuario no tiene contrasña.", Toast.LENGTH_LONG).show();
                contra.setError("la contraseña es incorrecta ");
                contra.requestFocus();
                contra.setText("");
                break;

            case "ERROR_USER_MISMATCH":
                Toast.makeText(MainActivity.this, "Las credenciales proporcionadas no corresponden al usuario que inició sesión anteriormente..", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_REQUIRES_RECENT_LOGIN":
                Toast.makeText(MainActivity.this, "Esta operación es sensible y requiere autenticación reciente. Inicie sesión nuevamente antes de volver a intentar esta solicitud.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                Toast.makeText(MainActivity.this, "Ya existe una cuenta con la misma direcciÃ³n de correo electrÃ³nico pero diferentes credenciales de inicio de sesiÃ³n. Inicie sesiÃ³n con un proveedor asociado a esta direcciÃ³n de correo electrÃ³nico.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_EMAIL_ALREADY_IN_USE":
                Toast.makeText(MainActivity.this, "La dirección de correo electrónico ya está siendo utilizada por otra cuenta..   ", Toast.LENGTH_LONG).show();
                correo.setError("La dirección de correo electrónico ya está siendo utilizada por otra cuenta.");
                correo.requestFocus();
                break;

            case "ERROR_CREDENTIAL_ALREADY_IN_USE":
                Toast.makeText(MainActivity.this, "Esta credencial ya está asociada con una cuenta de usuario diferente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_DISABLED":
                Toast.makeText(MainActivity.this, "La cuenta de usuario ha sido inhabilitada por un administrador..", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_TOKEN_EXPIRED":
                Toast.makeText(MainActivity.this, "La credencial del usuario ya no es válida. El usuario debe iniciar sesión nuevamente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_NOT_FOUND":
                Toast.makeText(MainActivity.this, "No hay ningún registro de usuario que corresponda a este identificador. Es posible que se haya eliminado al usuario.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_USER_TOKEN":
                Toast.makeText(MainActivity.this, "La credencial del usuario ya no es válida. El usuario debe iniciar sesión nuevamente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_OPERATION_NOT_ALLOWED":
                Toast.makeText(MainActivity.this, "Esta operación no está permitida. Debes habilitar este servicio en la consola.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_WEAK_PASSWORD":
                Toast.makeText(MainActivity.this, "La contraseña proporcionada no es válida..", Toast.LENGTH_LONG).show();
                contra.setError("La contraseña no es válida, debe tener al menos 6 caracteres");
                contra.requestFocus();
                break;

        }
    }

    public void registro (View r){
        Intent i = new Intent(MainActivity.this, registro.class);
        startActivity(i);
    }

    public void resuperar(View r){
        Intent i = new Intent(MainActivity.this,recuperar.class);
        startActivity(i);
    }
}