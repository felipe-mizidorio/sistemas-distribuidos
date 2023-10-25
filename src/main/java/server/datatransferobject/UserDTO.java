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
    private final Boolean isAdmin;

    @Positive
    private final long id;

    public UserDTO(String nome, String email, Boolean isAdmin, long id) {
        this.nome = nome;
        this.email = email;
        this.isAdmin = isAdmin;
        this.id = id;
    }

    public static UserDTO of(User user) {
        if (user == null) {
            return null;
        }
        return new UserDTO(user.getNome(), user.getEmail(), user.getIsAdmin(), user.getId());
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", isAdmin=" + isAdmin +
                ", id=" + id +
                '}';
    }
}
