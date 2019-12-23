package com.aperez.aplicacionpetagram.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.aperez.aplicacionpetagram.model.Petegram;


import java.util.ArrayList;

/**
 * Created by anahisalgado on 04/05/16.
 */
public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaPetegram = "CREATE TABLE " + ConstantesBaseDatos.TABLE_PETEGRAMS + "(" +
                ConstantesBaseDatos.TABLE_PETEGRAMS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_PETEGRAMS_NOMBRE + " TEXT, " +
                ConstantesBaseDatos.TABLE_PETEGRAMS_TELEFONO + " TEXT, " +
                ConstantesBaseDatos.TABLE_PETEGRAMS_EMAIL + " TEXT, " +
                ConstantesBaseDatos.TABLE_PETEGRAMS_FOTO + " INTEGER" +
                ")";
        String queryCrearTablaLikesContacto = "CREATE TABLE " + ConstantesBaseDatos.TABLE_LIKES_PETEGRAM + "(" +
                ConstantesBaseDatos.TABLE_LIKES_PETEGRAM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_LIKES_PETEGRAM_ID_CONTACTO + " INTEGER, " +
                ConstantesBaseDatos.TABLE_LIKES_PETEGRAM_NUMERO_LIKES + " INTEGER, " +
                "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_LIKES_PETEGRAM_ID_CONTACTO + ") " +
                "REFERENCES " + ConstantesBaseDatos.TABLE_PETEGRAMS + "(" + ConstantesBaseDatos.TABLE_PETEGRAMS_ID + ")" +
                ")";

        db.execSQL(queryCrearTablaPetegram);
        db.execSQL(queryCrearTablaLikesContacto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_PETEGRAMS);
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_LIKES_PETEGRAM);
        onCreate(db);
    }

    public ArrayList<Petegram> obtenerTodosLosContactos() {
        ArrayList<Petegram> contactos = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_PETEGRAMS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        /*while (registros.moveToNext()){
            Petegram contactoActual = new Petegram();
            contactoActual.setId(registros.getInt(0));
            contactoActual.setNombre(registros.getString(1));
            contactoActual.setTelefono(registros.getString(2));
            contactoActual.setEmail(registros.getString(3));
            contactoActual.setFoto(registros.getInt(4));

            String queryLikes = "SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKES_CONTACT_NUMERO_LIKES+") as likes " +
                                " FROM " + ConstantesBaseDatos.TABLE_LIKES_CONTACT +
                                " WHERE " + ConstantesBaseDatos.TABLE_LIKES_CONTACT_ID_CONTACTO + "=" + contactoActual.getId();

            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if (registrosLikes.moveToNext()){
                contactoActual.setLikes(registrosLikes.getInt(0));
            }else {
                contactoActual.setLikes(0);
            }

            contactos.add(contactoActual);

        }*/

        db.close();

        return contactos;
    }

    public void insertarContacto(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_PETEGRAMS,null, contentValues);
        db.close();
    }

    public void insertarLikeContacto(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_LIKES_PETEGRAM, null, contentValues);
        db.close();
    }


    public int obtenerLikesContacto(Petegram petegram){
        int likes = 0;

        String query = "SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKES_PETEGRAM_NUMERO_LIKES+")" +
                        " FROM " + ConstantesBaseDatos.TABLE_LIKES_PETEGRAM +
                        " WHERE " + ConstantesBaseDatos.TABLE_LIKES_PETEGRAM_ID_CONTACTO + "="+petegram.getId();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()){
            likes = registros.getInt(0);
        }

        db.close();

        return likes;
    }
}