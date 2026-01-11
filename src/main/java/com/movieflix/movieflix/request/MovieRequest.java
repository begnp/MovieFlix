package com.movieflix.movieflix.request;

import lombok.Builder;

@Builder
public record MovieRequest(String title) {
}
