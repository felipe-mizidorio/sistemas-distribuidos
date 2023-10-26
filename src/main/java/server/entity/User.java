package server.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import server.datatransferobject.CreateUser;
import server.datatransferobject.UpdateUser;

@Entity
@Table(name = "Users")
@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {
    @NotNull
    @Id
    @GeneratedValue
    private Long registro;

    @NotNull
    @Size(min = 3, max = 255)
    private String nome;

    @NotNull
    @Email
    @NaturalId(mutable = true)
    private String email;

    @NotNull
    private String senha;

    @NotNull
    private Boolean tipo;

    public User() {
    }

    public static User of(CreateUser user) {
        var entity = new User();
        entity.setNome(user.getNome());
        entity.setEmail(user.getEmail());
        entity.setSenha(user.getSenha());
        entity.setTipo(user.getTipo());
        return entity;
    }

    public static User of(UpdateUser user) {
        var entity = new User();
        entity.setNome(user.getNome());
        entity.setEmail(user.getEmail());
        entity.setSenha(user.getSenha());
        entity.setTipo(user.getTipo());
        return entity;
    }

    public void update(User info) {
        if (info.getEmail() != null) {
            setEmail(info.getEmail());
        }
        if (info.getSenha() != null) {
            setSenha(info.getSenha());
        }
        if (info.getTipo() != null) {
            setTipo(info.getTipo());
        }
        if (info.getNome() != null) {
            setNome(info.getNome());
        }
    }
}
