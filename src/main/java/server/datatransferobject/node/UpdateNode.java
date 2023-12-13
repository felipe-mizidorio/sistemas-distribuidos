package server.datatransferobject.node;

import lombok.Builder;
import lombok.Getter;
import server.datatransferobject.segment.SegmentDTO;

import java.util.List;

@Getter
public class UpdateNode {
    private final long registroSender;
    private final long id;
    private final String nome;
    private final String aviso;
    private final Boolean acessivel;

    @Builder
    public UpdateNode(long registroSender, long id, String nome, String aviso, Boolean acessivel) {
        this.registroSender = registroSender;
        this.id = id;
        this.nome = nome;
        this.aviso = aviso;
        this.acessivel = acessivel;
    }
}
