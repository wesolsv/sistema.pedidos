package com.weszdev.sistema.pedidos.util;

public enum PerfilUsuario {

    ADMIN("admin"),
    PADRAO("padrao");

    private String perfil;

    PerfilUsuario(String perfil){
        this.perfil = perfil;
    }

    public String getPerfil(){
        return perfil;
    }
}
