package com.administracionredes.administracionredesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.administracionredes.administracionredesapp.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class InventarioFormActivity extends AppCompatActivity {

    private TextInputLayout textInputLayout_nombre_dispositivo;
    private TextInputLayout textInputLayout_observaciones;
    private TextInputLayout textInputLayout_tipo;
    private TextInputLayout textInputLayout_status;
    private MaterialButton materialButton_agregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario_form);
        //Mario Alberto
        //Gradle
        setTitle("Inventario");

        textInputLayout_nombre_dispositivo = findViewById(R.id.textInputLayout_nombre_dispositivo);
        textInputLayout_observaciones = findViewById(R.id.textInputLayout_observaciones);
        textInputLayout_status = findViewById(R.id.textInputLayout_status);
        textInputLayout_tipo = findViewById(R.id.textInputLayout_tipo);
        materialButton_agregar = findViewById(R.id.button_agregar);

        materialButton_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre_dispositivo = textInputLayout_nombre_dispositivo.getEditText().getText().toString();
                String tipo = textInputLayout_tipo.getEditText().getText().toString();
                String status = textInputLayout_status.getEditText().getText().toString();
                String observaciones = textInputLayout_observaciones.getEditText().getText().toString();

                if (evaluarDatos(nombre_dispositivo, textInputLayout_nombre_dispositivo) && evaluarDatos(tipo, textInputLayout_tipo) &&
                        evaluarDatos(status, textInputLayout_status) && evaluarDatos(observaciones, textInputLayout_observaciones)) {
                    //Conexión exitosa
                    String datos[] = {nombre_dispositivo, tipo, status, observaciones};
                    Snackbar.make(view, "Datos válidos", Snackbar.LENGTH_SHORT).show();
                } else {
                    //Conexión
                    Snackbar.make(view, "Datos inválidos", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

    }

    public boolean evaluarDatos(String valor, TextInputLayout textInputLayout) {
        if (!valor.equals("")) {
            return true;
        } else {
            textInputLayout.setError("Campo vacío");
            return false;
        }
    }
}

