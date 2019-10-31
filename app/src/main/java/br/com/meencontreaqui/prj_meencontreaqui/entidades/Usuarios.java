package br.com.meencontreaqui.prj_meencontreaqui.entidades;

public class Usuarios {
    private long id;
    private String username;
    private String senha;

    public Usuarios(long id, String username, String senha) {
        this.id = id;
        this.username = username;
        this.senha = senha;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}