package com.aperez.aplicacionpetagram.restApi.deserializador;

import com.aperez.aplicacionpetagram.model.Petegram;
import com.aperez.aplicacionpetagram.restApi.JsonKeys;
import com.aperez.aplicacionpetagram.restApi.model.PetegramResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PetegramDeserializador implements JsonDeserializer<PetegramResponse> {
    @Override
    public PetegramResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        PetegramResponse petegramResponse = gson.fromJson(json, PetegramResponse.class);

        JsonArray contactoResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        petegramResponse.setPetegrams(deserializarContactoDeJson(contactoResponseData));
        return petegramResponse;
    }

    private ArrayList<Petegram> deserializarContactoDeJson(JsonArray contactoResponseData){
        ArrayList<Petegram> petegrams = new ArrayList<>();

        for (int i = 0; i < contactoResponseData.size(); i++) {
            JsonObject contactoResponseDataObject = contactoResponseData.get(i).getAsJsonObject();

            JsonObject userJson = contactoResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id = userJson.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto = userJson.get(JsonKeys.USER_FULLNAME).getAsString();

            JsonObject imageJson = contactoResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject stdResolutionJson = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String urlFoto = stdResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJson = contactoResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            Petegram petegramActual = new Petegram();

            petegramActual.setId(id);
            petegramActual.setNombreCompleto(nombreCompleto);
            petegramActual.setUrlFoto(urlFoto);
            petegramActual.setLikes(likes);

            petegrams.add(petegramActual);
        }

        return petegrams;
    }
}
