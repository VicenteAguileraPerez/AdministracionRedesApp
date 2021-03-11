package com.administracionredes.administracionredesapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.administracionredes.administracionredesapp.ConfiguracionesListaActivity;
import com.administracionredes.administracionredesapp.FallasListaActivity;
import com.administracionredes.administracionredesapp.InventarioListaActivity;
import com.administracionredes.administracionredesapp.LocalizacionListaActivity;
import com.administracionredes.administracionredesapp.R;
import com.administracionredes.administracionredesapp.helpers.Collections;

import java.util.ArrayList;
import java.util.List;

public class AdapterItemTopic extends RecyclerView.Adapter<AdapterItemTopic.ViewHolderTopic> {
    private long mLastClickTime = 0;
    List<String> productosList;
    Context context;


    public AdapterItemTopic(ArrayList<String> productosArrayList, Context context) {
        this.context = context;
        this.productosList = productosArrayList;
    }

    // create the item
    @NonNull
    @Override
    public ViewHolderTopic onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new ViewHolderTopic(view);
    }

    //fill the item with the information
    @Override
    public void onBindViewHolder(@NonNull ViewHolderTopic holder, int position) {
        final int[] clic = {0};
        String title = productosList.get(position);
        holder.textView_nombre.setText(title);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 2000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                mLastClickTime = SystemClock.elapsedRealtime();
                ++clic[0];
                if (clic[0] == 1) {
                    Intent intent;
                    if (title.equals(Collections.CONFIGURACION.toString())) {
                        intent = new Intent(context, ConfiguracionesListaActivity.class);
                    } else if (title.equals(Collections.FALLAS.toString())) {
                        intent = new Intent(context, FallasListaActivity.class);
                    } else if (title.equals(Collections.LOCALIZACION.toString())) {
                        intent = new Intent(context, LocalizacionListaActivity.class);
                    } else {
                        intent = new Intent(context, InventarioListaActivity.class);
                    }
                    context.startActivity(intent);
                }
            }
        });
    }

    //count the size of the elements of the grup of items
    @Override
    public int getItemCount() {
        return productosList.size();
    }

    //should store references to the views you’ll need when binding your data on the holder
    public static class ViewHolderTopic extends RecyclerView.ViewHolder {
        TextView textView_nombre;

        public ViewHolderTopic(View viewItem) {
            super(viewItem);
            textView_nombre = viewItem.findViewById(R.id.textView_nombre);
        }
    }
}