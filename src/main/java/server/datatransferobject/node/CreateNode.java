package server.datatransferobject.node;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateNode {
    private final String nome;
    private final Posicao posicao;
    private final String aviso;
    private final Boolean acessivel;

    @Builder
    public CreateNode(String nome, int posicaoX, int posicaoY, String aviso, Boolean acessivel) {
        this.nome = nome;
        this.posicao = new Posicao(posicaoX, posicaoY);
        this.aviso = aviso;
        this.acessivel = acessivel;
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
}