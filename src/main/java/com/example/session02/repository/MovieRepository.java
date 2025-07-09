package com.example.session02.repository;

import com.example.session02.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    // Tìm phim theo tên (không phân biệt hoa thường, có chứa từ khóa)
    List<Movie> findByTitleContainingIgnoreCase(String title);
}
