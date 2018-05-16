package br.com.opet.tds.lembretesapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.opet.tds.lembretesapp.Model.Nota;
import br.com.opet.tds.lembretesapp.R;

/**
 * Created by Diego on 16/05/2018.
 */

public class NotaAdapter extends RecyclerView.Adapter<NotaAdapter.NotaViewHolder> {

    class NotaViewHolder extends RecyclerView.ViewHolder{
        private final TextView notaItemView;

        private NotaViewHolder(View itemView){
            super(itemView);
            notaItemView = itemView.findViewById(R.id.textViewNota);
        }
    }

    private final LayoutInflater mInflater;
    private List<Nota> mNotas;

    public NotaAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public NotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = mInflater.inflate(R.layout.recyclerview_item,parent,false);
        return new NotaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotaViewHolder holder, int position){
        if(mNotas != null){
            Nota atual = mNotas.get(position);
            holder.notaItemView.setText(atual.getTexto());
        }
    }

    public void setNotas(List<Nota> notas){
        mNotas = notas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount(){
        if(mNotas != null){
            return mNotas.size();
        }
        return 0;
    }
}
