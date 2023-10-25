package protocol.response;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class LogoutResponse implements Response<LogoutResponse.Payload> {
    @NotNull
    @Valid
    private final Payload payload;

    public LogoutResponse() {
        this.payload = new Payload("Usuario desconectado.");
    }

    public LogoutResponse(Payload payload) {
        this.payload = payload;
    }

    @Override
    public Payload payload() {
        return payload;
    }

    @Override
    public String toString() {
        return "LogoutResponse{" +
                "payload=" + payload +
                '}';
    }

    @Getter
    public static class Payload {
        private final String mensagem;

        public Payload(String mensagem) {
            this.mensagem = mensagem;
        }

        @Override
        public String toString() {
            return "{ mensagem='" + mensagem + '\'' +
                    '}';
        }
    }
}
