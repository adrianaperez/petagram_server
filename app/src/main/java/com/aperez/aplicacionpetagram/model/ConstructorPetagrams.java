package com.aperez.aplicacionpetagram.model;

import android.content.ContentValues;
import android.content.Context;


import com.aperez.aplicacionpetagram.db.BaseDatos;
import com.aperez.aplicacionpetagram.db.ConstantesBaseDatos;
import com.aperez.recyclerviewfragments.R;

import java.util.ArrayList;


/**
 * Created by anahisalgado on 21/04/16.
 */
public class ConstructorPetagrams {

    private static final int LIKE = 1;
    private Context context;
    public ConstructorPetagrams(Context context) {
        this.context = context;
    }

    public ArrayList<Petegram> obtenerDatos() {
        BaseDatos db = new BaseDatos(context);
        insertarTresContactos(db);
        return  db.obtenerTodosLosContactos();
    }



    public void insertarTresContactos(BaseDatos db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_PETEGRAMS_NOMBRE, "Adriana Pérez");
        contentValues.put(ConstantesBaseDatos.TABLE_PETEGRAMS_TELEFONO, "+56932845561");
        contentValues.put(ConstantesBaseDatos.TABLE_PETEGRAMS_EMAIL, "adri.perez.08@gmail.com");
        contentValues.put(ConstantesBaseDatos.TABLE_PETEGRAMS_FOTO, R.drawable.candy_icon);

        db.insertarContacto(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_PETEGRAMS_NOMBRE, "Pedro Sanchez");
        contentValues.put(ConstantesBaseDatos.TABLE_PETEGRAMS_TELEFONO, "88882222");
        contentValues.put(ConstantesBaseDatos.TABLE_PETEGRAMS_EMAIL, "pedro@gmail.com");
        contentValues.put(ConstantesBaseDatos.TABLE_PETEGRAMS_FOTO, R.drawable.yammi_banana_icon);

        db.insertarContacto(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_PETEGRAMS_NOMBRE, "Mireya Lopez");
        contentValues.put(ConstantesBaseDatos.TABLE_PETEGRAMS_TELEFONO, "33331111");
        contentValues.put(ConstantesBaseDatos.TABLE_PETEGRAMS_EMAIL, "mireya@gmail.com");
        contentValues.put(ConstantesBaseDatos.TABLE_PETEGRAMS_FOTO, R.drawable.shock_rave_bonus_icon);

        db.insertarContacto(contentValues);
    }

    public void darLikeCotnacto(Petegram petegram){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_PETEGRAM_ID_CONTACTO, petegram.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_PETEGRAM_NUMERO_LIKES, LIKE);
        db.insertarLikeContacto(contentValues);
    }

    public int obtenerLikesContacto(Petegram petegram){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesContacto(petegram);
    }


}
