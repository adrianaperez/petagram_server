package com.aperez.aplicacionpetagram.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aperez.aplicacionpetagram.DetallePetegram;
import com.aperez.aplicacionpetagram.model.Petegram;
import com.aperez.recyclerviewfragments.R;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

/**
 * Created by anahisalgado on 19/04/16.
 */
public class PetegramAdaptador extends RecyclerView.Adapter<PetegramAdaptador.ContactoViewHolder>{

    ArrayList<Petegram> petegrams;
    Activity activity;

    public PetegramAdaptador(ArrayList<Petegram> petegrams, Activity activity) {
        this.petegrams = petegrams;
        this.activity = activity;
    }

    @Override
    public ContactoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_grid_petegram, parent, false);

        return new ContactoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ContactoViewHolder contactoViewHolder, int position) {
        final Petegram petegram = petegrams.get(position);

        Picasso.with(activity)
                .load(petegram.getUrlFoto())
                .placeholder(R.drawable.shock_rave_bonus_icon)
                .into(contactoViewHolder.imgFoto);

        contactoViewHolder.tvLikes.setText(String.valueOf(petegram.getLikes()));

        contactoViewHolder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity, DetallePetegram.class);
                intent.putExtra("url", petegram.getUrlFoto());
                intent.putExtra("likes", petegram.getLikes());
                activity.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return petegrams.size();
    }

    public static class ContactoViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFoto;
        private TextView tvLikes;
        private ImageView imgBone;

        public ContactoViewHolder(View itemView) {
            super(itemView);

            imgFoto     = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvLikes    = (TextView) itemView.findViewById(R.id.tvLikes);
            imgBone     = (ImageView) itemView.findViewById(R.id.imgBone);
        }
    }
}
