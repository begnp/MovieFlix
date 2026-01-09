package com.movieflix.movieflix.controller;

import com.movieflix.movieflix.entity.Streaming;
import com.movieflix.movieflix.mapper.StreamingMapper;
import com.movieflix.movieflix.request.StreamingRequest;
import com.movieflix.movieflix.response.StreamingResponse;
import com.movieflix.movieflix.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/streaming")
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingService streamingService;

    @GetMapping()
    public ResponseEntity<List<StreamingResponse>> getAllStreaming() {
        return ResponseEntity.ok(streamingService.findAll()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList());
    }

    @PostMapping
    public ResponseEntity<StreamingResponse> saveStreaming(@RequestBody StreamingRequest request) {
        Streaming newStreaming = StreamingMapper.toStreaming(request);
        Streaming savedStreaming = streamingService.save(newStreaming);
        StreamingResponse Streaming = StreamingMapper.toStreamingResponse(savedStreaming);
        return ResponseEntity.status(HttpStatus.CREATED).body(Streaming);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getStreamingById(@PathVariable Long id) {
        Optional<Streaming> optStreaming = streamingService.findById(id);
        if (optStreaming.isPresent()) {
            StreamingResponse streaming = StreamingMapper.toStreamingResponse(optStreaming.get());
            return ResponseEntity.ok().body(streaming);
        }
        return ResponseEntity.notFound().build();
        // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStreamingById(@PathVariable Long id) { // Esse Void precisa ter "V" em Uppercase
        streamingService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
