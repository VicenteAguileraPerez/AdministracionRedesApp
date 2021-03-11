package com.administracionredes.administracionredesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.administracionredes.administracionredesapp.adapters.AdapterItemTopic;
import com.administracionredes.administracionredesapp.helpers.Collections;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView_topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Administrador de redes");
        recyclerView_topic = findViewById(R.id.recyclerView_topic);
        llenar();

    }

    public void llenar() {
        ArrayList<String> topic = new ArrayList<>(Arrays.asList(Collections.FALLAS.toString(), Collections.LOCALIZACION.toString(), Collections.INVENTARIO.toString(), Collections.CONFIGURACION.toString()));
        recyclerView_topic.setAdapter(new AdapterItemTopic(topic, MainActivity.this));
        StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView_topic.setLayoutManager(mLayoutManager);
    }
}