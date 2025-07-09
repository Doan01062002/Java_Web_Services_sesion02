package com.example.session02.service;

import com.example.session02.model.entity.Theater;
import com.example.session02.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheaterService implements IService<Theater> {

    @Autowired
    private TheaterRepository theaterRepository;

    @Override
    public Theater save(Theater entity) {
        return theaterRepository.save(entity);
    }

    @Override
    public Optional<Theater> findById(Long id) {
        return theaterRepository.findById(id);
    }

    @Override
    public Theater update(Long id, Theater entity) {
        if (theaterRepository.existsById(id)){
            entity.setId(id);
            return theaterRepository.save(entity);
        }
        throw new RuntimeException("Theater not found with id: " + id);
    }

    @Override
    public void delete(Long id) {
        theaterRepository.deleteById(id);
    }

    public List<Theater> findAll() {
        return theaterRepository.findAll();
    }
}
