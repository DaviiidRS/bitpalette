package com.bitpalette.api.listeners;

import com.bitpalette.api.events.ArtworkPublishedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @EventListener
    public void onArtworkPublished(ArtworkPublishedEvent event) {
        System.out.println("[NOTIFICACIÓN] >> ¡Atención seguidores! Nueva obra disponible: " + event.artworkName());
    }
}