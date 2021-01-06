package goodstrade.service;

import goodstrade.entity.Goods;

import java.util.ArrayList;

public interface CollectService {
    ArrayList<Integer> getHistoryGid(int uid);//	获取用户浏览的商品id

    ArrayList<Goods> getHistoryGoods(int uid);//	获取用户浏览的商品集合

    ArrayList<Integer> getCollectGid(int uid);//	获取用户收藏的商品id

    ArrayList<Goods> getCollectGoods(int uid);//	获取用户收藏的商品集合

    boolean insertCollect(int uid, int gid);//	有数据更新收藏无数据添加收藏

    boolean deleteCollect(int uid, int gid);    //	有数据删除收藏无数据删除数据行

    boolean insertHistory(int uid, int gid);//	添加历史浏览记录

    boolean deleteHistory(int uid, int gid);    //	删除历史浏览记录

    boolean removeCollect(int uid, ArrayList<Integer> gids);    //	清空收藏

    void removeHistory(int uid, int[] gid);    //	清空历史
}
