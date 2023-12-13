package server.datatransferobject.segment;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateSegment {
    private final long registroSender;
    private final Long pdi_inicial;
    private final Long pdi_final;
    private final String aviso;
    private final Boolean acessivel;

    @Builder
    public UpdateSegment(long registroSender, Long pdi_inicial, Long pdi_final,
                         String aviso, Boolean acessivel) {
        this.registroSender = registroSender;
        this.pdi_inicial = pdi_inicial;
        this.pdi_final = pdi_final;
        this.aviso = aviso;
        this.acessivel = acessivel;
    }
}
