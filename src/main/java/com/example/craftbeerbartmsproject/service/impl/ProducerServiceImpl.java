package com.example.craftbeerbartmsproject.service.impl;

import com.example.craftbeerbartmsproject.model.Producer;
import com.example.craftbeerbartmsproject.repository.ProducerRepository;
import com.example.craftbeerbartmsproject.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProducerServiceImpl implements ProducerService {

    ProducerRepository repository;

    @Autowired
    public ProducerServiceImpl(ProducerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Producer> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Producer> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Producer findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Producer update(Producer producer) {
        return repository.save(producer);
    }

    @Override
    public Producer add(Producer producer) {
        return repository.save(producer);
    }

    @Override
    public void delete(Producer producer) {
        repository.delete(producer);
    }
}