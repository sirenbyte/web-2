package com.example.web2.controller;

import com.example.web2.dto.Message;
import com.example.web2.service.HistoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/history")
@AllArgsConstructor
@CrossOrigin
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping
    public ResponseEntity<List<Message>> getAll(){
        return ResponseEntity.ok(historyService.getAll());
    }
}
