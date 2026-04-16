package testtask.technical_assesment_task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import testtask.technical_assesment_task.dto.ProducerDto;
import testtask.technical_assesment_task.service.ProducerService;

import java.util.List;

@RestController
@RequestMapping("/producers")
@RequiredArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    @GetMapping
    public ResponseEntity<List<ProducerDto>> getAllProducers() {
        return ResponseEntity.ok(producerService.getAllProducers());
    }

    @PostMapping
    public ResponseEntity<ProducerDto> createProducer(@RequestBody ProducerDto producerDto) {
        return new ResponseEntity<>(producerService.createProducer(producerDto), HttpStatus.CREATED);
    }
}

