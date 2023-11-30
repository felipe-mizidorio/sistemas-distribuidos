package server.datatransferobject.segment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import server.entity.Segment;

@Getter
public class SegmentDTO {
    @NotBlank
    private final Long pdiInicial;

    @NotBlank
    private final Long pdiFinal;

    @NotBlank
    private final Float distancia;

    private final String aviso;

    @NotNull
    private final Boolean acessivel;

    public SegmentDTO(Long pdiInicial, Long pdiFinal, Float distancia, String aviso, Boolean acessivel) {
        this.pdiInicial = pdiInicial;
        this.pdiFinal = pdiFinal;
        this.distancia = distancia;
        this.aviso = aviso;
        this.acessivel = acessivel;
    }

    public static SegmentDTO of(Segment segment) {
        if(segment == null) {
            return null;
        }
        return new SegmentDTO(segment.getPdiInicial(), segment.getPdiFinal(),
                segment.getDistancia(), segment.getAviso(), segment.getAcessivel());
    }
}
