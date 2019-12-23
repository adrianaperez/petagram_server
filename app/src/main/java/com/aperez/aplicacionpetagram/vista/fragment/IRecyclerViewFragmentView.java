package com.aperez.aplicacionpetagram.vista.fragment;

import com.aperez.aplicacionpetagram.adapter.PetegramAdaptador;
import com.aperez.aplicacionpetagram.model.Petegram;

import java.util.ArrayList;

/**
 * Created by anahisalgado on 21/04/16.
 */
public interface IRecyclerViewFragmentView {

    public void generarGridLayoutVertical();

    public PetegramAdaptador crearAdaptador(ArrayList<Petegram> petegrams);

    public void inicializarAdaptadorRV(PetegramAdaptador adaptador);
}
