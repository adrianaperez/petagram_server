package com.aperez.aplicacionpetagram.restApi;

import com.aperez.aplicacionpetagram.model.UsuarioResponse;
import com.aperez.aplicacionpetagram.restApi.model.PetegramResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IEndpointsApi {

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<PetegramResponse> getRecentMedia();

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_ID_REGISTRAR_USUARIO)
    Call<UsuarioResponse> registrarUsuario(@Field("id_dispositivo") String id_dispositivo, @Field("id_usuario_instagram") String id_usuario_instagram,
                                           @Field("name_usuario_instagram") String name_usuario_instagram);

    @GET(ConstantesRestApi.KEY_TOQUE_ANIMAL)
    Call<UsuarioResponse> toqueAnimal(@Path("id") String id, @Path("animal") String animal);

}
