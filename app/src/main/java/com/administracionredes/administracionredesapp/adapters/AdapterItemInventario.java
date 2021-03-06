package com.administracionredes.administracionredesapp.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.administracionredes.administracionredesapp.InventarioFormActivity;
import com.administracionredes.administracionredesapp.R;
import com.administracionredes.administracionredesapp.helpers.Data;
import com.administracionredes.administracionredesapp.models.Inventario;

import java.util.List;

public class AdapterItemInventario extends RecyclerView.Adapter<AdapterItemInventario.ViewHolderInventario> {
    Inventario inventario;
    private long mLastClickTime = 0;
    List<Inventario> marioList;
    Context context;
    Data data;
    public AdapterItemInventario(List<Inventario> marioList, Context context,Data data) {
        this.marioList = marioList;
        this.data=data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderInventario onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new ViewHolderInventario(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderInventario holder, int position) {
        final int[] clic = {0};
        inventario = marioList.get(position);
        String nombre_dispositivo = inventario.getNombre_dispositivo();
        holder.textView_nombre.setText(nombre_dispositivo);
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
                    Intent intent = new Intent(context, InventarioFormActivity.class);
                    intent.putExtra("Inventario", marioList.get(position));
                    intent.putExtra("dato", true);
                    context.startActivity(intent);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("Eliminar")
                        .setMessage("??T?? quieres eliminar este producto?")
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton("S??", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Inventario p = marioList.remove(holder.getAdapterPosition());// if you want to remove item you should do this: first remove item:
                                notifyItemRemoved(holder.getAdapterPosition());//Then  next step you must notify your recycler adapter that you remove an item
                                notifyItemRangeChanged(holder.getAdapterPosition(), marioList.size());
                                notifyDataSetChanged();
                               data.id(p.getId());
                            }
                        })
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton("No", null)
                        .setIcon(android.R.drawable.ic_delete)
                        .show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return marioList.size();
    }

    public static class ViewHolderInventario extends RecyclerView.ViewHolder {
        TextView textView_nombre;

        public ViewHolderInventario(View viewItem) {
            super(viewItem);
            textView_nombre = viewItem.findViewById(R.id.textView_nombre);
        }
    }
}