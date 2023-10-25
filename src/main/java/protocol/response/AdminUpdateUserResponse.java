package protocol.response;

import server.datatransferobject.UserDTO;
import server.entity.User;

public class AdminUpdateUserResponse implements Response<UserDTO> {
    private final UserDTO payload;

    public AdminUpdateUserResponse(UserDTO payload) {
        this.payload = payload;
    }

    public static AdminUpdateUserResponse of(User user) {
        return new AdminUpdateUserResponse(UserDTO.of(user));
    }

    @Override
    public UserDTO payload() {
        return payload;
    }

    @Override
    public String toString() {
        return "AdminUpdateUserResponse{" +
                "payload=" + payload +
                '}';
    }
}
