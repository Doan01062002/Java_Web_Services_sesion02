package com.example.session02.service;

import com.example.session02.model.entity.Movie;
import com.example.session02.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements IService<Movie> {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie save(Movie entity) {
        return movieRepository.save(entity);
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public Movie update(Long id, Movie entity) {
        if (movieRepository.existsById(id)){
            entity.setId(id);
            return movieRepository.save(entity);
        }
        throw new RuntimeException("Movie not found with id: " + id);
    }

    @Override
    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }
}
