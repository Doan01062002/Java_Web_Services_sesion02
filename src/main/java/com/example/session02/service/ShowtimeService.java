package com.example.session02.service;

import com.example.session02.model.entity.Showtime;
import com.example.session02.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ShowtimeService implements IService<Showtime> {
    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Override
    public Showtime save(Showtime entity) {
        return showtimeRepository.save(entity);
    }

    @Override
    public Optional<Showtime> findById(Long id) {
        return showtimeRepository.findById(id);
    }

    @Override
    public Showtime update(Long id, Showtime entity) {
        if (showtimeRepository.existsById(id)){
            entity.setId(id);
            return showtimeRepository.save(entity);
        }
        throw new RuntimeException("Showtime not found with id: " + id);
    }

    @Override
    public void delete(Long id) {
        showtimeRepository.deleteById(id);
    }

    public List<Showtime> findAll() {
        return showtimeRepository.findAll();
    }

    public List<Showtime> findFilteredShowtimes(Long movieId, Long screenRoomId, LocalDate showDate) {
        return showtimeRepository.findByFilters(movieId, screenRoomId, showDate);
    }
}