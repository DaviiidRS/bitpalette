package com.bitpalette.api.services;

import org.springframework.stereotype.Service;

@Service
public class S3StorageService implements IStorageService {
    @Override
    public void upload(String fileName) {
        System.out.println("[ALMACENAMIENTO] >> Subiendo '" + fileName + "' a AWS S3...");
    }
}
