package calculus.dijkstra;

import lombok.Getter;
import server.controller.SegmentController;
import server.datatransferobject.segment.SegmentDTO;
import server.exceptions.BadRequestException;

import java.sql.SQLOutput;
import java.util.*;

public class Dijkstra {
    @Getter
    public static class Node {
        private final Long pdi_inicial;
        private final Long pdi_final;
        private final Double distance;
        private final Node father;
        private final List<Node> neighbors;

        public Node(Long pdi_inicial, Long pdi_final, Double distance, Node father, List<Node> neighbors) {
            this.pdi_inicial = pdi_inicial;
            this.pdi_final = pdi_final;
            this.distance = distance;
            this.father = father;
            this.neighbors = neighbors;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "pdi_inicial=" + pdi_inicial +
                    ", pdi_final=" + pdi_final +
                    ", distance=" + distance +
                    '}';
        }
    }

    public static Node createNode(Long pdi_inicial, Long pdi_final, Double distance, Node father) {
        return new Node(pdi_inicial, pdi_final, distance, father, new ArrayList<>());
    }

    public static List<Node> findLeafs(Node node) {
        List<Node> leafs = new ArrayList<>();
        if(node.neighbors.isEmpty()) {
            leafs.add(node);
        } else {
            for(Node neighbor : node.neighbors) {
                leafs.addAll(findLeafs(neighbor));
            }
        }
        return leafs;
    }

    public static Node shortestLeaf(List<Node> leafs, Long pdi_final) {
        Node shortestLeaf = leafs.get(0);
        for(Node node : leafs) {
            if(node.getDistance() < shortestLeaf.getDistance()) {
                shortestLeaf = node;
            }
            if(node.getPdi_final().equals(pdi_final)) {
                return node;
            }
        }
        return shortestLeaf;
    }

    public static void pruning(Node node, Node startNode) {
        Node aux;
        while(node.father != null) {
            aux = node;
            node = node.father;
            if (node.neighbors.size() > 1 || node == startNode) {
                node.neighbors.remove(aux);
                break;
            }
        }
    }

    public static List<Node> calculate(Long pdi_inicial, Long pdi_final) throws BadRequestException {
        List<Node> route = new ArrayList<>();

        Node startNode = createNode(pdi_inicial, pdi_inicial, 0.0, null);
        Node currentNode = startNode;

        while(!Objects.equals(currentNode.pdi_final, pdi_final)) {
            List<SegmentDTO> segmentList = SegmentController.getInstance().findNeighborsSegments(currentNode.pdi_final);
            for(SegmentDTO segment : segmentList) {
                if(!Objects.equals(currentNode.pdi_inicial, segment.getPdi_final())) {
                    Node newNode = createNode(segment.getPdi_inicial(), segment.getPdi_final(), currentNode.getDistance() + segment.getDistancia(), currentNode);
                    currentNode.neighbors.add(newNode);
                }
            }
            Node shortestLeaf = shortestLeaf(findLeafs(startNode), pdi_final);
            if(currentNode == shortestLeaf) {
                pruning(shortestLeaf, startNode);
                currentNode = shortestLeaf(findLeafs(startNode), pdi_final);
                if(currentNode == startNode) {
                    throw new BadRequestException("Caminho não encontrado!");
                }
            } else {
                currentNode = shortestLeaf;
            }
        }
        while(currentNode.father != null) {
            route.add(currentNode);
            currentNode = currentNode.father;
        }
        Collections.reverse(route);
        return route;
    }

}
