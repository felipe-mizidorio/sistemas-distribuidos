package server.datatransferobject.segment;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import server.entity.Segment;

@Getter
public class SegmentDTO {
    @NotNull
    private final Long pdi_inicial;

    @NotNull
    private final Long pdi_final;

//    @NotNull
    private final Double distancia;

    private final String aviso;

    @NotNull
    private final Boolean acessivel;

    public SegmentDTO(Long pdi_inicial, Long pdi_final, Double distancia, String aviso, Boolean acessivel) {
        this.pdi_inicial = pdi_inicial;
        this.pdi_final = pdi_final;
        this.distancia = distancia;
        this.aviso = aviso;
        this.acessivel = acessivel;
    }

    public static SegmentDTO of(Segment segment) {
        if(segment == null) {
            return null;
        }
        return new SegmentDTO(segment.getPdi_inicial(), segment.getPdi_final(),
                segment.getDistancia(), segment.getAviso(), segment.getAcessivel());
    }

    @Override
    public String toString() {
        return "SegmentDTO{" +
                "pdi_inicial=" + pdi_inicial +
                ", pdi_final=" + pdi_final +
                ", distancia=" + distancia +
                ", aviso='" + aviso + '\'' +
                ", acessivel=" + acessivel +
                '}';
    }
}
