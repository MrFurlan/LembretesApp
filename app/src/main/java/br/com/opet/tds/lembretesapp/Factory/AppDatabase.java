package br.com.opet.tds.lembretesapp.Factory;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import br.com.opet.tds.lembretesapp.DAO.NotaDAO;
import br.com.opet.tds.lembretesapp.Model.Nota;

/**
 * Created by Diego on 25/04/2018.
 */

/*@Database indica que esta classe dá acesso aos recursos de banco de dados do aplicativo*/
@Database(entities = {Nota.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NotaDAO notaDAO();
    private static AppDatabase INSTANCE;


    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "nota_db").fallbackToDestructiveMigration().allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }


    public static void setINSTANCE(AppDatabase INSTANCE) {
        AppDatabase.INSTANCE = INSTANCE;
    }
}
