package testtask.technical_assesment_task.service;

import testtask.technical_assesment_task.dto.ProducerDto;

import java.util.List;

public interface ProducerService {
    List<ProducerDto> getAllProducers();
    ProducerDto createProducer(ProducerDto producerDto);
}

