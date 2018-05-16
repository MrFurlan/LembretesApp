package br.com.opet.tds.lembretesapp.ViewHolder;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.opet.tds.lembretesapp.Model.Importancia;
import br.com.opet.tds.lembretesapp.R;

/**
 * Created by Diego on 09/05/2018.
 */

public class NotaViewHolder implements DatePickerDialog.OnDateSetListener {

    private EditText editTitulo;
    private EditText editNota;
    private TextView textDataNota;
    private CheckBox checkLembrar;
    private Spinner spinnerPrioridade;
    private Button btnSalvarNota;

    public NotaViewHolder(View view){
        editTitulo = view.findViewById(R.id.edit_titulo);
        editNota = view.findViewById(R.id.edit_texto);
        textDataNota = view.findViewById(R.id.textDataNota);
        checkLembrar = view.findViewById(R.id.checkLembrarNota);
        spinnerPrioridade = view.findViewById(R.id.spinnerPrioridade);
        btnSalvarNota = view.findViewById(R.id.btnSalvarNota);



        textDataNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),NotaViewHolder.this,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
    }

    public void setOnClickListener(View.OnClickListener listener){
        btnSalvarNota.setOnClickListener(listener);
    }

    public String getTextfromEditTitulo(){
        return editTitulo.getText().toString();
    }

    public String getTextfromEditNota(){
        return editNota.getText().toString();
    }

    public String getTextfromTextDataNota(){
        return textDataNota.getText().toString();
    }

    public boolean isCheckLembrarChecked(){
        return checkLembrar.isChecked();
    }

    public int getPrioridadefromSpinner(){
        switch (spinnerPrioridade.getSelectedItem().toString()){
            case "Alta":
                return Importancia.Alta.ordinal();
            case "Media":
                return Importancia.Media.ordinal();
            case "Baixa":
                return Importancia.Baixa.ordinal();
        }
        return -1;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar dataSelecionada = Calendar.getInstance();
        dataSelecionada.set(i,i1,i2);
        textDataNota.setText(sdf.format(dataSelecionada.getTime()));
    }
}
