package protocol.response;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import server.datatransferobject.UserDTO;
import server.entity.User;

public class CreateUserResponse implements Response<UserDTO> {
    @Valid
    @NotNull
    private final UserDTO payload;

    public CreateUserResponse(UserDTO payload) {
        this.payload = payload;
    }

    @Override
    public UserDTO payload() {
        return payload;
    }

    @Override
    public String toString() {
        return "CreateUserResponse{" +
                "payload=" + payload +
                '}';
    }
}
