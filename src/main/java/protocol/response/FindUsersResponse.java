package protocol.response;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import server.datatransferobject.UserDTO;

import java.util.List;

public class FindUsersResponse implements Response<FindUsersResponse.Payload> {
    @Valid
    @NotNull
    private final Payload payload;

    public FindUsersResponse(List<UserDTO> usuarios) {
        this.payload = new Payload(usuarios);
    }

    public FindUsersResponse(Payload payload) {
        this.payload = payload;
    }

    @Override
    public Payload payload() {
        return payload;
    }

    @Override
    public String toString() {
        return "FindUsersResponse{" +
                "payload=" + payload +
                '}';
    }

    @Getter
    public static class Payload {
        private final List<UserDTO> usuarios;

        public Payload(List<UserDTO> usuarios) {
            this.usuarios = usuarios;
        }

        @Override
        public String toString() {
            return "{ usuarios=" + usuarios +
                    '}';
        }
    }
}
