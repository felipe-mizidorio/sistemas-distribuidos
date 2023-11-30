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
    private final Long pdiInicial;

    @NonNull
    private final Long pdiFinal;

    @Builder
    public DeleteSegment(@NonNull Long registroSender, @NonNull Boolean isSenderAdmin, Long pdiInicial, Long pdiFinal) {
        this.registroSender = registroSender;
        this.isSenderAdmin = isSenderAdmin;
        this.pdiInicial = pdiInicial;
        this.pdiFinal = pdiFinal;
    }
}
