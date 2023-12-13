package server.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;
import server.datatransferobject.segment.CreateSegment;
import server.datatransferobject.segment.UpdateSegment;

@Entity
@Table(name = "Segments", uniqueConstraints = @UniqueConstraint(columnNames = {"pdi_inicial", "pdi_final"}))
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Segment {
    @NotNull
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Long pdi_inicial;

    @NotNull
    private Long pdi_final;

    @NotNull
    private Double distancia;

    private String aviso;

    @NotNull
    private Boolean acessivel;

    public Segment() {

    }

    public static Segment of(CreateSegment segment) {
        var entity = new Segment();
        entity.setPdi_inicial(segment.getPdi_inicial());
        entity.setPdi_final(segment.getPdi_final());
        entity.setDistancia(segment.getDistancia());
        entity.setAviso(segment.getAviso());
        entity.setAcessivel(segment.getAcessivel());
        return entity;
    }

    public static Segment of(UpdateSegment segment) {
        var entity = new Segment();
        entity.setPdi_inicial(segment.getPdi_inicial());
        entity.setPdi_final(segment.getPdi_final());
        entity.setAviso(segment.getAviso());
        entity.setAcessivel(segment.getAcessivel());
        return entity;
    }

    public void update(Segment info) {
        if(info.getPdi_inicial() != null) {
            setPdi_final(info.getPdi_inicial());
        }
        if(info.getPdi_final() != null) {
            setPdi_final(info.getPdi_final());
        }
        if(info.getDistancia() != null) {
            setDistancia(info.getDistancia());
        }
        if(info.getAviso() != null) {
            setAviso(info.getAviso());
        }
        if(info.getAcessivel() != null) {
            setAcessivel(info.getAcessivel());
        }
    }
}
