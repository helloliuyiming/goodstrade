package goodstrade.dao;

import goodstrade.entity.Goods;

import java.util.ArrayList;

public interface GoodsDao {
    int insert(Goods goods);    //	上架商品

    int delete(int goodsId);    //	下架商品

    int update(int goodsVisitor, int goodsId);//	修改访问量

    int update(String status, int goodsId);    //	修改商品状态

    int update(Goods goods, int goodsId);    //	修改商品信息

    ArrayList<Goods> selectEnumeration(ArrayList<Integer> gid);    //	获取收藏或浏览历史中的商品

    Goods selectGoods(int goodsId);    //	查询单个商品信息

    ArrayList<Goods> selectByName(String goodsName, String columnName, String order);    //	查询单个商品信息

    ArrayList<Goods> selectBySorts(String[] sorts, String columnName, String order);//	通过多种类别并筛选查询商品
}
