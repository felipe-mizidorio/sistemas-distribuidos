package protocol.request.header;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

public record Header(
        @NotBlank(message = "operation não pode estar vazio") String operation,

        @Pattern(regexp = "(?:\\w|-){2,}(?:\\.(?:\\w|-){2,}){2}", message = "JWT com formato inválido")
        String token) {
}

