package protocol.request;

import protocol.request.header.Header;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class AdminDeleteUserRequest extends Request<AdminDeleteUserRequest.Payload> {
    @NotNull(message = "payload não pode ser nulo.")
    @Valid
    private final Payload payload;

    public AdminDeleteUserRequest(String token, Long registro) {
        super(new Header(RequestOperations.ADMIN_DELETAR_USUARIO, token));
        payload = new Payload(registro);
    }

    @Override
    public String toString() {
        return "AdminDeleteUserRequest{" +
                "payload=" + payload +
                '}';
    }

    @Getter
    public static class Payload {
        @Positive
        private final long registro;

        public Payload(long registro) {
            this.registro = registro;
        }

        @Override
        public String toString() {
            return "{ registro=" + registro +
                    '}';
        }
    }
}
