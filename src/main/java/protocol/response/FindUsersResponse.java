package protocol.response;

import lombok.Getter;
import server.datatransferobject.UserDTO;

import java.util.List;

public class FindUsersResponse implements Response<FindUsersResponse.Payload> {
    private final Payload payload;

    public FindUsersResponse(List<UserDTO> users) {
        this.payload = new Payload(users);
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
        private final List<UserDTO> users;

        public Payload(List<UserDTO> users) {
            this.users = users;
        }

        @Override
        public String toString() {
            return "{ users=" + users +
                    '}';
        }
    }
}
