package testtask.technical_assesment_task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testtask.technical_assesment_task.dto.ProducerDto;
import testtask.technical_assesment_task.entity.Producer;
import testtask.technical_assesment_task.repository.ProducerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProducerServiceImpl implements ProducerService {

    private final ProducerRepository producerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProducerDto> getAllProducers() {
        return producerRepository.findAll().stream()
                .map(producer -> ProducerDto.builder()
                        .id(producer.getId())
                        .name(producer.getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProducerDto createProducer(ProducerDto producerDto) {
        Producer producer = Producer.builder()
                .name(producerDto.getName())
                .build();
        Producer savedProducer = producerRepository.save(producer);
        return ProducerDto.builder()
                .id(savedProducer.getId())
                .name(savedProducer.getName())
                .build();
    }
}

