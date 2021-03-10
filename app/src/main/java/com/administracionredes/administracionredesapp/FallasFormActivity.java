package com.administracionredes.administracionredesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.administracionredes.administracionredesapp.helpers.Status;
import com.administracionredes.administracionredesapp.models.Fallas;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class FallasFormActivity extends AppCompatActivity implements Status {
    private TextInputLayout textInputLayout_guia;
    private TextInputLayout textInputLayout_dispositivo;
    private TextInputLayout textInputLayout__tipo_de_falla;
    private TextInputLayout textInputLayout_observaciones;
    private MaterialButton materialButton_agregar;
    Fallas fallas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fallas_form);
        setTitle("Registro de fallas");
        textInputLayout_guia = findViewById(R.id.textInputLayout_guia);
        textInputLayout_dispositivo = findViewById(R.id.textInputLayout_dispositivo);
        textInputLayout__tipo_de_falla = findViewById(R.id.textInputLayout_tipo_de_falla);
        textInputLayout_observaciones = findViewById(R.id.textInputLayout_observaciones);
        materialButton_agregar = findViewById(R.id.button_agregar);

        materialButton_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String guia = textInputLayout_guia.getEditText().getText().toString();
                String dispositivo = textInputLayout_dispositivo.getEditText().getText().toString();
                String tipo_de_falla = textInputLayout__tipo_de_falla.getEditText().getText().toString();
                String observaciones = textInputLayout_observaciones.getEditText().getText().toString();
                if (evaluarDatos(guia, textInputLayout_guia) && evaluarDatos(dispositivo, textInputLayout_dispositivo) && evaluarDatos(tipo_de_falla, textInputLayout__tipo_de_falla) && evaluarDatos(observaciones, textInputLayout_observaciones)) {
                    //Conexión exitosa
                    String datos[] = {guia, dispositivo, tipo_de_falla, observaciones};
                    Snackbar.make(view, "Datos validos", Snackbar.LENGTH_SHORT).show();
                } else {
                    //Conexión NO exitosa
                    Snackbar.make(view, "Datos invalidos", Snackbar.LENGTH_SHORT).show();
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

    public void getDatos() {
        if (getIntent().getBooleanExtra("Fallas", false)) {
        }
    }

    @Override
    public void status(String mensaje) {

    }
}