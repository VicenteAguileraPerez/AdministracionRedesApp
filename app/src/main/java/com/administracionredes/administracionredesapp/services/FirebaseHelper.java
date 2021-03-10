package com.administracionredes.administracionredesapp.services;

import androidx.annotation.NonNull;

import com.administracionredes.administracionredesapp.helpers.Data;
import com.administracionredes.administracionredesapp.helpers.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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

    public void leerFalla(Data data, String collection) {
        db.collection(collection)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList datos = new ArrayList();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //objeto
                            }
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
                            ArrayList datos = new ArrayList();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //objeto
                            }
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
                            ArrayList datos = new ArrayList();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //objeto
                            }
                        } else {
                        }
                    }
                });
    }
}