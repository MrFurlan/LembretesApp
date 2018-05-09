package br.com.opet.tds.lembretesapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.opet.tds.lembretesapp.DAO.NotaDAO;
import br.com.opet.tds.lembretesapp.Factory.AppDatabase;
import br.com.opet.tds.lembretesapp.Model.Importancia;
import br.com.opet.tds.lembretesapp.Model.Nota;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("br.com.opet.tds.lembretesapp", appContext.getPackageName());
    }

    @Test
    public void testeInsertBanco() throws Exception{
        Context appContext = InstrumentationRegistry.getTargetContext();
        AppDatabase app = AppDatabase.getDatabase(appContext);
        NotaDAO notaDAO = app.notaDAO();
        Nota nota = new Nota();
        nota.setTexto("Oi");
        nota.setImportancia(Importancia.Media.ordinal());
        assertTrue(notaDAO.insertNota(nota) > 0);
        Nota resultado = notaDAO.findByNota(3);
        assertEquals(Importancia.Media.ordinal(),resultado.getImportancia());
        resultado.setImportancia(Importancia.Baixa.ordinal());
        assertTrue(notaDAO.update(resultado) > 0);
        assertTrue(notaDAO.delete(resultado) > 0);
    }
}
