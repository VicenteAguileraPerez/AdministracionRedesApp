package com.administracionredes.administracionredesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity
{

    private TextInputLayout textInputLayout_nombre;
    private TextInputLayout textInputLayout_observaciones;
    private MaterialButton materialButton_agregar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Administrador de redes");

        textInputLayout_nombre = findViewById(R.id.textInputLayout_nombre);
        textInputLayout_observaciones = findViewById(R.id.textInputLayout_observaciones);
        materialButton_agregar = findViewById(R.id.button_agregar);


        materialButton_agregar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String nombre = textInputLayout_nombre.getEditText().getText().toString();
                String observaciones =textInputLayout_observaciones.getEditText().getText().toString();
                if(evaluarDatos(nombre,textInputLayout_nombre) && evaluarDatos(observaciones,textInputLayout_observaciones))
                {

                    ///conexion a firebase
                    String datos[] = {nombre,observaciones};
                    Snackbar.make(view,"Datos validos",Snackbar.LENGTH_SHORT).show();
                }
                else
                {
                    Snackbar.make(view,"Datos invalidos",Snackbar.LENGTH_SHORT).show();
                }
            }
        });

    }

    public boolean evaluarDatos(String valor,  TextInputLayout textInputLayout)
    {
        if(!valor.equals(""))
        {
            return true;
        }
        else
        {
            textInputLayout.setError("Campo vacío");
            return false;
        }
    }
}