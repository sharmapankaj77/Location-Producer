package com.master.data.location.locationproducer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.master.data.location.locationproducer.dto.LocationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReactiveProducerService {

    private final Logger log = LoggerFactory.getLogger(ReactiveProducerService.class);
    private final ReactiveKafkaProducerTemplate<Integer, String> reactiveKafkaProducerTemplate;

    private final ObjectMapper objectMapper;

    @Value(value = "${TOPIC_NAME}")
    private String topic;

    public ReactiveProducerService(ReactiveKafkaProducerTemplate<Integer, String> reactiveKafkaProducerTemplate, ObjectMapper objectMapper) {
        this.reactiveKafkaProducerTemplate = reactiveKafkaProducerTemplate;
        this.objectMapper = objectMapper;
    }

    public void send(LocationEvent locationEvent) throws JsonProcessingException {
        log.info("send to topic={}, {}={},", topic, LocationEvent.class.getSimpleName(), locationEvent);
        reactiveKafkaProducerTemplate.send(topic, objectMapper.writeValueAsString(locationEvent))
                .doOnSuccess(senderResult -> log.info("sent {} offset : {}", locationEvent, senderResult.recordMetadata().offset()))
                .subscribe();
    }
}
