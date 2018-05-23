package br.com.opet.tds.lembretesapp.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.support.annotation.NonNull;

import java.util.List;

import br.com.opet.tds.lembretesapp.Model.Nota;

/**
 * Created by Diego on 25/04/2018.
 */

/*A anotação @DAO indica que esta interface disponibiliza métodos de acesso abstratos na database*/
@Dao
public interface NotaDAO {

    @Query("SELECT * from Nota")
    LiveData<List<Nota>> getAll();

    @Query("SELECT * from Nota")
    List<Nota> getAllWithoutLiveData();

    @Query("SELECT * from Nota WHERE ID in (:notasID)")
    List<Nota> loadNotasByIds(int[] notasID);

    @Query("SELECT * from Nota WHERE ID LIKE :id")
    Nota findByNota(int id);

    @Insert
    void insertAll(@NonNull Nota... notas);

    @Insert
    long insertNota(Nota nota);

    @Delete
    int delete(Nota nota);

    @Query("DELETE from Nota")
    void deleteAll();

    @Update
    int update(Nota nota);

}
