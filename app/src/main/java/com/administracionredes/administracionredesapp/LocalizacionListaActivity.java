package com.administracionredes.administracionredesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.administracionredes.administracionredesapp.adapters.AdapterItemLocalizacion;
import com.administracionredes.administracionredesapp.models.Localizacion;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.UUID;

public class LocalizacionListaActivity extends AppCompatActivity {
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
        recyclerView.setAdapter(new AdapterItemLocalizacion(localizaciones, LocalizacionListaActivity.this));
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
}