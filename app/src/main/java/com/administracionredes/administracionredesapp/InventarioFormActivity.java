package com.administracionredes.administracionredesapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.administracionredes.administracionredesapp.helpers.Collections;
import com.administracionredes.administracionredesapp.helpers.StaticHelper;
import com.administracionredes.administracionredesapp.helpers.Status;
import com.administracionredes.administracionredesapp.models.Inventario;
import com.administracionredes.administracionredesapp.services.FirebaseHelper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;


public class InventarioFormActivity extends AppCompatActivity implements Status {

    private TextInputLayout textInputLayout_nombre_dispositivo;
    private TextInputLayout textInputLayout_observaciones;
    private TextInputLayout textInputLayout_tipo;
    private TextInputLayout textInputLayout_status;
    private MaterialButton materialButton_agregar;

    Inventario inventario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario_form);

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

                    if (getIntent().getBooleanExtra("dato", false)) {
                        //edicion e firebase
                        new FirebaseHelper().editar(InventarioFormActivity.this::status, Collections.INVENTARIO.toString(), inventario.getId(), StaticHelper.INVENTARIOKEYS, datos);
                    } else {
                        //agregacion en firebase
                        new FirebaseHelper().add(InventarioFormActivity.this::status, Collections.INVENTARIO.toString(), StaticHelper.INVENTARIOKEYS, datos);
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
            inventario = (Inventario) getIntent().getSerializableExtra("Inventario");
            textInputLayout_nombre_dispositivo.getEditText().setText(inventario.getNombre_dispositivo());
            textInputLayout_observaciones.getEditText().setText(inventario.getObservaciones());
            textInputLayout_status.getEditText().setText(inventario.getStatus());
            textInputLayout_tipo.getEditText().setText(inventario.getStatus());
            materialButton_agregar.setText("Editar Device");
        }
    }

    @Override
    public void status(String mensaje) {
        Toast.makeText(InventarioFormActivity.this, mensaje, Toast.LENGTH_SHORT).show();
    }
}

