package com.aperez.aplicacionpetagram.vista.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aperez.recyclerviewfragments.R;
import com.aperez.aplicacionpetagram.adapter.PetegramAdaptador;
import com.aperez.aplicacionpetagram.model.Petegram;
import com.aperez.aplicacionpetagram.presentador.IRecylerViewFragmentPresenter;
import com.aperez.aplicacionpetagram.presentador.RecyclerViewFragmentPresenter;

import java.util.ArrayList;

/**
 * Created by anahisalgado on 20/04/16.
 */
public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView {
    private ArrayList<Petegram> contactos;
    private RecyclerView rvContactos;
    private IRecylerViewFragmentPresenter presenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        rvContactos = (RecyclerView) v.findViewById(R.id.rvPetegrams);
        presenter = new RecyclerViewFragmentPresenter(this, getContext());
        return v;
    }


    @Override
    public void generarGridLayoutVertical() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rvContactos.setLayoutManager(gridLayoutManager);

    }

    @Override
    public PetegramAdaptador crearAdaptador(ArrayList<Petegram> contactos) {
        PetegramAdaptador adaptador = new PetegramAdaptador(contactos, getActivity()  );
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(PetegramAdaptador adaptador) {
        rvContactos.setAdapter(adaptador);
    }
}
