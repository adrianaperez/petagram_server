package com.aperez.aplicacionpetagram.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.aperez.aplicacionpetagram.model.ConstructorPetagrams;
import com.aperez.aplicacionpetagram.model.Petegram;
import com.aperez.aplicacionpetagram.restApi.IEndpointsApi;
import com.aperez.aplicacionpetagram.restApi.adapter.RestApiAdapter;
import com.aperez.aplicacionpetagram.restApi.model.PetegramResponse;
import com.aperez.aplicacionpetagram.vista.fragment.IRecyclerViewFragmentView;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by anahisalgado on 21/04/16.
 */
public class RecyclerViewFragmentPresenter implements IRecylerViewFragmentPresenter {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorPetagrams constructorPetagrams;
    private ArrayList<Petegram> petegrams;

    public  RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        obtenerMediosRecientes();
    }

    @Override
    public void obtenerContactos() {
        constructorPetagrams = new ConstructorPetagrams(context);
        petegrams = constructorPetagrams.obtenerDatos();
        mostrarContactosRV();
    }


    @Override
    public void mostrarContactosRV() {
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(petegrams));
        iRecyclerViewFragmentView.generarGridLayoutVertical();
    }

    @Override
    public void obtenerMediosRecientes() {
        //Estableciendo conexion y ejecutando la llamada
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        IEndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);

        Call<PetegramResponse> contactoResponseCall = endpointsApi.getRecentMedia();
        contactoResponseCall.enqueue(new Callback<PetegramResponse>() {
            @Override
            public void onResponse(Call<PetegramResponse> call, Response<PetegramResponse> response) {
                PetegramResponse petegramResponse = response.body();
                petegrams = petegramResponse.getPetegrams();
                mostrarContactosRV();
            }

            @Override
            public void onFailure(Call<PetegramResponse> call, Throwable t) {
                Toast.makeText(context, "Algo paso en la conexion,intente de nuevo", Toast.LENGTH_LONG).show();
                Log.d("FALLO LA CONEXION", t.toString());
            }
        });
    }
}
