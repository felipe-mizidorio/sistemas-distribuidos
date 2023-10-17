package protocol.response;

import server.datatransferobject.UserDTO;
import server.entity.UserEntity;

public class AdminCreateUserResponse implements Response<UserDTO> {
    private final UserDTO payload;

    public AdminCreateUserResponse(UserDTO payload) {
        this.payload = payload;
    }

    public static AdminCreateUserResponse of(UserEntity user) {
        return new AdminCreateUserResponse(UserDTO.of(user));
    }

    @Override
    public UserDTO payload() {
        return payload;
    }
}