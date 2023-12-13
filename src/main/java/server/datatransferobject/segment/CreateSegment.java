package server.datatransferobject.segment;

import lombok.Builder;
import lombok.Getter;
import server.entity.Node;

@Getter
public class CreateSegment {
    private final Long pdi_inicial;
    private final Long pdi_final;
    private final Double distancia;
    private final String aviso;
    private final Boolean acessivel;

    @Builder
    public CreateSegment(Long pdi_inicial, Long pdi_final, Double distancia, String aviso, Boolean acessivel) {
        this.pdi_inicial = pdi_inicial;
        this.pdi_final = pdi_final;
        this.distancia = distancia;
        this.aviso = aviso;
        this.acessivel = acessivel;
    }
}
