package com.administracionredes.administracionredesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.administracionredes.administracionredesapp.helpers.Collections;
import com.administracionredes.administracionredesapp.helpers.StaticHelper;
import com.administracionredes.administracionredesapp.helpers.Status;
import com.administracionredes.administracionredesapp.models.Localizacion;
import com.administracionredes.administracionredesapp.services.FirebaseHelper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class LocalizacionFormActivity extends AppCompatActivity implements Status {
    private TextInputLayout textInputLayout_nombre;
    private TextInputLayout textInputLayout_tipo;
    private TextInputLayout textInputLayout_localizacion;
    private TextInputLayout textInputLayout_status;
    private TextInputLayout textInputLayout_observaciones;

    private MaterialButton materialButton_agregar;
    Localizacion localizacion;

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
        getDatos();
        materialButton_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = textInputLayout_nombre.getEditText().getText().toString();
                String tipo = textInputLayout_tipo.getEditText().getText().toString();
                String localizacions = textInputLayout_localizacion.getEditText().getText().toString();
                String status = textInputLayout_status.getEditText().getText().toString();
                String observaciones = textInputLayout_observaciones.getEditText().getText().toString();

                if (evaluarDatos(nombre, textInputLayout_nombre) && evaluarDatos(tipo, textInputLayout_tipo) && evaluarDatos(localizacions, textInputLayout_localizacion) &&
                        evaluarDatos(status, textInputLayout_status) && evaluarDatos(observaciones, textInputLayout_observaciones)) {
                    //Conexión exitosa
                    String datos[] = {nombre, tipo, localizacions, status, observaciones};
                    if (getIntent().getBooleanExtra("dato", false)) {
                        //edicion e firebase
                        new FirebaseHelper().editar(LocalizacionFormActivity.this::status, Collections.LOCALIZACION.toString(), localizacion.getId(), StaticHelper.LOCALIZACIONKEYS, datos);
                    } else {
                        //agregacion en firebase
                        new FirebaseHelper().add(LocalizacionFormActivity.this::status, Collections.LOCALIZACION.toString(), StaticHelper.LOCALIZACIONKEYS, datos);
                    }
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

    public void getDatos() {
        if (getIntent().getBooleanExtra("dato", false)) {
            localizacion = (Localizacion) getIntent().getSerializableExtra("Localizacion");
            textInputLayout_nombre.getEditText().setText(localizacion.getNombre());
            textInputLayout_observaciones.getEditText().setText(localizacion.getObservaciones());
            textInputLayout_status.getEditText().setText(localizacion.getEstatus());
            textInputLayout_tipo.getEditText().setText(localizacion.getTipo());
            textInputLayout_localizacion.getEditText().setText(localizacion.getLocalizacion());
            materialButton_agregar.setText("Editar Localizacion");
        }
    }

    @Override
    public void status(String mensaje) {
        Toast.makeText(LocalizacionFormActivity.this, mensaje, Toast.LENGTH_SHORT).show();

    }
}