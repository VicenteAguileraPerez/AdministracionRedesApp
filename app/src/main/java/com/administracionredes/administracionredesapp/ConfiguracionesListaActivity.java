package com.administracionredes.administracionredesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.administracionredes.administracionredesapp.adapters.AdapterItemConfiguraciones;
import com.administracionredes.administracionredesapp.helpers.Collections;
import com.administracionredes.administracionredesapp.helpers.Data;
import com.administracionredes.administracionredesapp.helpers.Status;
import com.administracionredes.administracionredesapp.models.Configuraciones;
import com.administracionredes.administracionredesapp.services.FirebaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ConfiguracionesListaActivity extends AppCompatActivity implements Data, Status {
    private FloatingActionButton floatingActionButton;
    private ArrayList<Configuraciones> arrayList;
    private RecyclerView recyclerView;

    @Override
    protected void onResume() {
        super.onResume();
        new FirebaseHelper().leerConfiguracion(ConfiguracionesListaActivity.this, Collections.CONFIGURACION.toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_configuraciones);
        recyclerView = findViewById(R.id.lista_configuraciones);
        floatingActionButton = findViewById(R.id.fab_mas);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActividad();
            }
        });
    }

    public void abrirActividad() {
        Intent intent = new Intent(this, ConfiguracionesFormActivity.class);
        startActivity(intent);
    }

    @Override
    public void arrayList(ArrayList<Object> data) {
        arrayList = new ArrayList<>();

        for (Object o : data) {
            Configuraciones config = (Configuraciones) o;
            arrayList.add(config);
        }
        llenar(arrayList);
    }

    public void llenar(ArrayList<Configuraciones> configuraciones) {
        recyclerView.setAdapter(new AdapterItemConfiguraciones(configuraciones, ConfiguracionesListaActivity.this, ConfiguracionesListaActivity.this));
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void id(String id) {
        new FirebaseHelper().eliminar(ConfiguracionesListaActivity.this::status, Collections.CONFIGURACION.toString(), id);
    }

    @Override
    public void status(String mensaje) {
        Toast.makeText(ConfiguracionesListaActivity.this, mensaje, Toast.LENGTH_SHORT).show();
    }
}