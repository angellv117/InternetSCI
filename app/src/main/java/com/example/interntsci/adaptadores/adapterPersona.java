package com.example.interntsci.adaptadores;

import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interntsci.Models.Persona;
import com.example.interntsci.Models.pendiente;
import com.example.interntsci.R;

import java.util.ArrayList;
import java.util.List;

public class adapterPersona extends RecyclerView.Adapter<adapterPersona.viwHolderPerosna>  {

    List<Persona> personaList;
    private  ItemClickListener mClickListener;


    public adapterPersona(List<Persona> personaList, ItemClickListener itemClickListener) {
        this.personaList = personaList;
        this.mClickListener =  itemClickListener;
    }
    @NonNull
    @Override
    public viwHolderPerosna onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_personas,parent,false);
       viwHolderPerosna holder = new viwHolderPerosna(v) ;

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viwHolderPerosna holder, int position) {
        Persona ms = personaList.get(position);
        holder.nombre.setText(ms.getNombre());
        holder.id.setText(ms.getId());
        holder.direccion.setText(ms.getDomicilio());
        holder.telefono.setText(ms.getTelefono());
        holder.plan.setText(ms.getPlan());
        holder.adeudos.setText(ms.getAdeuOOb());
        holder.itemView.setOnClickListener(v -> {
            mClickListener.onItemClick(personaList.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return personaList.size();
    }

     public interface ItemClickListener{
        void onItemClick(Persona details);
     }


    public class viwHolderPerosna extends RecyclerView.ViewHolder {

        TextView id, nombre, direccion, telefono, plan, adeudos;
        public viwHolderPerosna(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            nombre = itemView.findViewById(R.id.nombre);
            direccion = itemView.findViewById(R.id.domicilio);
            telefono = itemView.findViewById(R.id.tell);
            plan = itemView.findViewById(R.id.zona);
            adeudos = itemView.findViewById(R.id.adeu);
        }
    }
}
