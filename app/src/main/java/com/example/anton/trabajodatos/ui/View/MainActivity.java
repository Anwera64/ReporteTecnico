package com.example.anton.trabajodatos.ui.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.anton.trabajodatos.R;
import com.example.anton.trabajodatos.ui.Adapter.ContactAdapter;
import com.example.anton.trabajodatos.ui.Presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        mPresenter = new MainPresenter();

        ContactAdapter adapter = new ContactAdapter();
        adapter.setContacts(mPresenter.getContacts());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.fab)
    public void onClick() {
        Intent intent = new Intent(this, FormActivity.class);
        startActivity(intent);
    }
}
