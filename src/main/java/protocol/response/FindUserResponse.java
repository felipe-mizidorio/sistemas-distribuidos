package protocol.response;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import server.datatransferobject.user.UserDTO;

public class FindUserResponse implements Response<UserDTO> {
    @Valid
    @NotNull
    private final UserDTO payload;

    public FindUserResponse(UserDTO payload) {
        this.payload = payload;
    }

    @Override
    public UserDTO payload() {
        return payload;
    }

    @Override
    public String toString() {
        return "FindUserResponse{" +
                "payload=" + payload +
                '}';
    }
}
