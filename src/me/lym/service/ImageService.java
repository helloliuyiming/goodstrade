package me.lym.service;

import me.lym.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface ImageService {
    int save(String id, byte[] image);

    int save(String id, File file) throws IOException;

    int save(String id, MultipartFile file) throws IOException;

    Image get(String id);
}
