package goodstrade.service;

import goodstrade.entity.Goods;

import java.util.ArrayList;

public interface GoodsService {
    boolean insert(Goods goods);    //	上架商品

    Goods selectGoods(int goodsId);    //	获取单个商品信息

    //	public String deleteGoods(int goodsId);	//	下架商品
	boolean modifyStatus(ArrayList<Integer> gidList);

    ArrayList<Goods> getOrderGoods(ArrayList<Integer> gidList);

    boolean removemoreVisitor(ArrayList<Integer> gidList);    //	对多个商品的取消收藏时收藏量减一

    boolean addVisitor(int goodsId);    //	增加收藏数量

    boolean removeVisitor(int goodsId);//	减少收藏数量

    ArrayList<Goods> selectBySorts(String sorts);//	通过一种或多种类别获取商品

    ArrayList<Goods> selectPriceAsc(String sort);    //	某种类别价格升序

    ArrayList<Goods> selectPriceDesc(String sort);    //	某种类别价格降序

    ArrayList<Goods> selectNewGoods();    //	获取所有新品

    ArrayList<Goods> hotSellingGoods();    //	获取爆款类型的所有商品

    ArrayList<Goods> guessLikeGoods();    //	猜你喜欢的所有商品

    ArrayList<Goods> selectNewGoods(String sort);    //	获取某种类别新品

    ArrayList<Goods> hotSellingGoods(String sort);    //	获取某种爆款类型的商品

    ArrayList<Goods> guessLikeGoods(String sort);    //	某种猜你喜欢的商品

    ArrayList<Goods> selectByName(String goodsName);

    //	筛选商品
	ArrayList<Goods> screen(String sort, int columnNumber, int orderNumber);

    //	通过名称筛选商品
	ArrayList<Goods> screenByName(String goodsName, int columnNumber, int orderNumber);

    ArrayList<Goods> selectNewGoodsByName(String goodsName);    //	获取通过名称筛选的新品

    ArrayList<Goods> hotSellingGoodsByName(String goodsName);    //	获取通过名称筛选的爆款类型商品

    ArrayList<Goods> guessLikeGoodsByName(String goodsName);    //	通过名称筛选的猜你喜欢商品
}
