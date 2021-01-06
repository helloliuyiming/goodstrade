package me.lym.service.impl;

import me.lym.dao.CollectDao;
import me.lym.entity.Collect;
import me.lym.service.FavoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class FavoritiesServiceImpl implements FavoritiesService {

    @Autowired
    CollectDao collectDao;

    @Override
    public int deleteCollects(int userId, int goodsId) {
        return collectDao.deleteCollects(userId, goodsId);
    }

    @Override
    public int deleteCollectsByGoodsId(int goodsId) {
        return collectDao.deleteCollectsByGoodsId(goodsId);
    }

    @Override
    public List<Collect> getCollects(int userId) {
        return collectDao.getCollects(userId);
    }

    @Override
    public List<Collect> getCollectsOutline(int userId) {
        return collectDao.getCollectsOutline(userId);
    }

    @Override
    public List<Map<String, Object>> getCollectsAndLimit(int userId, int limit, int offset) {
        return collectDao.getCollectsAndLimit(userId, limit, offset);
    }

    @Override
    public List<Map<String, Object>> getCollectsOutlineAndLimit(int userId, int limit, int offset) {
        return collectDao.getCollectsOutlineAndLimit(userId, limit, offset);
    }
}
