package br.com.opet.tds.lembretesapp.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import br.com.opet.tds.lembretesapp.DAO.NotaDAO;
import br.com.opet.tds.lembretesapp.Factory.AppDatabase;
import br.com.opet.tds.lembretesapp.Model.Nota;

/**
 * Created by Diego on 16/05/2018.
 */

public class NotaRepository {

    private NotaDAO notaDAO;
    private LiveData<List<Nota>> notas;

    public NotaRepository(Application app){
        AppDatabase db = AppDatabase.getDatabase(app);
        notaDAO = db.notaDAO();
        notas = notaDAO.getAll();
    }

    public LiveData<List<Nota>> findAll(){
        return notas;
    }

    public void insert(Nota nota){
        new insertAsynkTask(notaDAO).execute(nota);
    }

    private static class insertAsynkTask extends AsyncTask<Nota,Void,Void>{
        private NotaDAO notaDAO;

        insertAsynkTask(NotaDAO notaDAO){
            this.notaDAO = notaDAO;
        }

        @Override
        protected Void doInBackground(final Nota... nota){
            notaDAO.insertNota(nota[0]);
            return null;
        }
    }
}
