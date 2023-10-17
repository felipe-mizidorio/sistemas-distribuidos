package protocol.response;

import protocol.commons.EmptyPayload;

public class AdminDeleteUserResponse implements Response<EmptyPayload> {
    private final EmptyPayload payload;

    public AdminDeleteUserResponse() {
        this.payload = new EmptyPayload();
    }

    public AdminDeleteUserResponse(EmptyPayload payload) {
        this.payload = payload;
    }

    @Override
    public EmptyPayload payload() {
        return payload;
    }
}
