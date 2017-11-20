package com.example.anton.trabajodatos.ui.View;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.anton.trabajodatos.Data.Model.PeopleModel;
import com.example.anton.trabajodatos.R;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etGrade)
    EditText etGrade;

    @BindView(R.id.layoutName)
    TextInputLayout layoutName;
    @BindView(R.id.layoutLastname)
    TextInputLayout layoutLastname;
    @BindView(R.id.layoutEmail)
    TextInputLayout layoutEmail;
    @BindView(R.id.layoutGrade)
    TextInputLayout layoutGrade;
    @BindView(R.id.layoutSpinnerGrade)
    TextInputLayout layoutSpinnerGrade;
    @BindView(R.id.layoutCargo)
    TextInputLayout layoutCargo;

    @BindView(R.id.gradeSpinner)
    AppCompatSpinner gradeSpinner;
    @BindView(R.id.cargoSpinner)
    AppCompatSpinner cargoSpinner;


    private String schoolName;
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
        BusProvider.getInstance().register(this);
        setContentView(R.layout.activity_form_bolsa);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_check_white_24dp);

        schoolName = getIntent().getStringExtra("School");

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

                //etBirth.setText(sdf.format(myCalendar.getTime()));
            }

        };

        /**
         * Se crean los adapters junto con las respectivas listas de cargos y grados.
         */

        List<String> list_grado_colegio = Arrays.asList(getResources()
                .getStringArray(R.array.contenido_spinner_grado));
        List<String> list_cargo;

        /**
         * El contenido cambia dependiendo de la institucion:
         *  1. Colegio: Alumno y Profesor
         *  2. Universidad: Asesor, Pregonero, etc.
         */

        if (checkULIMA()) {
            list_cargo = Arrays.asList(getResources()
                    .getStringArray(R.array.contenido_spinner_cargo_universidad));
            layoutSpinnerGrade.setVisibility(View.GONE);
        } else {
            list_cargo = Arrays.asList(getResources()
                    .getStringArray(R.array.contenido_spinner_cargo_colegio));
        }

        final SpinnerArrayAdapter gradoAdapter =
                new SpinnerArrayAdapter(this, R.layout.spinner_form_item, list_grado_colegio);
        final SpinnerArrayAdapter cargoAdapter =
                new SpinnerArrayAdapter(this, R.layout.spinner_form_item, list_cargo);

        gradoAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        cargoAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        gradeSpinner.setAdapter(gradoAdapter);
        gradeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /** If user change the default selection
                 * First item is disable and it is used for hint
                 */
                if (position > 0) {
                    // Notify the selected item text
                    if (parent.getCount() - 1 == position) {
                        layoutGrade.setVisibility(View.VISIBLE);
                        layoutGrade.requestFocus();
                    } else {
                        layoutGrade.setVisibility(View.GONE);
                        layoutEmail.requestFocus();
                    }
                }
                layoutSpinnerGrade.setErrorEnabled(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        gradeSpinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                hideKeyboard();

                return false;
            }
        });
        cargoSpinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                hideKeyboard();
                return false;
            }
        });
        cargoSpinner.setAdapter(cargoAdapter);
        cargoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Si es profesor@, no debe seleccionar grado
             * pos 0 - hint, pos 1 - alumno, pos 2 - profesor(a)
             */
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (!checkULIMA()) {
                    if (position == 2) {
                        layoutSpinnerGrade.setVisibility(View.GONE);
                        layoutGrade.setVisibility(View.GONE);
                        grade = null;
                    } else if (position == 1 && layoutSpinnerGrade.getVisibility() == View.GONE) {
                        layoutSpinnerGrade.setVisibility(View.VISIBLE);
                    }
                    layoutCargo.setErrorEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                name = etName.getText().toString().trim();
                lastname = etLastname.getText().toString().trim();
                //String birth = etBirth.getText().toString().trim();
                if (layoutGrade.getVisibility() == View.VISIBLE) {
                    grade = gradeSpinner.getSelectedItem().toString().trim();
                } else if (layoutSpinnerGrade.getVisibility() == View.VISIBLE) {
                    grade = etGrade.getText().toString().trim();
                }
                email = etEmail.getText().toString().trim();
                gradeOtros = etGrade.getText().toString().trim();
                cargo = cargoSpinner.getSelectedItem().toString().trim();

                if (checkForm()) {
                    addObjectToFirebase();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /*@OnClick(R.id.btnPicker)
    public void btnPickerOnClick(){
        imm.hideSoftInputFromWindow(etName.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(etLastname.getWindowToken(), 0);
        //imm.hideSoftInputFromWindow(etDocument.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(etGrade.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(etEmail.getWindowToken(), 0);
        new DatePickerDialog(this, date, myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }*/

    private boolean checkULIMA() {
        return schoolName.equalsIgnoreCase(getString(R.string.ulima));
    }

    public void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void addObjectToFirebase() {
        if (StringFunctions.validateEmail(email)) {
            PeopleModel model = new PeopleModel();
            model.setName(name);
            model.setLastname(lastname);
            if (grade != null) {
                if (!gradeOtros.equals("")) {
                    model.setGrade(gradeOtros);
                } else {
                    model.setGrade(grade);
                }
            }
            model.setCargo(cargo);
            model.setEmail(email);
            Firebase newPostRef = FirebaseConfig.fbPeople.child(schoolName).push();
            newPostRef.setValue(model);
            String date = DateFunctions.getDateFromString(new Date());
            FirebaseConfig.fbPeople.child(schoolName).child(newPostRef.getKey()).child(date).setValue(true);
            onBackPressed();
        } else {
            etEmail.setError(getString(R.string.email_invalido));
        }
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
        boolean email = checkTextLayout(layoutEmail, this.email);
        boolean spinner = checkSpinners();
        return name && lastname && email && spinner;
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

    private boolean checkSpinners() {
        boolean bGradoSpinner;
        boolean bGradoLayout;
        boolean bCargo;

        /**
         *  Se prefiere trabajar con layoutSpinnerGrade para evitar errores (Anton).
         */
        if (layoutSpinnerGrade.getVisibility() == View.VISIBLE
                && grade.equalsIgnoreCase("Selecciona un grado")
                && layoutGrade.getVisibility() == View.GONE) {
            layoutSpinnerGrade.setError(getString(R.string.error_spinner));
            bGradoSpinner = false;
        } else {
            bGradoSpinner = true;
            layoutSpinnerGrade.setErrorEnabled(false);
        }
        if (layoutGrade.getVisibility() == View.VISIBLE && gradeOtros.equals("")) {
            layoutGrade.setError(getString(R.string.required));
            bGradoLayout = false;
        } else {
            layoutGrade.setErrorEnabled(false);
            bGradoLayout = true;
        }
        if (cargo.equalsIgnoreCase("Selecciona un cargo")) {
            layoutCargo.setError(getString(R.string.error_spinner));
            bCargo = false;
        } else {
            bCargo = true;
            layoutCargo.setErrorEnabled(false);
        }

        return bCargo && bGradoLayout && bGradoSpinner;
    }
}
