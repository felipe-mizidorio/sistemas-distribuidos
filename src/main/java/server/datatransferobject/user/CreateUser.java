package server.datatransferobject.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateUser {
    private final String email;
    private final String senha;
    private final String nome;
    private final Boolean tipo;

    @Builder
    public CreateUser(String email, String senha, String nome, Boolean tipo) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.tipo = tipo;
    }
}
