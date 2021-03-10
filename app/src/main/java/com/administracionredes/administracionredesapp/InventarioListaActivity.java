package com.administracionredes.administracionredesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.administracionredes.administracionredesapp.adapters.AdapterItemInventario;
import com.administracionredes.administracionredesapp.models.Inventario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.UUID;

public class InventarioListaActivity extends AppCompatActivity {
    private FloatingActionButton floatingActionButton;
    private ArrayList<Inventario> arrayList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario_lista);
        recyclerView = findViewById(R.id.lista_inventario);
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
        Intent intent = new Intent(this, InventarioFormActivity.class);
        startActivity(intent);
    }

    public void llenar(ArrayList<Inventario> Inventario) {
        recyclerView.setAdapter(new AdapterItemInventario(Inventario, InventarioListaActivity.this));
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
    }

    public void arrayList() {
        arrayList = new ArrayList<Inventario>();
        arrayList.add(new Inventario(UUID.randomUUID().toString(), "1234AB", "Modem", "Se quemo", "ps esta quemao no ves"));
        arrayList.add(new Inventario(UUID.randomUUID().toString(), "1234AB", "Modem", "Se quemo", "ps esta quemao no ves"));
        arrayList.add(new Inventario(UUID.randomUUID().toString(), "1234AB", "Modem", "Se quemo", "ps esta quemao no ves"));
        llenar(arrayList);
    }
}