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

import com.administracionredes.administracionredesapp.ConfiguracionesFormActivity;
import com.administracionredes.administracionredesapp.R;
import com.administracionredes.administracionredesapp.helpers.Data;
import com.administracionredes.administracionredesapp.models.Configuraciones;

import java.util.List;

public class AdapterItemConfiguraciones extends RecyclerView.Adapter<AdapterItemConfiguraciones.ViewHolderConfiguraciones> {
    Configuraciones configuraciones;
    private long mLastClickTime = 0;
    List<Configuraciones> configuracionesList;
    Context context;
    Data data;

    public AdapterItemConfiguraciones(List<Configuraciones> configuracionesList, Context context, Data data) {
        this.configuracionesList = configuracionesList;
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public AdapterItemConfiguraciones.ViewHolderConfiguraciones onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new AdapterItemConfiguraciones.ViewHolderConfiguraciones(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterItemConfiguraciones.ViewHolderConfiguraciones holder, int position) {
        final int[] clic = {0};
        configuraciones = configuracionesList.get(position);
        String lugar = configuraciones.getLugar();
        holder.textView_nombre.setText(lugar);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 2000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                ++clic[0];
                if (clic[0] == 1) {
                    Intent intent = new Intent(context, ConfiguracionesFormActivity.class);
                    intent.putExtra("Configuraciones", configuracionesList.get(position));
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
                                Configuraciones p = configuracionesList.remove(holder.getAdapterPosition());// if you want to remove item you should do this: first remove item:
                                notifyItemRemoved(holder.getAdapterPosition());//Then  next step you must notify your recycler adapter that you remove an item
                                notifyItemRangeChanged(holder.getAdapterPosition(), configuracionesList.size());
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
        return configuracionesList.size();
    }

    public static class ViewHolderConfiguraciones extends RecyclerView.ViewHolder {
        TextView textView_nombre;

        public ViewHolderConfiguraciones(View viewItem) {
            super(viewItem);
            textView_nombre = viewItem.findViewById(R.id.textView_nombre);
        }
    }
}
