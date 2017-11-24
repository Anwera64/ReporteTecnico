package com.example.anton.trabajodatos.ui.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.anton.trabajodatos.Data.Model.PeopleModel;
import com.example.anton.trabajodatos.R;
import com.example.anton.trabajodatos.ui.View.MainActivity;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anton on 11/20/17.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private ArrayList<PeopleModel> contacts;
    private Context context;
    private String selectedID;
    private ArrayList<ViewHolder> holders;

    public ContactAdapter(Context context) {
        contacts = new ArrayList<>();
        this.context = context;
        holders = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final PeopleModel contact = contacts.get(position);

        holders.add(holder);
        holder.id = contact.getId();
        final MainActivity main = (MainActivity) context;

        holder.tvAge.setText(String.valueOf(contact.getCellphone()));
        holder.tvName.setText(contact.getFullName());
        holder.root.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (holder.selected) {
                    view.setBackgroundColor(Color.WHITE);
                    main.hideDelete();

                } else {
                    selectedID = contact.getId();
                    selectView();
                    main.showDelete();
                }
                holder.selected = !holder.selected;
                return false;
            }
        });

    }

    private void selectView() {
        for (ViewHolder holder : holders) {
            if (holder.selected && !holder.id.equals(selectedID)) {
                holder.root.setBackgroundColor(Color.WHITE);
                holder.selected = !holder.selected;
            } else if (holder.id.equals(selectedID)) {
                holder.root
                        .setBackgroundColor(ResourcesCompat
                                .getColor(context.getResources(), R.color.grey_300, null));
            }
        }
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void setContacts(ArrayList<PeopleModel> contacts) {
        this.contacts = contacts;
        Collections.reverse(this.contacts);
    }

    public String getSelectedID() {
        return selectedID;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        boolean selected;
        String id;

        @BindView(R.id.tvTitle)
        TextView tvName;
        @BindView(R.id.tvSubTitle)
        TextView tvAge;
        @BindView(R.id.root)
        RelativeLayout root;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
