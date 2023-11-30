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
@Table(name = "Segments", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"pdiInicial", "pdiFinal"}),
        @UniqueConstraint(columnNames = {"pdiFinal", "pdiInicial"})})
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Segment {
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long pdiInicial;

    @NotNull
    private Long pdiFinal;

    @NotNull
    private Float distancia;

    private String aviso;

    @NotNull
    private Boolean acessivel;

    public Segment() {

    }

    public static Segment of(CreateSegment segment) {
        var entity = new Segment();
        entity.setPdiInicial(segment.getPdiInicial());
        entity.setPdiFinal(segment.getPdiFinal());
        entity.setDistancia(segment.getDistancia());
        entity.setAviso(segment.getAviso());
        entity.setAcessivel(segment.getAcessivel());
        return entity;
    }

    public static Segment of(UpdateSegment segment) {
        var entity = new Segment();
        entity.setPdiInicial(segment.getPdiInicial());
        entity.setPdiFinal(segment.getPdiFinal());
        entity.setDistancia(segment.getDistancia());
        entity.setAviso(segment.getAviso());
        entity.setAcessivel(segment.getAcessivel());
        return entity;
    }

    public void update(Segment info) {
        if(info.getPdiInicial() != null) {
            setPdiFinal(info.getPdiInicial());
        }
        if(info.getPdiFinal() != null) {
            setPdiFinal(info.getPdiFinal());
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
