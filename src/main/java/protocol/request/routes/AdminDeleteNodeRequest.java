package protocol.request.routes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import protocol.request.Request;
import protocol.request.RequestOperations;
import protocol.request.header.Header;

@Getter
public class AdminDeleteNodeRequest extends Request<AdminDeleteNodeRequest.Payload> {

    @NotNull(message = "payload não pode ser nulo")
    @Valid
    private final AdminDeleteNodeRequest.Payload payload;

    public AdminDeleteNodeRequest(String token, Long id) {
        super(new Header(RequestOperations.DELETAR_PDI, token));
        payload = new Payload(id);
    }

    @Getter
    public static class Payload {
        @NotNull
        @NotBlank(message = "campo id não pode estar vazio.")
        private Long id;

        public Payload(Long id) {
            this.id = id;
        }
    }
}
