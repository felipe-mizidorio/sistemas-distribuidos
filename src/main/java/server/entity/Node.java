package server.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;
import server.datatransferobject.node.CreateNode;
import server.datatransferobject.node.UpdateNode;

@Entity
@Table(name = "Nodes")
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Node {
    @NotNull
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 3, max = 255)
    @Column(unique = true)
    private String nome;

    @NotNull
    private Double posicaoX;

    @NotNull
    private Double posicaoY;

    private String aviso;

    @NotNull
    private Boolean acessivel;

    public Node() {

    }

    public static Node of(CreateNode node) {
        var entity = new Node();
        entity.setNome(node.getNome());
        entity.setPosicaoX(node.getPosicao().x());
        entity.setPosicaoY(node.getPosicao().y());
        entity.setAviso(node.getAviso());
        entity.setAcessivel(node.getAcessivel());
        return entity;
    }

    public static Node of(UpdateNode node) {
        var entity = new Node();
        entity.setNome(node.getNome());
        entity.setAviso(node.getAviso());
        entity.setAcessivel(node.getAcessivel());
        return entity;
    }

    public void update(Node info) {
        if(info.getNome() != null) {
            setNome(info.getNome());
        }
        if(info.getPosicaoX() != null) {
            setPosicaoX(info.getPosicaoX());
        }
        if(info.getPosicaoY() != null) {
            setPosicaoY(info.getPosicaoY());
        }
        if(info.getAviso() != null) {
            setAviso(info.getAviso());
        }
        if(info.getAcessivel() != null) {
            setAcessivel(info.getAcessivel());
        }
    }
}
