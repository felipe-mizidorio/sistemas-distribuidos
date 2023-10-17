package protocol.response;

import server.datatransferobject.UserDTO;

public class FindUserResponse implements Response<UserDTO> {
    private final UserDTO payload;

    public FindUserResponse(UserDTO payload) {
        this.payload = payload;
    }

    @Override
    public UserDTO payload() {
        return payload;
    }
}
