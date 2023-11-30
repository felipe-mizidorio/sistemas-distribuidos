package protocol.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

public class DeleteUserResponse implements Response<DeleteUserResponse.Payload> {
    private final Payload payload;

    public DeleteUserResponse(@Positive @NotNull Long registro) {
        this.payload = new Payload("Usuário deletado com sucesso: " + registro);
    }

    public Payload payload() {
        return payload;
    }

    @Override
    public String toString() {
        return "DeleteUserResponse{" +
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
