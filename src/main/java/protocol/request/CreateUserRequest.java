package protocol.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import protocol.request.header.Header;

@Getter
public class CreateUserRequest extends Request<CreateUserRequest.Payload> {
    @NotNull(message = "payload não pode ser nulo")
    @Valid
    private final CreateUserRequest.Payload payload;

    public CreateUserRequest(final String token, final String nome, final String email, final String senha) {
        super(new Header(RequestOperations.CADASTRAR_USUARIO, token));
        payload = new CreateUserRequest.Payload(nome, email, senha, false);
    }

    @Override
    public String toString() {
        return "CreateUserRequest{" +
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
