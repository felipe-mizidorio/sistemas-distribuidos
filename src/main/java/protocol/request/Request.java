package protocol.request;

import protocol.request.header.Header;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Request<T> {
    @NotNull(message = "header não pode estar nulo.")
    @Valid
    final Header header;

    public T getPayload() {
        return null;
    }
}
