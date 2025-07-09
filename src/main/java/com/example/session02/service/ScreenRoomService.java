package com.example.session02.service;

import com.example.session02.model.entity.ScreenRoom;
import com.example.session02.repository.ScreenRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScreenRoomService implements IService<ScreenRoom> {
    @Autowired
    private ScreenRoomRepository screenRoomRepository;

    @Override
    public ScreenRoom save(ScreenRoom entity) {
        return screenRoomRepository.save(entity);
    }

    @Override
    public Optional<ScreenRoom> findById(Long id) {
        return screenRoomRepository.findById(id);
    }

    @Override
    public ScreenRoom update(Long id, ScreenRoom entity) {
        if (screenRoomRepository.existsById(id)){
            entity.setId(id);
            return screenRoomRepository.save(entity);
        }
        throw new RuntimeException("ScreenRoom not found with id: " + id);
    }

    @Override
    public void delete(Long id) {
        screenRoomRepository.deleteById(id);
    }

    public List<ScreenRoom> findAll() {
        return screenRoomRepository.findAll();
    }
}
