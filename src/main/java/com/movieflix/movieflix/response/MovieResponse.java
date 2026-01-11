package com.movieflix.movieflix.response;

import lombok.Builder;

@Builder
public record MovieResponse(Long id, String title) {
}
