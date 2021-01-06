package goodstrade.service.impl;

import goodstrade.dao.CollectDao;
import goodstrade.dao.GoodsDao;
import goodstrade.dao.impl.CollectDaoImpl;
import goodstrade.dao.impl.GoodsDaoImpl;
import goodstrade.entity.Collect;
import goodstrade.entity.Goods;
import goodstrade.service.CollectService;

import java.util.ArrayList;

public class CollectServiceImpl implements CollectService {
    CollectDao collectDao = new CollectDaoImpl();
    GoodsDao goodsDao = new GoodsDaoImpl();

    //	获取用户收藏的商品id
    @Override
    public ArrayList<Integer> getCollectGid(int uid) {
        ArrayList<Integer> gidList = collectDao.gidBycorh(uid, "collect");
        return gidList;

    }


    //	获取用户收藏的商品
    @Override
    public ArrayList<Goods> getCollectGoods(int uid) {
        ArrayList<Integer> gidList = collectDao.gidBycorh(uid, "collect");
        ArrayList<Goods> goodsList = new ArrayList<Goods>();
        if (gidList != null) {
            goodsList = goodsDao.selectEnumeration(gidList);
            return goodsList;
        } else {
            return null;
        }
    }

    //	获取用户浏览的商品id
    @Override
    public ArrayList<Integer> getHistoryGid(int uid) {
        ArrayList<Integer> gidList = collectDao.gidBycorh(uid, "history");
        return gidList;

    }


    //	获取用户浏览的商品
    @Override
    public ArrayList<Goods> getHistoryGoods(int uid) {
        ArrayList<Integer> gidList = collectDao.gidBycorh(uid, "history");
        ArrayList<Goods> goodsList = new ArrayList<Goods>();
        if (gidList != null) {
            goodsList = goodsDao.selectEnumeration(gidList);
            return goodsList;
        } else {
            return null;
        }
    }


    //	商品展示页面点击收藏则添加收藏
    @Override
    public boolean insertCollect(int uid, int gid) {
        Collect collect = collectDao.select(uid, gid);
        if (collect != null) {    //	有这条数据
            int result = collectDao.modifyCollect(collect.getCid(), 1);
            //	收藏成功
            return result == 1;
        } else {    //	没有数据则新增
            collect = new Collect(uid, gid, 1, 0);
            int result = collectDao.insert(collect);
            //	收藏成功
            return result == 1;
        }
    }

    //	商品展示页面点击取消则取消收藏
    @Override
    public boolean deleteCollect(int uid, int gid) {
        Collect collect = collectDao.select(uid, gid);
        if (collect.getHistory() == 0) {    //	历史被清除则清除此条数据
            int result = collectDao.delete(collect.getCid());
            //	清除数据成功
            return result == 1;
        } else {    //	浏览历史存在则取消收藏
            int result = collectDao.modifyCollect(collect.getCid(), 0);
            //	取消收藏成功
            return result == 1;
        }
    }


    //	进入详情浏览历史增加
    @Override
    public boolean insertHistory(int uid, int gid) {
        Collect collect = collectDao.select(uid, gid);
        if (collect != null) {    //	有这条数据
            int result = collectDao.modifyHistory(collect.getCid(), 1);
            //	历史添加成功
            return result == 1;
        } else {    //	没有数据则新增
            collect = new Collect(uid, gid, 0, 1);
            int result = collectDao.insert(collect);
            //	；历史添加成功
            return result == 1;
        }
    }


    //	删除历史记录
    @Override
    public boolean deleteHistory(int uid, int gid) {
        Collect collect = collectDao.select(uid, gid);
        if (collect.getCollect() == 0) {    //	未收藏则清除此条数据
            int result = collectDao.delete(collect.getCid());
            //	清除数据成功
            return result == 1;
        } else {    //	已收藏则清除历史记录
            int result = collectDao.modifyHistory(collect.getCid(), 0);
            //	清除历史成功
            return result == 1;
        }
    }

    //	清除多个或所有商品收藏
    @Override
    public boolean removeCollect(int uid, ArrayList<Integer> gids) {
        if (gids.size() == 0) {
            ArrayList<Integer> gidList = getCollectGid(uid);//	获取收藏商品的id
            for (Integer integer : gidList) {
                Collect collect = collectDao.select(uid, integer);
                if (collect.getHistory() == 1) {
                    collectDao.modifyCollect(collect.getCid(), 0);
                } else {
                    collectDao.delete(collect.getCid());
                }
            }
            return true;
        } else {
            for (Integer gid : gids) {
                Collect collect = collectDao.select(uid, gid);
                if (collect.getHistory() == 1) {
                    collectDao.modifyCollect(collect.getCid(), 0);
                } else {
                    collectDao.delete(collect.getCid());
                }
            }
            return true;
        }
    }

    //	清空多个或所有浏览历史
    @Override
    public void removeHistory(int uid, int[] gid) {
        if (gid == null) {    //	清空所有
            ArrayList<Integer> gidList = getHistoryGid(uid);//	获取浏览历史商品的id
            for (Integer integer : gidList) {
                Collect collect = collectDao.select(uid, integer);
                if (collect.getCollect() == 1) {
                    collectDao.modifyHistory(collect.getCid(), 0);
                } else {
                    collectDao.delete(collect.getCid());
                }
            }
        } else {
            for (int i : gid) {
                Collect collect = collectDao.select(uid, i);
                if (collect.getCollect() == 1) {
                    collectDao.modifyHistory(collect.getCid(), 0);
                } else {
                    collectDao.delete(collect.getCid());
                }
            }
        }
    }

}
