package protocol.response.routes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import protocol.response.Response;
import server.datatransferobject.segment.SegmentDTO;
import server.entity.Segment;

public class UpdateSegmentResponse implements Response<SegmentDTO> {
    @Valid
    @NotNull
    private final SegmentDTO payload;

    public UpdateSegmentResponse(SegmentDTO payload) {
        this.payload = payload;
    }

    public static UpdateSegmentResponse of(Segment node) {
        return new UpdateSegmentResponse(SegmentDTO.of(node));
    }

    @Override
    public SegmentDTO payload() {
        return payload;
    }

    @Override
    public String toString() {
        return "UpdateSegmentResponse{" +
                "payload=" + payload +
                '}';
    }
}
