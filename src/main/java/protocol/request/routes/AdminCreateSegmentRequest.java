package protocol.request.routes;

import lombok.Getter;
import protocol.request.Request;
import protocol.request.RequestOperations;
import protocol.request.header.Header;

@Getter
public class AdminCreateSegmentRequest extends Request<AdminCreateSegmentRequest.Payload> {

    private final AdminCreateSegmentRequest.Payload payload;

    public AdminCreateSegmentRequest(String token, Long pdiInicial, Long pdiFinal, String aviso, Boolean acessivel) {
        super(new Header(RequestOperations.CADASTRAR_SEGMENTO, token));
        payload = new Payload(pdiInicial, pdiFinal, aviso, acessivel);
    }

    @Getter
    public static class Payload {
        private final Long pdiInicial;

        private final Long pdiFinal;

        private final String aviso;

        private final Boolean acessivel;

        public Payload(Long pdiInicial, Long pdiFinal, String aviso, Boolean acessivel) {
            this.pdiInicial = pdiInicial;
            this.pdiFinal = pdiFinal;
            this.aviso = aviso;
            this.acessivel = acessivel;
        }
    }
}