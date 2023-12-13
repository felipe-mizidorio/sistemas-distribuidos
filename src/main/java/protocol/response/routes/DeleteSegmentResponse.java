package protocol.response.routes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import protocol.response.Response;

public class DeleteSegmentResponse implements Response<DeleteSegmentResponse.Payload>{
    @Valid
    @NotNull
    private final DeleteSegmentResponse.Payload payload;

    public DeleteSegmentResponse(Long pdiInicial, Long pdiFinal) {
        this.payload = new DeleteSegmentResponse.Payload("Segmento entre os pontos " + pdiInicial + " e " + pdiFinal + " foi deletado.");
    }

    public DeleteSegmentResponse.Payload payload() {
        return payload;
    }

    @Override
    public String toString() {
        return "DeleteSegmentResponse{" +
                "payload=" + payload +
                '}';
    }

    @Getter
    public static class Payload {
        @NotBlank
        private final String mensagem;

        public Payload(String mensagem) {
            this.mensagem = mensagem;
        }

        @Override
        public String toString() {
            return "{mensagem='" + mensagem + '\'' +
                    '}';
        }
    }
}
