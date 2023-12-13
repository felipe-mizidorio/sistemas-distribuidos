package protocol.request.routes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import protocol.request.Request;
import protocol.request.RequestOperations;
import protocol.request.header.Header;

@Getter
public class AdminCreateSegmentRequest extends Request<AdminCreateSegmentRequest.Payload> {

    @NotNull(message = "payload não pode ser nulo")
    @Valid
    private final AdminCreateSegmentRequest.Payload payload;

    public AdminCreateSegmentRequest(String token, Long pdi_inicial, Long pdi_final, String aviso, Boolean acessivel) {
        super(new Header(RequestOperations.CADASTRAR_SEGMENTO, token));
        payload = new Payload(pdi_inicial, pdi_final, aviso, acessivel);
    }

    @Getter
    public static class Payload {
        @NotNull(message = "campo pdi_inicial não pode estar nulo.")
        private final Long pdi_inicial;

        @NotNull(message = "campo pdi_final não pode estar nulo.")
        private final Long pdi_final;

        @NotNull(message = "campo aviso não pode estar nulo.")
        private final String aviso;

        @NotNull(message = "campo acessivel não pode estar nulo.")
        private final Boolean acessivel;

        public Payload(Long pdi_inicial, Long pdi_final, String aviso, Boolean acessivel) {
            this.pdi_inicial = pdi_inicial;
            this.pdi_final = pdi_final;
            this.aviso = aviso;
            this.acessivel = acessivel;
        }
    }
}
