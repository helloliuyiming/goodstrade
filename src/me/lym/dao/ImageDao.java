package me.lym.dao;

import me.lym.entity.Image;

public interface ImageDao {
    int save(String id, byte[] image);

    Image get(String id);
}
