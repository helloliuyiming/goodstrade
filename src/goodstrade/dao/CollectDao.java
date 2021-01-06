package goodstrade.dao;

import goodstrade.entity.Collect;

import java.util.ArrayList;

public interface CollectDao {
    int insert(Collect collect);

    int delete(int cid);

    int modifyCollect(int cid, int collect);

    int modifyHistory(int cid, int history);

    Collect select(int uid, int gid);    //	通过用户id和商品id判断用户是否收藏或浏览过

    ArrayList<Integer> gidBycorh(int uid, String column);    //	通过历史或者收藏获取商品gid
}
