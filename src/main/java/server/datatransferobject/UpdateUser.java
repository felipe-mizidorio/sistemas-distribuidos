package server.datatransferobject;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateUser {
    private final long sender;
    private final long registro;
    private final String nome;
    private final String email;
    private final String senha;
    private final Boolean tipo;

    @Builder
    public UpdateUser(long sender, long registro, String nome, String email, String senha, Boolean tipo) {
        this.sender = sender;
        this.registro = registro;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }
}
