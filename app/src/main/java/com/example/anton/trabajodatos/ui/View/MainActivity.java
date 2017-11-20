package com.example.anton.trabajodatos.ui.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.anton.trabajodatos.R;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recylcer)
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
}
