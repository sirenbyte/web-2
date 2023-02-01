package com.example.web2.service;

import com.example.web2.dto.Message;
import com.example.web2.repository.HistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;

    public List<Message> getAll() {
        return historyRepository.findAll();
    }

    public void save(Message message){
        historyRepository.save(message);
    }
}
