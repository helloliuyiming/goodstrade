package me.lym.dao;

import me.lym.entity.Goods;

import java.util.List;

public interface GoodsDao {
    int addGoods(Goods goods);

    int deleteGoods(Goods goods);

    int deleteGoodsById(int goodsId);

    int updateGoods(Goods goods);

    Goods queryById(int id);

    List<Goods> queryUserAllGoods(int userId);

    List<Goods> queryUserAllGoodsAndLimit(int userId, int limit, int offset);

    List<Goods> queryByKey(String key);

    List<Goods> queryByKeyAndLimit(String key, int limit, int offset);

    List<Goods> queryAll();

    List<Goods> queryAllAndLimit(int limit, int offset);

    List<Goods> queryAndSortByHot(int limit, int offset);

    List<Goods> queryAndSortByNew(int limit, int offset);
}
