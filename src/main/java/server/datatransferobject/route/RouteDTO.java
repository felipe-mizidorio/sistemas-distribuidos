package server.datatransferobject.route;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class RouteDTO {
    @NotNull
    private final String nome_inicio;

    @NotNull
    private final String nome_final;

    @NotNull
    private final Double distancia;

    private final String aviso;

    @NotNull
    private final String direcao;

    public RouteDTO(String nome_inicio, String nome_final, Double distancia, String aviso, String direcao) {
        this.nome_inicio = nome_inicio;
        this.nome_final = nome_final;
        this.distancia = distancia;
        this.aviso = aviso;
        this.direcao = direcao;
    }

    @Override
    public String toString() {
        return "RouteDTO{" +
                "nome_inicio=" + nome_inicio +
                ", nome_final=" + nome_final +
                ", distancia=" + distancia +
                ", aviso='" + aviso + '\'' +
                ", direcao='" + direcao + '\'' +
                '}';
    }
}
