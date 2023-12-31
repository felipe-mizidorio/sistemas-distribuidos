package protocol.response;

import com.google.gson.annotations.SerializedName;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

public class ErrorResponse implements Response<ErrorResponse.Payload> {
    @NotNull
    @Valid
    private final Payload error;

    public ErrorResponse(int code, String message) {
        this.error = new Payload(code, message);
    }

    public ErrorResponse(Payload error) {
        this.error = error;
    }

    @Override
    public Payload payload() {
        return error;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "error=" + error +
                '}';
    }

    @Getter
    public static class Payload {
        @Positive
        private final int code;

        @NotBlank
        private final String mensagem;

        public Payload(int code, String message) {
            this.code = code;
            this.mensagem = message;
        }

        @Override
        public String toString() {
            return "{code=" + code +
                    ", mensagem='" + mensagem + '\'' +
                    '}';
        }
    }
}
