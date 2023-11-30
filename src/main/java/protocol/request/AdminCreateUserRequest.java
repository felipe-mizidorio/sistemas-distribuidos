package protocol.request.admin;

import jakarta.validation.constraints.*;
import protocol.request.Request;
import protocol.request.RequestOperations;
import protocol.request.header.Header;
import jakarta.validation.Valid;
import lombok.Getter;

@Getter
public class AdminCreateUserRequest extends Request<AdminCreateUserRequest.Payload> {
    @NotNull(message = "payload não pode ser nulo")
    @Valid
    private final Payload payload;

    public AdminCreateUserRequest(final String token, final String nome, final String email,
                                  final String senha) {
        super(new Header(RequestOperations.ADMIN_CADASTRAR_USUARIO, token));
        payload = new Payload(nome, email, senha, true);
    }

    @Override
    public String toString() {
        return "AdminCreateUserRequest{" +
                "payload=" + payload +
                '}';
    }

    @Getter
    public static class Payload {
        @NotBlank(message = "campo nome não pode estar vazio.")
        @Size(min = 3, max = 255, message = "campo nome deve conter entre 3 e 255 caracteres.")
        private final String nome;

        @NotBlank(message = "campo email não pode estar vazio.")
        @Email
        private final String email;

        @NotBlank(message = "campo senha não pode estar vazio.")
        private final String senha;

        @NotNull(message = "campo tipo não pode ser nulo.")
        private final Boolean tipo;

        public Payload(String nome, String email, String senha, Boolean tipo) {
            this.nome = nome;
            this.email = email;
            this.senha = senha;
            this.tipo = tipo;
        }

        @Override
        public String toString() {
            return "{ nome='" + nome + '\'' +
                    ", email='" + email + '\'' +
                    ", senha='" + senha + '\'' +
                    ", tipo=" + tipo +
                    '}';
        }
    }
}
