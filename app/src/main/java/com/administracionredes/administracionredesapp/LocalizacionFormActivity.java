package com.administracionredes.administracionredesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class LocalizacionFormActivity extends AppCompatActivity {
    private TextInputLayout textInputLayout_nombre;
    private TextInputLayout textInputLayout_tipo;
    private TextInputLayout textInputLayout_localizacion;
    private TextInputLayout textInputLayout_status;
    private TextInputLayout textInputLayout_observaciones;
    private MaterialButton materialButton_agregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizacion_form);
        //Antonio García

        //Agregué archivos gradle
        setTitle("Localización");

        //Se enlazan las variables con sus id's
        textInputLayout_nombre = findViewById(R.id.textInputLayout_nombre);
        textInputLayout_tipo = findViewById(R.id.textInputLayout_tipo);
        textInputLayout_localizacion = findViewById(R.id.textInputLayout_localizacion);
        textInputLayout_status = findViewById(R.id.textInputLayout_status);
        textInputLayout_observaciones = findViewById(R.id.textInputLayout_observaciones);
        materialButton_agregar = findViewById(R.id.button_agregar);

        materialButton_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = textInputLayout_nombre.getEditText().getText().toString();
                String tipo = textInputLayout_tipo.getEditText().getText().toString();
                String localizacion = textInputLayout_localizacion.getEditText().getText().toString();
                String status = textInputLayout_status.getEditText().getText().toString();
                String observaciones = textInputLayout_observaciones.getEditText().getText().toString();

                if (evaluarDatos(nombre, textInputLayout_nombre) && evaluarDatos(tipo, textInputLayout_tipo) && evaluarDatos(localizacion, textInputLayout_localizacion) &&
                        evaluarDatos(status, textInputLayout_status) && evaluarDatos(observaciones, textInputLayout_observaciones)) {
                    //Conexión exitosa
                    String datos[] = {nombre, tipo, localizacion, status, observaciones};
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