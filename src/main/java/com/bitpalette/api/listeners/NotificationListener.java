package com.bitpalette.api.listeners;

import com.bitpalette.api.events.ArtworkPublishedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    private final SimpMessagingTemplate messagingTemplate;

    // Spring inyecta automáticamente esta utilidad para enviar mensajes web
    public NotificationListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void onArtworkPublished(ArtworkPublishedEvent event) {
        System.out.println("[PUB-SUB] >> Enviando a WebSockets: " + event.artworkName());

        // Esto envía la notificación al canal que configuramos en WebSocketConfig
        messagingTemplate.convertAndSend("/topic/notifications",
                "🔔 ¡Nueva obra de arte!: " + event.artworkName());
    }
}