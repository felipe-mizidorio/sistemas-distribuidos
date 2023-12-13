package server.datatransferobject.node;

import jakarta.validation.constraints.*;
import lombok.Getter;
import server.entity.Node;

@Getter
public class NodeDTO {
    @Positive
    private final Long id;

    @NotBlank
    @Size(min = 3, max = 255)
    private final String nome;

    @NotNull
    private final Posicao posicao;

    private final String aviso;

    @NotNull
    private final Boolean acessivel;

    public NodeDTO(Long id, String nome, Double posicaoX, Double posicaoY, String aviso, Boolean acessivel) {
        this.id = id;
        this.nome = nome;
        this.posicao = new Posicao(posicaoX, posicaoY);
        this.aviso = aviso;
        this.acessivel = acessivel;
    }

    public static NodeDTO of(Node node) {
        if (node == null) {
            return null;
        }
        return new NodeDTO(node.getId(), node.getNome(), node.getPosicaoX(), node.getPosicaoY(),
                node.getAviso(), node.getAcessivel());
    }

    @Override
    public String toString() {
        return "NodeDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", posicao=" + posicao +
                ", aviso='" + aviso + '\'' +
                ", acessivel=" + acessivel +
                '}';
    }

    public record Posicao(Double x, Double y) {
        @Override
        public String toString() {
            return "Posicao{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
