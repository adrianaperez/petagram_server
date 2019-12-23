package com.aperez.aplicacionpetagram.db;

/**
 * Created by anahisalgado on 04/05/16.
 */
public final class ConstantesBaseDatos {

    public static final String DATABASE_NAME = "petegram";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_PETEGRAMS           = "petegram";
    public static final String TABLE_PETEGRAMS_ID        = "id";
    public static final String TABLE_PETEGRAMS_NOMBRE    = "nombre";
    public static final String TABLE_PETEGRAMS_TELEFONO  = "telefono";
    public static final String TABLE_PETEGRAMS_EMAIL     = "email";
    public static final String TABLE_PETEGRAMS_FOTO      = "foto";

    public static final String TABLE_LIKES_PETEGRAM = "petegram_likes";
    public static final String TABLE_LIKES_PETEGRAM_ID = "id";
    public static final String TABLE_LIKES_PETEGRAM_ID_CONTACTO = "id_petegram";
    public static final String TABLE_LIKES_PETEGRAM_NUMERO_LIKES = "numero_likes";
}
