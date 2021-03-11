package com.administracionredes.administracionredesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.administracionredes.administracionredesapp.adapters.AdapterItemFallas;
import com.administracionredes.administracionredesapp.helpers.Collections;
import com.administracionredes.administracionredesapp.helpers.Data;
import com.administracionredes.administracionredesapp.helpers.Status;
import com.administracionredes.administracionredesapp.models.Fallas;
import com.administracionredes.administracionredesapp.services.FirebaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FallasListaActivity extends AppCompatActivity implements Data, Status {
    private FloatingActionButton floatingActionButton;
    private ArrayList<Fallas> arrayList;
    private RecyclerView recyclerView;

    @Override
    protected void onResume() {
        super.onResume();
        new FirebaseHelper().leerFalla(FallasListaActivity.this, Collections.FALLAS.toString());
    }

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
    }

    public void abrirActividad() {
        Intent intent = new Intent(this, FallasFormActivity.class);
        startActivity(intent);
    }

    @Override
    public void arrayList(ArrayList<Object> data) {
        arrayList = new ArrayList<>();
        for (Object o : data) {
            Fallas fallas = (Fallas) o;
            arrayList.add(fallas);
        }
        llenar(arrayList);
    }

    public void llenar(ArrayList<Fallas> fallas) {
        recyclerView.setAdapter(new AdapterItemFallas(fallas, FallasListaActivity.this, FallasListaActivity.this));
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void id(String id) {
        new FirebaseHelper().eliminar(FallasListaActivity.this::status, Collections.FALLAS.toString(), id);
    }

    @Override
    public void status(String mensaje) {
        Toast.makeText(FallasListaActivity.this, mensaje, Toast.LENGTH_SHORT).show();
    }
}