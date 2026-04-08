package com.bitpalette.api.controllers;

import com.bitpalette.api.services.ArtworkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

@RestController
@RequestMapping("/api/artworks")
@CrossOrigin(origins = "*") // Permite que el navegador no bloquee la petición
public class ArtworkController {

    private final ArtworkService artworkService;

    // DI por constructor: Inyectamos el servicio que ya tenías funcionando
    public ArtworkController(ArtworkService artworkService) {
        this.artworkService = artworkService;
    }

    @PostMapping(value = "/publish", consumes = "multipart/form-data")
    public ResponseEntity<String> publish(
            @RequestParam("name") String name,
            @RequestParam("artist") String artist,
            @RequestPart("file") MultipartFile file) {

        // Aquí procesarías el archivo (guardarlo en disco o S3)
        System.out.println("[HITO 3] >> Recibido archivo: " + file.getOriginalFilename());

        artworkService.publishArtwork(name, artist);
        return ResponseEntity.ok("Publicado con éxito");
    }

    @MessageMapping("/notif") // Coincide con el stompClient.send("/app/notif", ...)
    @SendTo("/topic/notifications") // Lo retransmite a quienes estén escuchando
    public String handleWebNotification(String artworkJson) {
        // Aquí el servidor recibe el objeto con la imagen y el nombre
        System.out.println("[WEBSOCKET] >> Retransmitiendo nueva obra al feed...");
        return artworkJson; // Al retornar, Spring lo envía automáticamente a /topic/notifications
    }
}