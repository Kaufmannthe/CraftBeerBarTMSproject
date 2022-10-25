package com.example.craftbeerbartmsproject.service;

import com.example.craftbeerbartmsproject.model.Producer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProducerService {

    List<Producer> findAll();

    Producer update(Producer producer);

    Producer add(Producer producer);

    void delete(Producer producer);

    String saveImage(MultipartFile file) throws IOException;

    Producer findByLogin(String login);

}
