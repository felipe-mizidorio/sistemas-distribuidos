package server.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import server.datatransferobject.CreateUser;
import server.datatransferobject.UpdateUser;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserEntity {
    @NotNull
    private Integer id;
    @NotBlank
    @Size(min = 3, max = 255)
    private String nome;
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String senha;
    @NotNull
    private Boolean isAdmin;

    public UserEntity() {
    }

    public static UserEntity of(CreateUser user) {
        var entity = new UserEntity();
        entity.setNome(user.getNome());
        entity.setEmail(user.getEmail());
        entity.setSenha(user.getSenha());
        entity.setIsAdmin(user.getTipo());
        return entity;
    }

    public static UserEntity of(UpdateUser user) {
        var entity = new UserEntity();
        entity.setNome(user.getNome());
        entity.setEmail(user.getEmail());
        entity.setSenha(user.getSenha());
        entity.setIsAdmin(user.getTipo());
        return entity;
    }

    public void update(UserEntity info) {
        if (info.getEmail() != null) {
            setEmail(info.getEmail());
        }
        if (info.getSenha() != null) {
            setSenha(info.getSenha());
        }
        if (info.getIsAdmin() != null) {
            setIsAdmin(info.getIsAdmin());
        }
        if (info.getNome() != null) {
            setNome(info.getNome());
        }
    }
}
