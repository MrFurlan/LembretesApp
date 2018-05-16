package br.com.opet.tds.lembretesapp.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import br.com.opet.tds.lembretesapp.Model.Nota;
import br.com.opet.tds.lembretesapp.Repository.NotaRepository;

/**
 * Created by Diego on 09/05/2018.
 */

public class NotaViewModel extends AndroidViewModel {
   private NotaRepository notaRepository;
   private LiveData<List<Nota>> notas;

   public NotaViewModel(Application app){
       super(app);
       notaRepository = new NotaRepository(app);
       notas = notaRepository.findAll();
   }

   public LiveData<List<Nota>> findAll(){
       return notas;
   }

   public void insert(Nota nota){
       notaRepository.insert(nota);
   }
}
