package me.lym.service.impl;

import me.lym.dao.GoodsDao;
import me.lym.entity.Goods;
import me.lym.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsDao goodsDao;

    @Override
    public int addGoods(Goods goods) {
        return goodsDao.addGoods(goods);
    }

    @Override
    public int deleteGoods(Goods goods) {
        return goodsDao.deleteGoods(goods);
    }

    @Override
    public int deleteGoodsById(int userId) {
        return goodsDao.deleteGoodsById(userId);
    }

    @Override
    public int updateGoods(Goods goods) {
        return goodsDao.updateGoods(goods);
    }

    @Override
    public Goods queryById(int id) {
        return goodsDao.queryById(id);
    }


    @Override
    public List<Goods> queryByKey(String key) {
        return goodsDao.queryByKey(key);
    }

    @Override
    public List<Goods> queryByKeyAndLimit(String key, int limit, int offset) {
        return goodsDao.queryByKeyAndLimit(key, limit, offset);
    }

    @Override
    public List<Goods> queryAll() {
        return goodsDao.queryAll();
    }

    @Override
    public List<Goods> queryAllAndLimit(int limit, int offset) {
        return goodsDao.queryAllAndLimit(limit, offset);
    }

    @Override
    public List<Goods> queryAndSortByHot(int limit, int offset) {
        return goodsDao.queryAndSortByHot(limit, offset);
    }

    @Override
    public List<Goods> queryAndSortByNew(int limit, int offset) {
        return goodsDao.queryAndSortByNew(limit, offset);
    }

    @Override
    public List<Goods> queryUserAllGoods(int userId) {
        return goodsDao.queryUserAllGoods(userId);
    }

    @Override
    public List<Goods> queryUserAllGoodsAndLimit(int userId, int limit, int offset) {
        return goodsDao.queryUserAllGoodsAndLimit(userId, limit, offset);
    }

}
