package server.datatransferobject;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateUser {
    private final long registro;
    private final String email;
    private final String nome;
    private final String senha;
    private final Boolean tipo;

    @Builder
    public UpdateUser(long registro, String email, String nome, String senha, Boolean tipo) {
        this.registro = registro;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.tipo = tipo;
    }
}
