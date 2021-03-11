package com.administracionredes.administracionredesapp.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.administracionredes.administracionredesapp.R;
import java.util.ArrayList;
import java.util.List;

public class AdapterItemTopic extends RecyclerView.Adapter<AdapterItemTopic.ViewHolderTopic>
{
    private long mLastClickTime =0;
    List<String> productosList;
    Context context;


    public AdapterItemTopic(ArrayList<String> productosArrayList, Context context)
    {
        this.context =context;
        this.productosList = productosArrayList;
    }

    // create the item
    @NonNull
    @Override
    public ViewHolderTopic onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        return new ViewHolderTopic(view);
    }

    //fill the item with the information
    @Override
    public void onBindViewHolder(@NonNull ViewHolderTopic holder, int position)
    {
        final int[] clic = {0};
        String title = productosList.get(position);
        holder.textView_nombre.setText(title);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 2000){
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                mLastClickTime = SystemClock.elapsedRealtime();
                ++clic[0];
                if(clic[0] ==1)
                {
                    Intent intent;
                   // Intent intent = new Intent(context, class);
                    //intent.putExtra("producto", productos);
                    //intent.putExtra("dato", true);
                    //context.startActivity(intent);
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
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                               /*  p = productosList.remove(holder.getAdapterPosition());// if you want to remove item you should do this: first remove item:
                                notifyItemRemoved(holder.getAdapterPosition());//Then  next step you must notify your recycler adapter that you remove an item
                                notifyItemRangeChanged(holder.getAdapterPosition(), productosList.size());
                                notifyDataSetChanged();
                                product.deleteProduct(p.getId());*/

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

    //count the size of the elements of the grup of items
    @Override
    public int getItemCount() {
        return productosList.size();
    }

    //should store references to the views you’ll need when binding your data on the holder
    public static class ViewHolderTopic extends RecyclerView.ViewHolder
    {

        TextView textView_nombre;


        public ViewHolderTopic(View viewItem)
        {
            super(viewItem);
            textView_nombre = viewItem.findViewById(R.id.textView_nombre);
        }


    }









}


