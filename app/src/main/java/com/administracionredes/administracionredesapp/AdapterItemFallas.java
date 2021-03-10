package com.administracionredes.administracionredesapp;

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

import java.util.List;

public class AdapterItemFallas extends RecyclerView.Adapter<AdapterItemFallas.ViewHolderFallas> {
    Fallas fallas;
    private long mLastClickTime = 0;
    List<Fallas> fallasList;
    Context context;

    public AdapterItemFallas(List<Fallas> fallasList, Context context) {
        this.fallasList = fallasList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderFallas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(null, parent, false);
        return new ViewHolderFallas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFallas holder, int position) {
        final int[] clic = {0};
        fallas = fallasList.get(position);
        String guia = fallas.getGuia();
        holder.textView_nombre.setText(guia);
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
                    Intent intent = new Intent(context, FallasFormActivity.class);
                    intent.putExtra("Fallas", fallas);
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
                        .setMessage("¿Tú quieres eliminar este producto?")
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Fallas p = fallasList.remove(holder.getAdapterPosition());// if you want to remove item you should do this: first remove item:
                                notifyItemRemoved(holder.getAdapterPosition());//Then  next step you must notify your recycler adapter that you remove an item
                                notifyItemRangeChanged(holder.getAdapterPosition(), fallasList.size());
                                notifyDataSetChanged();
                                //product.deleteProduct(p.getId());
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
        return fallasList.size();
    }

    public static class ViewHolderFallas extends RecyclerView.ViewHolder {
        TextView textView_nombre;

        public ViewHolderFallas(View viewItem) {
            super(viewItem);
            //textView_nombre = viewItem.findViewById(R.id.textView_nombre);
        }
    }
}