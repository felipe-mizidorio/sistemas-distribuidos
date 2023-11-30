package protocol.request;

import protocol.request.header.Header;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class AdminFindUserRequest extends Request<AdminFindUserRequest.Payload> {
    @NotNull(message = "payload não pode ser nulo.")
    @Valid
    private final Payload payload;

    public AdminFindUserRequest(String token, Long registro) {
        super(new Header(RequestOperations.ADMIN_BUSCAR_USUARIO, token));
        payload = new Payload(registro);
    }

    @Override
    public String toString() {
        return "AdminFindUserRequest{" +
                "payload=" + payload +
                '}';
    }

    @Getter
    public static class Payload {
        @Positive
        @NotNull
        private final Long registro;

        public Payload(Long registro) {
            this.registro = registro;
        }

        @Override
        public String toString() {
            return "{ registro=" + registro +
                    '}';
        }
    }
}
