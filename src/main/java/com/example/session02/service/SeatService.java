package com.example.session02.service;

import com.example.session02.model.entity.Seat;
import com.example.session02.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService implements IService<Seat> {

    @Autowired
    private SeatRepository seatRepository;

    @Override
    public Seat save(Seat entity) {
        return seatRepository.save(entity);
    }

    @Override
    public Optional<Seat> findById(Long id) {
        return seatRepository.findById(id);
    }

    @Override
    public Seat update(Long id, Seat entity) {
        if (seatRepository.existsById(id)) {
            entity.setId(id);
            return seatRepository.save(entity);
        }
        throw new RuntimeException("Seat with ID " + id + " not found");
    }

    @Override
    public void delete(Long id) {
        seatRepository.deleteById(id);
    }

    public List<Seat> findAll() {
        return seatRepository.findAll();
    }
}
