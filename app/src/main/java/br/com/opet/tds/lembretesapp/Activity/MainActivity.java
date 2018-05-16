package br.com.opet.tds.lembretesapp.Activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.facebook.stetho.Stetho;

import java.util.List;

import br.com.opet.tds.lembretesapp.Adapter.NotaAdapter;
import br.com.opet.tds.lembretesapp.Model.Nota;
import br.com.opet.tds.lembretesapp.R;
import br.com.opet.tds.lembretesapp.ViewHolder.NotaViewHolder;
import br.com.opet.tds.lembretesapp.ViewModel.NotaViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    NotaViewModel model;
    NotaViewHolder notaViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final NotaAdapter adapter = new NotaAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        model = ViewModelProviders.of(this).get(NotaViewModel.class);
        model.findAll().observe(this, new Observer<List<Nota>>() {
            @Override
            public void onChanged(@Nullable List<Nota> notas) {
                adapter.setNotas(notas);
            }
        });

        notaViewHolder = new NotaViewHolder(findViewById(R.id.activity_main));
        notaViewHolder.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnSalvarNota){
            Nota nota = new Nota();
            nota.setTitulo(notaViewHolder.getTextfromEditTitulo());
            nota.setTexto(notaViewHolder.getTextfromEditNota());
            nota.setImportancia(notaViewHolder.getPrioridadefromSpinner());
            nota.setAvisarNota(notaViewHolder.isCheckLembrarChecked());
            nota.setDataNota(notaViewHolder.getTextfromTextDataNota());

            model.insert(nota);
        }
    }
}
