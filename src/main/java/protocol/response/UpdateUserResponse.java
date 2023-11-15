package protocol.response;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import server.datatransferobject.UserDTO;
import server.entity.User;

public class UpdateUserResponse implements Response<UserDTO> {
    @Valid
    @NotNull
    private final UserDTO payload;

    public UpdateUserResponse(UserDTO payload) {
        this.payload = payload;
    }

    public static UpdateUserResponse of(User user) {
        return new UpdateUserResponse(UserDTO.of(user));
    }

    @Override
    public UserDTO payload() {
        return payload;
    }

    @Override
    public String toString() {
        return "UpdateUserResponse{" +
                "payload=" + payload +
                '}';
    }
}
