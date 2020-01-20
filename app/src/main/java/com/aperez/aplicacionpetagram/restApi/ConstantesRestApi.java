package com.aperez.aplicacionpetagram.restApi;

import android.graphics.drawable.Drawable;

import com.aperez.recyclerviewfragments.R;

public final class ConstantesRestApi {

    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;

    //Shasha
    public static final String ACCESS_TOKEN = "25098115729.1677ed0.84fc471c8e854b76a4ab4141743ae93e";
    public static final String ACCOUNT = "sasha.dogdog";
    public static final String RECEIVER_ACCOUNT_NAME = "Perrito Mike";
    public static final String TRANSMITTER_ACCOUNT_NAME = "Shasha Lobita";
    public static final int IMAGE = R.drawable.perritomike;
    public static final String RECEIVER_IDENTIFIER = "-Ly6wfNkcUoTM8V8lsr4";

    //Mike

    /*public static final String ACCESS_TOKEN = "3259702353.aa0d0f4.e61865afc9144ecc8ffc8f6dc84aa17b";
    public static final String ACCOUNT = "perritomike";
    public static final String RECEIVER_ACCOUNT_NAME = "Shasha Lobita";
    public static final String TRANSMITTER_ACCOUNT_NAME = "Perrito Mike";
    public static final int IMAGE = R.drawable.shashadog;
    public static final String RECEIVER_IDENTIFIER = "-Ly6wdjI5DMPsht2sF-c";*/

    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String KEY_GET_RECENT_MEDIA_USER = "users/self/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    public static final String ROOT_URL_HEROKU = "https://calm-fortress-96556.herokuapp.com/";
    public static final String KEY_POST_ID_REGISTRAR_USUARIO = "registrar-usuario";
    public static final String KEY_TOQUE_ANIMAL = "toque-animal/{id}/{animal}/";
}
