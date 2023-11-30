package protocol.request.admin;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import protocol.Optional;
import protocol.request.Request;
import protocol.request.RequestOperations;
import protocol.request.header.Header;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class AdminUpdateUserRequest extends Request<AdminUpdateUserRequest.Payload> {
    @NotNull(message = "payload não pode ser nulo.")
    @Valid
    private final Payload payload;

    public AdminUpdateUserRequest(String token, @NotNull Long registro, @Optional String nome,
                                  @Optional String email, @Optional String senha,
                                  @Optional Boolean tipo) {
        super(new Header(RequestOperations.ADMIN_ATUALIZAR_USUARIO, token));
        payload = new Payload(registro, email, nome, senha, tipo);
    }

    @Override
    public String toString() {
        return "AdminUpdateUserRequest{" +
                "payload=" + payload +
                '}';
    }

    @Getter
    public static class Payload {
        @Positive
        private final Long registro;

        @Email
        private final String email;

        @Size(min = 3, max = 255)
        private final String nome;

        private final String senha;

        private final Boolean tipo;

        public Payload(Long registro, String email, String nome, String senha, Boolean tipo) {
            this.registro = registro;
            this.email = email;
            this.nome = nome;
            this.senha = senha;
            this.tipo = tipo;
        }

        @Override
        public String toString() {
            return "{ registro=" + registro +
                    ", email='" + email + '\'' +
                    ", nome='" + nome + '\'' +
                    ", senha='" + senha + '\'' +
                    ", tipo=" + tipo +
                    '}';
        }
    }
}
