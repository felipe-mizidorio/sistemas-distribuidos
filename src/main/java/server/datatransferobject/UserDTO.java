package server.datatransferobject;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import server.entity.UserEntity;

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
    private final int id;

    public UserDTO(String nome, String email, Boolean isAdmin, int id) {
        this.nome = nome;
        this.email = email;
        this.isAdmin = isAdmin;
        this.id = id;
    }

    public static UserDTO of(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return new UserDTO(userEntity.getNome(), userEntity.getEmail(), userEntity.getIsAdmin(), userEntity.getId());
    }
}
