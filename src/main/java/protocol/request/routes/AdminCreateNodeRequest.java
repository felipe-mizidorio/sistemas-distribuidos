package protocol.request.routes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import protocol.request.Request;
import protocol.request.RequestOperations;
import protocol.request.header.Header;

@Getter
public class AdminCreateNodeRequest extends Request<AdminCreateNodeRequest.Payload> {

    @NotNull(message = "payload não pode ser nulo")
    @Valid
    private final AdminCreateNodeRequest.Payload payload;

    public AdminCreateNodeRequest(String token, String nome, int x, int y, String aviso, boolean acessivel) {
        super(new Header(RequestOperations.CADASTRAR_PDI, token));
        payload = new Payload(nome, new Posicao(x, y), aviso, acessivel);
    }

    @Getter
    public static class Posicao {
        private final int x;
        private final int y;

        public Posicao(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Posicao{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    @Getter
    public static class Payload {
        @NotBlank(message = "campo nome não pode estar vazio.")
        @Size(min = 3, max = 255, message = "campo nome deve conter entre 3 e 255 caracteres.")
        private final String nome;

        @NotNull(message = "campo posicao não pode estar vazio.")
        private final Posicao posicao;

        @NotBlank(message = "campo aviso não pode estar vazio.")
        private final String aviso;

        @NotNull(message = "campo acessivel não pode estar vazio.")
        private final Boolean acessivel;

        public Payload(String nome, Posicao posicao, String aviso, Boolean acessivel) {
            this.nome = nome;
            this.posicao = posicao;
            this.aviso = aviso;
            this.acessivel = acessivel;
        }
    }
}
