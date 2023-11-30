package server.datatransferobject.node;

import lombok.Builder;
import lombok.Getter;
import server.datatransferobject.segment.SegmentDTO;

import java.util.List;

@Getter
public class UpdateNode {
    private final long registroSender;
    private final long id;
    private final String node;
    private final String nome;
    private final Posicao posicao;
    private final String aviso;
    private final Boolean acessivel;

    @Builder
    public UpdateNode(long registroSender, long id, String node, String nome, int posicaoX, int posicaoY, String aviso, Boolean acessivel) {
        this.registroSender = registroSender;
        this.id = id;
        this.node = node;
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
