package protocol.response.routes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import protocol.response.Response;

public class DeleteNodeResponse implements Response<DeleteNodeResponse.Payload> {
    @Valid
    @NotNull
    private final DeleteNodeResponse.Payload payload;

    public DeleteNodeResponse(@Positive @NotNull Long id) {
        this.payload = new DeleteNodeResponse.Payload("Ponto com id " + id + " foi deletado.");
    }

    public DeleteNodeResponse.Payload payload() {
        return payload;
    }

    @Override
    public String toString() {
        return "DeleteNodeResponse{" +
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
