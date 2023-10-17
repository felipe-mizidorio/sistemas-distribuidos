package protocol.request;

import lombok.Builder;
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

    @Getter
    public static class Payload {
        @Positive
        private final long registro;

        public Payload(long registro) {
            this.registro = registro;
        }
    }
}
