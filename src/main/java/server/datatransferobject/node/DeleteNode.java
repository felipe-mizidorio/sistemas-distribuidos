package server.datatransferobject.node;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class DeleteNode {
    @NonNull
    private final Long registroSender;

    @NonNull
    private final Boolean isSenderAdmin;

    @NonNull
    private final Long nodeToDelete;

    @Builder
    public DeleteNode(@NonNull Long registroSender, @NonNull Boolean isSenderAdmin, Long nodeToDelete) {
        this.registroSender = registroSender;
        this.isSenderAdmin = isSenderAdmin;
        this.nodeToDelete = nodeToDelete;
    }
}
