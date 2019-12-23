package com.aperez.aplicacionpetagram.adapter;

import com.aperez.aplicacionpetagram.restApi.ConstantesRestApi;
import com.aperez.aplicacionpetagram.restApi.IEndpointsApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiAdapter {
    public IEndpointsApi establecerConexionRestApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL_HEROKU)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(IEndpointsApi.class);
    }
}
