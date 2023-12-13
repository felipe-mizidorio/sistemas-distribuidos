package protocol.request.routes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import protocol.request.Request;
import protocol.request.RequestOperations;
import protocol.request.header.Header;

@Getter
public class AdminUpdateNodeRequest extends Request<AdminUpdateNodeRequest.Payload>{

    @NotNull(message = "payload não pode ser nulo")
    @Valid
    private final AdminUpdateNodeRequest.Payload payload;

    public AdminUpdateNodeRequest(String token, Long id, String nome, String aviso, Boolean acessivel) {
        super(new Header(RequestOperations.ATUALIZAR_PDI, token));
        payload = new Payload(id, nome, aviso, acessivel);
    }

    @Getter
    public static class Payload {
        @NotNull(message = "campo id não pode estar nulo.")
        private final Long id;

        private final String nome;

        private final String aviso;

        private final Boolean acessivel;

        public Payload(Long id, String nome, String aviso, Boolean acessivel) {
            this.id = id;
            this.nome = nome;
            this.aviso = aviso;
            this.acessivel = acessivel;
        }
    }
}
