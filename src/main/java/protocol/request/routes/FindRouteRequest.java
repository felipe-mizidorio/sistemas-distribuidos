package protocol.request.routes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import protocol.request.Request;
import protocol.request.RequestOperations;
import protocol.request.header.Header;

@Getter
public class FindRouteRequest extends Request<FindRouteRequest.Payload> {

    @NotNull(message = "payload não pode ser nulo")
    @Valid
    private final FindRouteRequest.Payload payload;

    public FindRouteRequest(String token, Long pdi_inicial, Long pdi_final) {
        super(new Header(RequestOperations.BUSCAR_ROTA, token));
        payload = new Payload(pdi_inicial, pdi_final);
    }

    @Getter
    public static class Payload {
        @NotNull(message = "campo pdi_inicial não pode estar nulo.")
        private final Long pdi_inicial;
        @NotNull(message = "campo pdi_final não pode estar nulo.")
        private final Long pdi_final;

        public Payload(Long pdi_inicial, Long pdi_final) {
            this.pdi_inicial = pdi_inicial;
            this.pdi_final = pdi_final;
        }
    }
}
