package protocol.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jwt.Jwt;
import lombok.Getter;
import protocol.Optional;
import protocol.request.header.Header;

@Getter
public class UpdateUserRequest extends Request<UpdateUserRequest.Payload> {
    @NotNull(message = "payload não pode ser nulo")
    @Valid
    private final UpdateUserRequest.Payload payload;

    public UpdateUserRequest(String token, @Optional String email,
                                  @Optional String nome, @Optional String senha) {
        super(new Header(RequestOperations.ATUALIZAR_USUARIO, token));
        payload = new UpdateUserRequest.Payload(email, nome, senha);
    }

    @Override
    public String toString() {
        return "UpdateUserRequest{" +
                "payload=" + payload +
                '}';
    }

    @Getter
    public static class Payload {
        @Email
        private final String email;

        @Size(min = 3, max = 255)
        private final String nome;

        private final String senha;

        public Payload(String email, String nome, String senha) {
            this.email = email;
            this.nome = nome;
            this.senha = senha;
        }

        @Override
        public String toString() {
            return "{ email='" + email + '\'' +
                    ", nome='" + nome + '\'' +
                    ", senha='" + senha +
                    '}';
        }
    }
}
