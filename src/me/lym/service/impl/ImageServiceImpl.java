package me.lym.service.impl;

import me.lym.dao.ImageDao;
import me.lym.entity.Image;
import me.lym.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Component
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;

    @Override
    public int save(String id, byte[] image) {
        if (id == null) {
            throw new NullPointerException("id is null");
        }
        if (image == null) {
            throw new NullPointerException("image is null");
        }

        return imageDao.save(id, image);
    }

    @Override
    public int save(String id, File file) throws IOException {
        if (id == null) {
            throw new NullPointerException("id is null");
        }
        if (file == null) {
            throw new NullPointerException("file is null");
        }
        byte[] buffer = null;
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int n;
        while ((n = fis.read(b)) != -1) {
            bos.write(b, 0, n);
        }
        fis.close();
        bos.close();
        buffer = bos.toByteArray();

        return save(id, buffer);
    }

    @Override
    public int save(String id, MultipartFile file) throws IOException {
        if (id == null) {
            throw new NullPointerException("id is null");
        }
        if (file == null) {
            throw new NullPointerException("file is null");
        }
        InputStream is = file.getInputStream();
        byte[] pic = new byte[(int) file.getSize()];
        is.read(pic);
        return save(id, pic);
    }

    @Override
    public Image get(String id) {
        return imageDao.get(id);
    }
}
