package com.example.anton.trabajodatos.ui.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.anton.trabajodatos.Data.Model.PeopleModel;
import com.example.anton.trabajodatos.Data.Source.MongoService;
import com.example.anton.trabajodatos.Data.Source.ResponseListener;
import com.example.anton.trabajodatos.R;
import com.example.anton.trabajodatos.ui.Adapter.ContactAdapter;
import com.example.anton.trabajodatos.ui.Presenter.MainPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ResponseListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    MainPresenter mPresenter;
    ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        mPresenter = new MainPresenter(this);
        adapter = new ContactAdapter();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                download();
            }
        });
    }

    @OnClick(R.id.fab)
    public void onClick() {
        Intent intent = new Intent(this, FormActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSuccess(ArrayList<PeopleModel> contacts) {
        swipeRefreshLayout.setRefreshing(true);
        adapter.setContacts(contacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onError(String error) {

    }

    private void download() {
        new MongoService().getContacts(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        download();
    }
}
