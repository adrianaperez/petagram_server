package com.aperez.aplicacionpetagram.restApi.adapter;

import com.aperez.aplicacionpetagram.restApi.ConstantesRestApi;
import com.aperez.aplicacionpetagram.restApi.IEndpointsApi;
import com.aperez.aplicacionpetagram.restApi.deserializador.PetegramDeserializador;
import com.aperez.aplicacionpetagram.restApi.model.PetegramResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiAdapter {

    public IEndpointsApi establecerConexionRestApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(IEndpointsApi.class);
    }

    public Gson construyeGsonDeserializadorMediaRecent(){

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PetegramResponse.class, new PetegramDeserializador());
        return  gsonBuilder.create();
    }
}
