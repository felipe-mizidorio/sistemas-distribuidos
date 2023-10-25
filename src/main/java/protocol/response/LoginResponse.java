package protocol.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class LoginResponse implements Response<LoginResponse.Payload> {
    private final Payload payload;

    public LoginResponse(final String token) {
        this.payload = new Payload(token);
    }

    public LoginResponse(Payload payload) {
        this.payload = payload;
    }

    @Override
    public Payload payload() {
        return payload;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "payload=" + payload +
                '}';
    }

    @Getter
    public static class Payload {
        @NotBlank
        private final String token;

        public Payload(String token) {
            this.token = token;
        }

        @Override
        public String toString() {
            return "{ token='" + token + '\'' +
                    '}';
        }
    }
}

