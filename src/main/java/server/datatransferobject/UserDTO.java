package server.datatransferobject;

import jakarta.validation.constraints.*;
import lombok.Getter;
import server.entity.User;

@Getter
public class UserDTO {
    @NotBlank
    @Size(min = 3, max = 255)
    private final String nome;

    @NotBlank
    @Email
    private final String email;

    @NotNull
    private final Boolean tipo;

    @Positive
    private final long registro;

    public UserDTO(String nome, String email, Boolean tipo, long registro) {
        this.nome = nome;
        this.email = email;
        this.tipo = tipo;
        this.registro = registro;
    }

    public static UserDTO of(User user) {
        if (user == null) {
            return null;
        }
        return new UserDTO(user.getNome(), user.getEmail(), user.getTipo(), user.getRegistro());
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", isAdmin=" + tipo +
                ", id=" + registro +
                '}';
    }
}
