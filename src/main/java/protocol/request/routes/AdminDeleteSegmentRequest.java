package protocol.request.routes;

import lombok.Getter;
import protocol.request.Request;
import protocol.request.RequestOperations;
import protocol.request.header.Header;

@Getter
public class AdminDeleteSegmentRequest extends Request<AdminDeleteSegmentRequest.Payload> {

    private final AdminDeleteSegmentRequest.Payload payload;

    public AdminDeleteSegmentRequest(String token, Long pdiInicial, Long pdiFinal) {
        super(new Header(RequestOperations.DELETAR_SEGMENTO, token));
        payload = new Payload(pdiInicial, pdiFinal);
    }

    @Getter
    public static class Payload {
        private final Long pdiInicial;

        private final Long pdiFinal;

        public Payload(Long pdiInicial, Long pdiFinal) {
            this.pdiInicial = pdiInicial;
            this.pdiFinal = pdiFinal;
        }
    }
}
