package com.administracionredes.administracionredesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.administracionredes.administracionredesapp.adapters.AdapterItemLocalizacion;
import com.administracionredes.administracionredesapp.helpers.Collections;
import com.administracionredes.administracionredesapp.helpers.Data;
import com.administracionredes.administracionredesapp.helpers.Status;
import com.administracionredes.administracionredesapp.models.Localizacion;
import com.administracionredes.administracionredesapp.services.FirebaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class LocalizacionListaActivity extends AppCompatActivity implements Data, Status {
    private FloatingActionButton floatingActionButton;
    private ArrayList<Localizacion> arrayList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        recyclerView = findViewById(R.id.lista_localizaciones);
        floatingActionButton = findViewById(R.id.fab_mas);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActividad();
            }
        });
        arrayList();
    }

    public void abrirActividad() {
        Intent intent = new Intent(this, LocalizacionFormActivity.class);
        startActivity(intent);
    }

    public void llenar(ArrayList<Localizacion> localizaciones) {
        recyclerView.setAdapter(new AdapterItemLocalizacion(localizaciones, LocalizacionListaActivity.this,LocalizacionListaActivity.this));
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
    }

    public void arrayList() {
        arrayList = new ArrayList<Localizacion>();
        arrayList.add(new Localizacion(UUID.randomUUID().toString(), "1234AB", "Modem", "Se quemo", "ps esta quemao no ves","hola"));
        arrayList.add(new Localizacion(UUID.randomUUID().toString(), "1234AB", "Modem", "Se quemo", "ps esta quemao no ves","hola"));
        arrayList.add(new Localizacion(UUID.randomUUID().toString(), "1234AB", "Modem", "Se quemo", "ps esta quemao no ves","hola"));
        llenar(arrayList);
    }

    @Override
    public void arrayList(ArrayList<Object> data) {
        arrayList = new ArrayList<>();
        for (Object obj :data) {
            Localizacion loc = (Localizacion)obj;
            arrayList.add(loc);
        }
        llenar(arrayList);
    }

    @Override
    public void id(String id) {
        new FirebaseHelper().eliminar(LocalizacionListaActivity.this::status, Collections.LOCALIZACION.toString(),id);

    }

    @Override
    public void status(String mensaje) {
        Toast.makeText(LocalizacionListaActivity.this, mensaje,Toast.LENGTH_SHORT).show();
    }
}