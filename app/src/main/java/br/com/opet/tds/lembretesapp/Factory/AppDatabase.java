package br.com.opet.tds.lembretesapp.Factory;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

import br.com.opet.tds.lembretesapp.DAO.NotaDAO;
import br.com.opet.tds.lembretesapp.Model.Importancia;
import br.com.opet.tds.lembretesapp.Model.Nota;

/**
 * Created by Diego on 25/04/2018.
 */

/*@Database indica que esta classe dá acesso aos recursos de banco de dados do aplicativo*/
@Database(entities = {Nota.class}, version = 6)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NotaDAO notaDAO();
    private static AppDatabase INSTANCE;


    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "nota_db").fallbackToDestructiveMigration().allowMainThreadQueries().addCallback(sRoomDatabaseCallback)
                            .build();
        }
        return INSTANCE;
    }


    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void,Void,Void>{
        private final NotaDAO notaDAO;

        PopulateDbAsync(AppDatabase db){
            notaDAO = db.notaDAO();
        }

        @Override
        protected Void doInBackground(final Void... params){
            List<Nota> notas = notaDAO.getAllWithoutLiveData();
            if (notas.size() == 0) {
                Nota nota = new Nota(0, "Teste", "Bem Legal", "24/10/2018", false);
                nota.setImportancia(Importancia.Media.ordinal());
                notaDAO.insertNota(nota);
            }
            return null;
        }
    }
}
