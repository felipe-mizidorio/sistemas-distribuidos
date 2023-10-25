package protocol.response;

import server.datatransferobject.UserDTO;
import server.entity.User;

public class AdminCreateUserResponse implements Response<UserDTO> {
    private final UserDTO payload;

    public AdminCreateUserResponse(UserDTO payload) {
        this.payload = payload;
    }

    @Override
    public UserDTO payload() {
        return payload;
    }

    @Override
    public String toString() {
        return "AdminCreateUserResponse{" +
                "payload=" + payload +
                '}';
    }
}