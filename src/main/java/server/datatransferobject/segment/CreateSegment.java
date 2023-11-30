package server.datatransferobject.segment;

import lombok.Builder;
import lombok.Getter;
import server.entity.Node;

@Getter
public class CreateSegment {
    private final Long pdiInicial;
    private final Long pdiFinal;
    private final Float distancia;
    private final String aviso;
    private final Boolean acessivel;

    @Builder
    public CreateSegment(Long pdiInicial, Long pdiFinal, Float distancia, String aviso, Boolean acessivel) {
        this.pdiInicial = pdiInicial;
        this.pdiFinal = pdiFinal;
        this.distancia = distancia;
        this.aviso = aviso;
        this.acessivel = acessivel;
    }
}
