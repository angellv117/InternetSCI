package com.example.interntsci.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interntsci.Models.Persona;
import com.example.interntsci.Models.pendiente;
import com.example.interntsci.R;

import java.util.List;

public class adapterNotas extends RecyclerView.Adapter<adapterNotas.viwHolderPerosna>  {

    List<pendiente> notaList;
    private  ItemClickListener mClickListener;


    public adapterNotas(List<pendiente> notaList, ItemClickListener itemClickListener) {
        this.notaList = notaList;
        this.mClickListener =  itemClickListener;
    }

    @NonNull
    @Override
    public viwHolderPerosna onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_notas,parent,false);
       viwHolderPerosna holder = new viwHolderPerosna(v) ;

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viwHolderPerosna holder, int position) {
        pendiente ms = notaList.get(position);
        holder.nombre.setText(ms.getNompendiente());
        holder.descripcion.setText(ms.getDescripcion());
        holder.monto.setText(ms.getMonto());
        holder.itemView.setOnClickListener(v -> {
            mClickListener.onItemClick(notaList.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return notaList.size();
    }

     public interface ItemClickListener{
        void onItemClick(pendiente details);
     }


    public class viwHolderPerosna extends RecyclerView.ViewHolder {

        TextView nombre, monto, descripcion, id;
        public viwHolderPerosna(@NonNull View itemView) {
            super(itemView);
            monto = itemView.findViewById(R.id.ontoTex);

            nombre = itemView.findViewById(R.id.pendientetex);
            descripcion = itemView.findViewById(R.id.decripcionTex);
        }
    }
}
