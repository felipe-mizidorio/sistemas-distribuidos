package protocol.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import protocol.request.header.Header;

@Getter
public class DeleteUserRequest extends Request<DeleteUserRequest.Payload> {
    @NotNull(message = "payload não pode ser nulo.")
    @Valid
    private final DeleteUserRequest.Payload payload;

    public DeleteUserRequest(String token, String email, String senha) {
        super(new Header(RequestOperations.DELETAR_USUARIO, token));
        payload = new Payload(email, senha);
    }

    @Override
    public String toString() {
        return "DeleteUserRequest{" +
                "payload=" + payload +
                '}';
    }

    @Getter
    public static class Payload {
        @NotBlank(message = "campo email não pode estar vazio.")
        @Email
        private final String email;

        @NotBlank(message = "campo senha não pode estar vazio.")
        private final String senha;

        public Payload(String email, String senha) {
            this.email = email;
            this.senha = senha;
        }

        @Override
        public String toString() {
            return "{ email='" + email + '\'' +
                    ", senha='" + senha + '\'' +
                    '}';
        }
    }
}
