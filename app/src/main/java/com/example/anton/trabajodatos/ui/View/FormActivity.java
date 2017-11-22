package com.example.anton.trabajodatos.ui.View;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.anton.trabajodatos.Data.Model.PeopleModel;
import com.example.anton.trabajodatos.Data.Source.FormPostListener;
import com.example.anton.trabajodatos.Data.Source.MongoService;
import com.example.anton.trabajodatos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anton on 11/20/17.
 */

public class FormActivity extends AppCompatActivity implements FormPostListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etLastname)
    EditText etLastname;
    @BindView(R.id.etCellphone)
    EditText etCellphone;

    @BindView(R.id.layoutName)
    TextInputLayout layoutName;
    @BindView(R.id.layoutLastname)
    TextInputLayout layoutLastname;
    @BindView(R.id.layoutCellphone)
    TextInputLayout layoutCellphone;

    private String name;
    private String lastname;
    private String cellphone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.check);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                name = etName.getText().toString().trim();
                lastname = etLastname.getText().toString().trim();
                cellphone = etCellphone.getText().toString().trim();

                if (checkForm()) {
                    addObjectToMongoDB();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addObjectToMongoDB() {
        PeopleModel contact = new PeopleModel(name, lastname, cellphone);
        new MongoService().loadContact(contact, this);
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
        boolean cellphone = checkTextLayout(layoutCellphone, this.cellphone);
        return name && lastname && cellphone;
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

    @Override
    public void onSuccess() {
        onBackPressed();
    }

    @Override
    public void onError(String error) {
        Log.e("POST", error);
    }
}
