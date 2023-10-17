package protocol.response;

import server.datatransferobject.UserDTO;
import server.entity.UserEntity;

public class AdminUpdateUserResponse implements Response<UserDTO> {
    private final UserDTO payload;

    public AdminUpdateUserResponse(UserDTO payload) {
        this.payload = payload;
    }

    public static AdminUpdateUserResponse of(UserEntity user) {
        return new AdminUpdateUserResponse(UserDTO.of(user));
    }

    @Override
    public UserDTO payload() {
        return payload;
    }
}
