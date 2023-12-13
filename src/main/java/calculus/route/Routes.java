package calculus.route;

import calculus.dijkstra.Dijkstra;
import calculus.direction.Direction;
import server.controller.NodeController;
import server.controller.SegmentController;
import server.datatransferobject.node.NodeDTO;
import server.datatransferobject.route.RouteDTO;
import server.datatransferobject.segment.SegmentDTO;
import server.exceptions.BadRequestException;
import server.exceptions.ResourceNotFoundException;

import java.util.*;

public class Routes {

    public static void verifyPDI(Long pdi_inicial, Long pdi_final) throws ResourceNotFoundException, BadRequestException {
        var controller = NodeController.getInstance();
        boolean isPdiInicialAcessivel = controller.findNode(pdi_inicial).getAcessivel();
        boolean isPdiFinalAcessivel = controller.findNode(pdi_final).getAcessivel();
        if(!isPdiInicialAcessivel) {
            throw new BadRequestException("PDI Inicial não está acessível!");
        }
        if(!isPdiFinalAcessivel) {
            throw new BadRequestException("PDI Final não está acessível!");
        }
    }

    public static String getNomePdi(Long pdi) throws ResourceNotFoundException {
        NodeDTO node = NodeController.getInstance().findNode(pdi);
        return node.getNome();
    }

    public static Double getDistanceSegment(Long pdi_inicial, Long pdi_final) throws ResourceNotFoundException {
        SegmentDTO segment = SegmentDTO.of(SegmentController.getInstance().findSegment(pdi_inicial, pdi_final));
        return segment.getDistancia();
    }

    public static String getAvisoSegment(Long pdi_inicial, Long pdi_final) throws ResourceNotFoundException {
        SegmentDTO segment = SegmentDTO.of(SegmentController.getInstance().findSegment(pdi_inicial, pdi_final));
        return segment.getAviso();
    }

    public static void printRoute(List<Dijkstra.Node> routeFounded) {
        for(Dijkstra.Node node : routeFounded) {
            System.out.println("Node inicial: " + node.getPdi_inicial());
            System.out.println("Node final: " + node.getPdi_final());
            System.out.println("Distancia: " + node.getDistance());
            System.out.println();
        }
    }

    public static List<RouteDTO> calculateRoute(Long pdi_inicial, Long pdi_final) throws BadRequestException, ResourceNotFoundException {
        verifyPDI(pdi_inicial, pdi_final);

        List<Dijkstra.Node> routeFounded = Dijkstra.calculate(pdi_inicial, pdi_final);
        printRoute(routeFounded);
        List<String> directions = Direction.getAllDirections(routeFounded);

        List<RouteDTO> route = new ArrayList<>();
        for(int i = 0; i < routeFounded.size(); i++) {
            if(i == (routeFounded.size() - 1)) {
                route.add(new RouteDTO(
                        getNomePdi(routeFounded.get(i).getPdi_inicial()),
                        getNomePdi(routeFounded.get(i).getPdi_final()),
                        getDistanceSegment(routeFounded.get(i).getPdi_inicial(), routeFounded.get(i).getPdi_final()),
                        "Destino",
                        directions.get(i)
                ));
            } else {
                route.add(new RouteDTO(
                        getNomePdi(routeFounded.get(i).getPdi_inicial()),
                        getNomePdi(routeFounded.get(i).getPdi_final()),
                        getDistanceSegment(routeFounded.get(i).getPdi_inicial(), routeFounded.get(i).getPdi_final()),
                        getAvisoSegment(routeFounded.get(i).getPdi_inicial(), routeFounded.get(i).getPdi_final()),
                        directions.get(i)
                ));
            }
        }
        return route;
    }
}
