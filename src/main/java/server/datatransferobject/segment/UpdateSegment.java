package server.datatransferobject.segment;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateSegment {
    private final long registroSender;
    private final Long pdiInicial;
    private final Long pdiFinal;
    private final Float distancia;
    private final String aviso;
    private final Boolean acessivel;

    @Builder
    public UpdateSegment(long registroSender, Long pdiInicial, Long pdiFinal,
                         Float distancia, String aviso, Boolean acessivel) {
        this.registroSender = registroSender;
        this.pdiInicial = pdiInicial;
        this.pdiFinal = pdiFinal;
        this.distancia = distancia;
        this.aviso = aviso;
        this.acessivel = acessivel;
    }

}
