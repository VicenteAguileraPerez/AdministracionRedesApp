package com.administracionredes.administracionredesapp.services;

import androidx.annotation.NonNull;

import com.administracionredes.administracionredesapp.helpers.Data;
import com.administracionredes.administracionredesapp.helpers.Status;
import com.administracionredes.administracionredesapp.models.Fallas;
import com.administracionredes.administracionredesapp.models.Inventario;
import com.administracionredes.administracionredesapp.models.Localizacion;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FirebaseHelper {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void add(Status status, String collection, String keys[], String datas[]) {
        Map<String, Object> data = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            data.put(keys[i], datas[i]);
        }
        // Add a new document with a generated ID
        db.collection(collection)
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        status.status("Agregación exitosa");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        status.status("Error al hacer la agregación");
                    }
                });
    }

    public void editar(Status status, String collection, String id, String keys[], String datas[]) {
        Map<String, Object> data = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            data.put(keys[i], datas[i]);
        }
        // Add a new document with a generated ID
        db.collection(collection).document(id).set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                status.status("Edición exitosa");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        status.status("Error al hacer la agregación");
                    }
                });
    }


    public void eliminar(Status status, String collection, String id) {
        db.collection(collection).document(id)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        status.status("Eliminación exitosa");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        status.status("Error al hacer la eliminación");
                    }
                });
    }

    public void leerFalla(Data data, String collection) {
        db.collection(collection)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Object> datos = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //{"GUIA","DISPOSITIVO", "TIPO","OBSERVACION"}
                                Fallas fallas = new Fallas(document.getId(), document.getData().get("GUIA").toString(), document.getData().get("DISPOSITIVO").toString(), document.getData().get("TIPO").toString(), document.getData().get("OBSERVACION").toString());
                                datos.add(fallas);
                            }
                            data.arrayList(datos);
                        } else {
                        }
                    }
                });
    }

    public void leerInventario(Data data, String collection) {
        db.collection(collection)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Object> datos = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //objeto
                                //{"NOMBRE","TIPO", "STATUS","OBSERVACIONES"};
                                Inventario inventario = new Inventario(document.getId(), document.getData().get("NOMBRE").toString(),
                                        document.getData().get("TIPO").toString(), document.getData().get("STATUS").toString(),
                                        document.getData().get("OBSERVACIONES").toString());
                                datos.add(inventario);
                            }
                            data.arrayList(datos);
                        } else {
                        }
                    }
                });
    }

    public void leerConfiguracion(Data data, String collection) {
        db.collection(collection)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList datos = new ArrayList();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //objeto
                                //
                            }
                        } else {

                        }
                    }
                });
    }

    public void leerLocalizacion(Data data, String collection) {
        db.collection(collection)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Object> datos = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //objeto {"NOMBRE","TIPO", "LOCALIZACION","STATUS","OBSERVACIONES"}
                                Localizacion localizacion = new Localizacion(document.getId(), document.getData().get("NOMBRE").toString(),
                                        document.getData().get("TIPO").toString(), document.getData().get("LOCALIZACION").toString(), document.getData().get("STATUS").toString(),
                                        document.getData().get("OBSERVACIONES").toString());
                                datos.add(localizacion);
                            }
                            data.arrayList(datos);
                        } else {
                        }
                    }
                });
    }
}