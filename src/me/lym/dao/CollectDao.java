package me.lym.dao;

import me.lym.entity.Collect;

import java.util.List;
import java.util.Map;

public interface CollectDao {

    int deleteCollects(int userId, int goodsId);
    int deleteCollectsByGoodsId(int goodsId);


    List<Collect> getCollects(int userId);

    List<Collect> getCollectsOutline(int userId);

    List<Map<String,Object>> getCollectsAndLimit(int userId, int limit, int offset);

    List<Map<String,Object>> getCollectsOutlineAndLimit(int userId, int limit, int offset);

}
