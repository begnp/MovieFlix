package com.movieflix.movieflix.mapper;

import com.movieflix.movieflix.entity.Streaming;
import com.movieflix.movieflix.request.StreamingRequest;
import com.movieflix.movieflix.response.StreamingResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    public Streaming toStreaming(StreamingRequest streamingRequest) {
        return Streaming
                .builder()
                .name(streamingRequest.name())
                .build();
    }

    public StreamingResponse toStreamingResponse(Streaming streaming) {
        return StreamingResponse
                .builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }

}
