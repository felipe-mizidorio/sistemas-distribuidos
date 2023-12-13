package calculus.direction;

import calculus.dijkstra.Dijkstra;
import server.controller.NodeController;
import server.datatransferobject.node.NodeDTO;
import server.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Direction {
    public static String getOrientation(Long pdi_inicial, Long pdi_final) throws ResourceNotFoundException {
        NodeDTO node_inicial = NodeController.getInstance().findNode(pdi_inicial);
        NodeDTO node_final = NodeController.getInstance().findNode(pdi_final);

        double dX = node_final.getPosicao().x() - node_inicial.getPosicao().x();
        double dY = node_final.getPosicao().y() - node_inicial.getPosicao().y();

        if(dX > 0) {
            return "Leste";
        } else if(dX < 0) {
            return "Oeste";
        } else if(dY > 0) {
            return "Norte";
        } else if(dY < 0) {
            return "Sul";
        } else {
            return null;
        }
    }

    public static String getDirection(String lastOrientation, String newOrientation) {
        if (lastOrientation.equals(newOrientation)) {
            return "Seguir em frente";
        } else if (areAdjacentsToTheRight(lastOrientation, newOrientation)) {
            return "Virar à direita";
        } else {
            return "Virar à esquerda";
        }
    }

    private static boolean areAdjacentsToTheRight(String lastOrientation, String newOrientation) {
        return (lastOrientation.equals("Norte") && newOrientation.equals("Leste")) ||
                (lastOrientation.equals("Leste") && newOrientation.equals("Sul")) ||
                (lastOrientation.equals("Sul") && newOrientation.equals("Oeste")) ||
                (lastOrientation.equals("Oeste") && newOrientation.equals("Norte"));
    }

    public static List<String> getAllDirections(List<Dijkstra.Node> route) throws ResourceNotFoundException {
        List<String> directions = new ArrayList<>();

        String lastOrientation = getOrientation(route.get(0).getPdi_inicial(), route.get(0).getPdi_final());
        for(int i = 0; i < route.size(); i++) {
            if(i == 0) {
                directions.add("Seguir em frente");
            } else {
                assert lastOrientation != null;
                directions.add(getDirection(lastOrientation, getOrientation(route.get(i).getPdi_inicial(), route.get(i).getPdi_final())));
                lastOrientation = getOrientation(route.get(i).getPdi_inicial(), route.get(i).getPdi_final());
            }
        }
        return directions;
    }
}
