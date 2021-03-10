package com.administracionredes.administracionredesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.administracionredes.administracionredesapp.adapters.AdapterItemFallas;
import com.administracionredes.administracionredesapp.adapters.AdapterItemInventario;
import com.administracionredes.administracionredesapp.helpers.Collections;
import com.administracionredes.administracionredesapp.helpers.Data;
import com.administracionredes.administracionredesapp.helpers.Status;
import com.administracionredes.administracionredesapp.models.Fallas;
import com.administracionredes.administracionredesapp.models.Inventario;
import com.administracionredes.administracionredesapp.services.FirebaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.UUID;

public class InventarioListaActivity extends AppCompatActivity implements Data, Status {
    private FloatingActionButton floatingActionButton;
    private ArrayList<Inventario> arrayList;
    private RecyclerView recyclerView;


    @Override
    protected void onResume(){
        super.onResume();
        new FirebaseHelper().leerInventario(null, Collections.INVENTARIO.toString());
    }

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

    }

    public void abrirActividad() {
        Intent intent = new Intent(this, InventarioFormActivity.class);
        startActivity(intent);
    }



    @Override
    public void arrayList(ArrayList<Object> data) {
        arrayList = new ArrayList<>();
        for (Object o : data) {
            Inventario inventario = (Inventario) o;
            arrayList.add(inventario);
        }
        llenar(arrayList);
    }

    public void llenar(ArrayList<Inventario> Inventario) {
        recyclerView.setAdapter(new AdapterItemInventario(Inventario, InventarioListaActivity.this));
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void id(String id) {
        new FirebaseHelper().eliminar(InventarioListaActivity.this::status, Collections.INVENTARIO.toString(), id);
    }

    @Override
    public void status(String mensaje) {
        Toast.makeText(InventarioListaActivity.this, mensaje, Toast.LENGTH_SHORT).show();
    }

   /* public void arrayList() {
        arrayList = new ArrayList<Inventario>();
        arrayList.add(new Inventario(UUID.randomUUID().toString(), "1234AB", "Modem", "Se quemo", "ps esta quemao no ves"));
        arrayList.add(new Inventario(UUID.randomUUID().toString(), "1234AB", "Modem", "Se quemo", "ps esta quemao no ves"));
        arrayList.add(new Inventario(UUID.randomUUID().toString(), "1234AB", "Modem", "Se quemo", "ps esta quemao no ves"));
        llenar(arrayList);
    }*/

}