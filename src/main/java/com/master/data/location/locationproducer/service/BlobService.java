package com.master.data.location.locationproducer.service;

import com.azure.core.util.BinaryData;
import com.azure.storage.blob.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.master.data.location.locationproducer.dto.LocationEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
public class BlobService {

    @Value("${azure.storage.blob.connection-string}")
    private String connectionString;

    public BlobService(ReactiveProducerService reactiveProducerService) {
        this.reactiveProducerService = reactiveProducerService;
    }

    ReactiveProducerService reactiveProducerService;



    public Flux<String> processBlobs(String containerName) {
        BlobServiceClientBuilder builder = new BlobServiceClientBuilder();
        builder.connectionString(connectionString);
        BlobServiceAsyncClient asyncClient = builder.buildAsyncClient();
        BlobContainerAsyncClient containerClient = asyncClient.getBlobContainerAsyncClient(containerName);

        return containerClient.listBlobs().flatMap(data -> containerClient.getBlobAsyncClient(data.getName())
                .downloadContent().flatMap(blobData -> {
                    callProducer(blobData);
                    return Mono.just(blobData);
                }))
                .map(data -> "SUCCESS");
    }

    private LocationEvent buildLocationEvent(BinaryData blobData) {
        return LocationEvent.builder()
                .location(blobData)
                .locationEventId(new Random().nextInt())
                .build();
    }
    private void callProducer(BinaryData blobData) {
        try {
            reactiveProducerService.send(buildLocationEvent(blobData));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

