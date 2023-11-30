package protocol.request.routes;

import lombok.Getter;
import protocol.request.Request;
import protocol.request.RequestOperations;
import protocol.request.header.Header;

@Getter
public class AdminUpdateNodeRequest extends Request<AdminUpdateNodeRequest.Payload>{

    private final AdminUpdateNodeRequest.Payload payload;

    public AdminUpdateNodeRequest(String token, Long id, String nome, int posicaoX, int posicaoY, String aviso, Boolean acessivel) {
        super(new Header(RequestOperations.ATUALIZAR_PDI, token));
        payload = new Payload(id, nome, new Posicao(posicaoX, posicaoY), aviso, acessivel);
    }

    public record Posicao(int x, int y) {
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
        private final Long id;
        private final String nome;
        private final Posicao posicao;
        private final String aviso;
        private final Boolean acessivel;

        public Payload(Long id, String nome, Posicao posicao, String aviso, Boolean acessivel) {
            this.id = id;
            this.nome = nome;
            this.posicao = posicao;
            this.aviso = aviso;
            this.acessivel = acessivel;
        }
    }
}
