package com.administracionredes.administracionredesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.administracionredes.administracionredesapp.adapters.AdapterItemFallas;
import com.administracionredes.administracionredesapp.models.Fallas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.UUID;

public class FallasListaActivity extends AppCompatActivity {
    private FloatingActionButton floatingActionButton;
    private ArrayList<Fallas> arrayList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fallas_lista);
        recyclerView = findViewById(R.id.lista_fallas);
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
        Intent intent = new Intent(this, FallasFormActivity.class);
        startActivity(intent);
    }

    public void llenar(ArrayList<Fallas> fallas) {
        recyclerView.setAdapter(new AdapterItemFallas(fallas, FallasListaActivity.this));
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
    }

    public void arrayList() {
        arrayList = new ArrayList<Fallas>();
        arrayList.add(new Fallas(UUID.randomUUID().toString(), "ABC-123", "Tarjeta de red", "Incendio", "Fuego por todos lados"));
        arrayList.add(new Fallas(UUID.randomUUID().toString(), "DEF-456", "Módem", "Choque eléctrico", "A causa de tormenta se dañaron los cables"));
        arrayList.add(new Fallas(UUID.randomUUID().toString(), "GHI-789", "Switch", "Mala configuración", "No se agregaron bien la IPs"));
        llenar(arrayList);
    }

}