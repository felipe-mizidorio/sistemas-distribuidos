package server.controller;

import server.datatransferobject.node.CreateNode;
import server.datatransferobject.node.DeleteNode;
import server.datatransferobject.node.NodeDTO;
import server.datatransferobject.node.UpdateNode;
import server.datatransferobject.segment.DeleteSegment;
import server.datatransferobject.segment.SegmentDTO;
import server.entity.Node;
import server.entity.Segment;
import server.exceptions.ResourceNotFoundException;
import server.exceptions.ServerResponseException;
import server.repository.NodeRepository;
import server.repository.SegmentRepository;

import java.util.List;

public class NodeController {
    private static NodeController instance = null;
    private final NodeRepository repository = new NodeRepository();

    private NodeController() {
    }

    public static NodeController getInstance() {
        if (instance == null) {
            instance = new NodeController();
        }
        return instance;
    }

    public NodeDTO createNode(CreateNode node) throws ServerResponseException {
        var entity = Node.of(node);
        repository.create(entity);
        return NodeDTO.of(entity);
    }

    public NodeDTO findNode(Long nodeId) throws ResourceNotFoundException {
        return NodeDTO.of(repository.find(nodeId).orElseThrow(() -> new ResourceNotFoundException("PDI não encontrado")));
    }

    public List<NodeDTO> findNodes() {
        return repository.findAll()
                .stream()
                .map(NodeDTO::of)
                .toList();
    }

    public NodeDTO updateNode(UpdateNode node) throws ServerResponseException {
        var entity = repository.update(node.getId(), Node.of(node));
        return NodeDTO.of(entity);
    }

    public void deleteNode(DeleteNode node) throws ResourceNotFoundException {
        repository.delete(node.getNodeToDelete());
        var controller = SegmentController.getInstance();
        List<SegmentDTO> segmentList = controller.findSegments();
        if(!segmentList.isEmpty()) {
            for(SegmentDTO segment : segmentList) {
                if(node.getNodeToDelete().equals(segment.getPdi_inicial()) || node.getNodeToDelete().equals(segment.getPdi_final())) {
                    Long id = controller.findSegment(segment.getPdi_inicial(), segment.getPdi_final()).getId();
                    controller.deleteSegment(id);
                }
            }
        }
    }
}
