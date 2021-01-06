package me.lym.controller;

import me.lym.entity.Image;
import me.lym.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

@Controller
public class ImageController {

    @Autowired
    ImageService imageService;

    @RequestMapping("images/{id}")
    public void getImage(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        System.out.println("查询image by id=" + id);

        Image image = imageService.get(id);
        if (image == null && id.length() == 36) {
            id = id+"1";
            image = imageService.get(id);
        }

        if (image == null || image.getImage() == null) {
            return;
        }
        byte[] data = image.getImage();

        response.setCharacterEncoding("UTF-8");
        OutputStream outputSream = response.getOutputStream();
        InputStream in = new ByteArrayInputStream(data);
        int len = 0;
        byte[] buf = new byte[1024];
        while ((len = in.read(buf, 0, 1024)) != -1) {
            outputSream.write(buf, 0, len);
        }
        outputSream.close();
    }

    @ResponseBody
    @RequestMapping("testImage")
    public String saveImage(@RequestParam("file") MultipartFile file) throws IOException {
        if (file == null) {
            System.out.println("上传失败！！！");
            return "error";
        }

        System.out.println(file.getName());
        imageService.save(UUID.randomUUID().toString(), file);
        return "success";
    }
}
