package server.controller;

import server.datatransferobject.segment.CreateSegment;
import server.datatransferobject.segment.DeleteSegment;
import server.datatransferobject.segment.SegmentDTO;
import server.datatransferobject.segment.UpdateSegment;
import server.entity.Segment;
import server.exceptions.ResourceNotFoundException;
import server.exceptions.ServerResponseException;
import server.repository.SegmentRepository;

import java.util.List;
import java.util.Objects;

public class SegmentController {
    private static SegmentController instance = null;
    private final SegmentRepository repository = new SegmentRepository();

    private SegmentController() {
    }

    public static SegmentController getInstance() {
        if (instance == null) {
            instance = new SegmentController();
        }
        return instance;
    }

    public SegmentDTO createSegment(CreateSegment segment) throws ServerResponseException {
        var entity = Segment.of(segment);
        repository.create(entity);
        return SegmentDTO.of(entity);
    }

    public Segment findSegment(Long pdiInicial, Long pdiFinal) throws ResourceNotFoundException {
        return repository.find(pdiInicial, pdiFinal).orElseThrow(()->new ResourceNotFoundException("Segmento não encontrado"));
    }

    public List<SegmentDTO> findSegments() {
        return repository.findAll()
                .stream()
                .map(SegmentDTO::of)
                .toList();
    }

    public SegmentDTO updateSegment(UpdateSegment segment) throws ServerResponseException {
        Long segmentId = findSegment(segment.getPdi_inicial(), segment.getPdi_final()).getId();
        var entity = repository.update(segmentId, Segment.of(segment));
        return SegmentDTO.of(entity);
    }

    public void deleteSegment(DeleteSegment segment) throws ResourceNotFoundException {
        Long segmentId = findSegment(segment.getPdi_inicial(), segment.getPdi_final()).getId();
        repository.delete(segmentId);
    }

    public void deleteSegment(Long id) {
        repository.delete(id);
    }

    public List<SegmentDTO> findNeighborsSegments(Long id) {
        return repository.findNeighborsNodeSegments(id)
                .stream()
                .map(SegmentDTO::of)
                .toList();
    }

    public Long findNextNodeInSegment(Long idSegment, Long idNode) throws ResourceNotFoundException {
        Segment segment = repository.find(idSegment).orElseThrow(()->new ResourceNotFoundException("Segmento não encontrado"));
        if(Objects.equals(segment.getPdi_inicial(), idNode)) {
            return segment.getPdi_final();
        } else if(Objects.equals(segment.getPdi_final(), idNode)) {
            return segment.getPdi_inicial();
        }
        return null;
    }
}
