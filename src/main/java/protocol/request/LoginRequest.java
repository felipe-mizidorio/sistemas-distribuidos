package protocol.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import protocol.request.header.Header;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequest extends Request<LoginRequest.Payload> {
    @NotNull(message = "payload não pode ser nulo.")
    @Valid
    private final Payload payload;

    public LoginRequest(final String email, final String senha) {
        super(new Header(RequestOperations.LOGIN, null));
        this.payload = new Payload(email, senha);
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
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
                    ", password='" + senha + '\'' +
                    '}';
        }
    }
}
