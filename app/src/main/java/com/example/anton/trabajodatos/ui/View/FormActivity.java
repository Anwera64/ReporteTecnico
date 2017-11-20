package com.example.anton.trabajodatos.ui.View;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.anton.trabajodatos.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anton on 11/20/17.
 */

public class FormActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etLastname)
    EditText etLastname;

    @BindView(R.id.layoutName)
    TextInputLayout layoutName;
    @BindView(R.id.layoutLastname)
    TextInputLayout layoutLastname;

    private Calendar myCalendar;
    private InputMethodManager imm;
    private DatePickerDialog.OnDateSetListener date;

    private String name;
    private String lastname;
    private String grade;
    private String email;
    private String gradeOtros;
    private String cargo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.check);

        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            }

        };

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                name = etName.getText().toString().trim();
                lastname = etLastname.getText().toString().trim();
                //String birth = etBirth.getText().toString().trim();

                if (checkForm()) {
                    addObjectToMongoDB();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void addObjectToMongoDB() {

    }

    /**
     * Revisa si el formulario esta completo.
     * Se instancia cada booleano ya que se debe ejecutar las funciones de setear error en cada item vacio.
     *
     * @return true or false dependiendo de si esta completo el formulario
     */

    private boolean checkForm() {
        boolean name = checkTextLayout(layoutName, this.name);
        boolean lastname = checkTextLayout(layoutLastname, this.lastname);
        return name && lastname;
    }

    /**
     * Verifica si hay input alguno en los textEdit
     *
     * @param view  - el layout para mostrar el error
     * @param value - el valor a verificar
     * @return true or false
     */

    private boolean checkTextLayout(TextInputLayout view, String value) {
        if (value.equals("")) {
            view.setError(getString(R.string.required));
            return false;
        } else {
            view.setErrorEnabled(false);
            return true;
        }
    }

}
