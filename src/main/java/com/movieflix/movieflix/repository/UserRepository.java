package com.movieflix.movieflix.repository;

import com.movieflix.movieflix.entity.User;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Builder
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
