package br.com.opet.tds.lembretesapp.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by Diego on 25/04/2018.
 */

/*Para o Room, a anotação @Entity inidica que a classe é mapeada para uma tabela em banco de dados*/

@Entity
public class Nota {
    /*A anotação Primary Key indica que este atributo é chave primaria na tabela Nota*/
    @PrimaryKey(autoGenerate = true)
    private long ID;
    private String titulo;
    private String texto;

    /*@ColumnInfo é utilizada para indicar que este atributo será renomeado na tabela Nota*/
    @ColumnInfo(name = "data_nota")
    private String dataNota;

    @ColumnInfo(name = "avisar_nota")
    private boolean avisarNota;

    private int importancia;

    public int getImportancia() {
        return importancia;
    }

    public void setImportancia(int importancia) {
        this.importancia = importancia;
    }

    public Nota() {
    }

    public Nota(long ID, String titulo, String texto, String dataNota, boolean avisarNota) {
        this.ID = ID;
        this.titulo = titulo;
        this.texto = texto;
        this.dataNota = dataNota;
        this.avisarNota = avisarNota;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getDataNota() {
        return dataNota;
    }

    public void setDataNota(String dataNota) {
        this.dataNota = dataNota;
    }

    public boolean isAvisarNota() {
        return avisarNota;
    }

    public void setAvisarNota(boolean avisarNota) {
        this.avisarNota = avisarNota;
    }
}
