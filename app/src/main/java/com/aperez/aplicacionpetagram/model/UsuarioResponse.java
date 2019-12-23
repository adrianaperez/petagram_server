package com.aperez.aplicacionpetagram.model;

public class UsuarioResponse {
    private String id_dispositivo;
    private String id_usuario_instagram;

    public UsuarioResponse() {
    }

    public UsuarioResponse(String id_dispositivo, String id_usuario_instagram) {
        this.id_dispositivo = id_dispositivo;
        this.id_usuario_instagram = id_usuario_instagram;
    }

    public String getIdDispositivo() {
        return id_dispositivo;
    }

    public void setIdDispositivo(String id_dispositivo) {
        this.id_dispositivo = id_dispositivo;
    }

    public String getIdUsuarioInstagram() {
        return id_usuario_instagram;
    }

    public void setIdUsuarioInstagram(String id_usuario_instagram) {
        this.id_usuario_instagram = id_usuario_instagram;
    }
}
