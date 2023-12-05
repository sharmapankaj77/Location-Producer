package com.master.data.location.locationproducer.controller;

import com.azure.storage.blob.models.BlobItem;
import com.master.data.location.locationproducer.service.BlobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RestController
@RequestMapping("process/blob")
public class BlobController {

    @Autowired
    private BlobService blobService;

    @GetMapping("/{containerName}")
    public Flux<String> processBlobs(@PathVariable String containerName) {
        return blobService.processBlobs(containerName);
    }
}

