package server.datatransferobject;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class DeleteUser {
    @NonNull
    private final Long registroSender;

    @NonNull
    private final Boolean isSenderAdmin;

    @NonNull
    private final Long registroToDelete;

    private final String email;

    private final String senha;

    @Builder
    public DeleteUser(@NonNull Long registroSender, @NonNull Boolean isSenderAdmin,
                      @NonNull Long registroToDelete, String email, String senha) {
        this.registroSender = registroSender;
        this.isSenderAdmin = isSenderAdmin;
        this.registroToDelete = registroToDelete;
        this.email = email;
        this.senha = senha;
    }
}
