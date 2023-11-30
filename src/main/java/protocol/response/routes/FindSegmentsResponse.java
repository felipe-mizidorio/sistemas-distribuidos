package protocol.response.routes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import protocol.response.Response;
import server.datatransferobject.segment.SegmentDTO;

import java.util.List;

public class FindSegmentsResponse implements Response<FindSegmentsResponse.Payload> {
    @Valid
    @NotNull
    private final FindSegmentsResponse.Payload payload;

    public FindSegmentsResponse(List<SegmentDTO> segmentos) {
        this.payload = new FindSegmentsResponse.Payload(segmentos);
    }

    public FindSegmentsResponse(FindSegmentsResponse.Payload payload) {
        this.payload = payload;
    }

    @Override
    public Payload payload() {
        return payload;
    }

    @Override
    public String toString() {
        return "FindSegmentsResponse{" +
                "payload=" + payload +
                '}';
    }

    @Getter
    public static class Payload {
        private final List<SegmentDTO> segmentos;

        public Payload(List<SegmentDTO> segmentos) {
            this.segmentos = segmentos;
        }

        @Override
        public String toString() {
            return "{ segmentos=" + segmentos +
                    '}';
        }
    }
}
