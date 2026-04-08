package com.bitpalette.api.services;

import com.bitpalette.api.events.ArtworkPublishedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class ArtworkService {
    private final IStorageService storageService;
    private final ApplicationEventPublisher eventPublisher;

    // DI por constructor: La mejor práctica en Spring
    public ArtworkService(IStorageService storageService, ApplicationEventPublisher eventPublisher) {
        this.storageService = storageService;
        this.eventPublisher = eventPublisher;
    }

    public void publishArtwork(String name, String artist) {
        // 1. Lógica de guardado
        storageService.upload(name);

        // 2. Disparar el patrón Pub-Sub
        System.out.println("[SISTEMA] >> Disparando evento de publicación...");
        eventPublisher.publishEvent(new ArtworkPublishedEvent(name, artist));
    }
}