package com.movieflix.movieflix.mapper;

import com.movieflix.movieflix.entity.Category;
import com.movieflix.movieflix.entity.Movie;
import com.movieflix.movieflix.entity.Streaming;
import com.movieflix.movieflix.request.MovieRequest;
import com.movieflix.movieflix.response.CategoryResponse;
import com.movieflix.movieflix.response.MovieResponse;
import com.movieflix.movieflix.response.StreamingResponse;

import java.util.List;

public class MovieMapper {

    public static Movie toMovie(MovieRequest movieRequest) {

        List<Category> categories = movieRequest.categories().stream()
                .map(categoryId -> Category.builder().id(categoryId).build())
                .toList();

        List<Streaming> streamings = movieRequest.streamings().stream()
                .map(streamingId -> Streaming.builder().id(streamingId).build())
                .toList();

        return Movie
                .builder()
                .title(movieRequest.title())
                .description(movieRequest.description())
                .releaseDate(movieRequest.releaseDate())
                .rating(movieRequest.rating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }

    public static MovieResponse toMovieResponse(Movie movie) {

        List<CategoryResponse> categories = movie.getCategories()
                .stream()
                .map(category -> CategoryMapper.toCategoryResponse(category))
                .toList();

        List<StreamingResponse> streamings = movie.getStreamings()
                .stream()
                .map(streaming -> StreamingMapper.toStreamingResponse(streaming))
                .toList();

        return MovieResponse
                .builder()
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .rating(movie.getRating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }

}
