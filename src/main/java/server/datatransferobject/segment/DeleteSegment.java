package server.datatransferobject.segment;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class DeleteSegment {
    @NonNull
    private final Long registroSender;

    @NonNull
    private final Boolean isSenderAdmin;

    @NonNull
    private final Long pdi_inicial;

    @NonNull
    private final Long pdi_final;

    @Builder
    public DeleteSegment(Long registroSender, Boolean isSenderAdmin, Long pdi_inicial, Long pdi_final) {
        this.registroSender = registroSender;
        this.isSenderAdmin = isSenderAdmin;
        this.pdi_inicial = pdi_inicial;
        this.pdi_final = pdi_final;
    }
}
