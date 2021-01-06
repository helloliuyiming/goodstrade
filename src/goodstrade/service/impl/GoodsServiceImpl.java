package goodstrade.service.impl;

import goodstrade.dao.GoodsDao;
import goodstrade.dao.impl.GoodsDaoImpl;
import goodstrade.entity.Goods;
import goodstrade.service.GoodsService;

import java.util.ArrayList;

public class GoodsServiceImpl implements GoodsService {
    GoodsDao goodsDao = new GoodsDaoImpl();

    //	上架商品
    @Override
    public boolean insert(Goods goods) {
        int result = goodsDao.insert(goods);
        return result == 1;
    }

    //	查找单个商品
    @Override
    public boolean modifyStatus(ArrayList<Integer> gidList) {
        if (gidList.size() != 0) {
            for (int i = 0; i < gidList.size(); i++) {
                goodsDao.update("protected", gidList.get(i));
            }
            return true;
        } else {
            return false;
        }
    }

    //	查找单个商品
    @Override
    public Goods selectGoods(int goodsId) {
        Goods goods = goodsDao.selectGoods(goodsId);
        return goods;
    }

    //	收藏访问量加一
    @Override
    public boolean addVisitor(int goodsId) {
        Goods goods = goodsDao.selectGoods(goodsId);
        int result = goodsDao.update(goods.getGoodsVisitor() + 1, goodsId);
        return result == 1;
    }

    //	取消收藏访问量减一
    @Override
    public boolean removeVisitor(int goodsId) {
        Goods goods = goodsDao.selectGoods(goodsId);
        int result = goodsDao.update(goods.getGoodsVisitor() - 1, goodsId);
        return result == 1;
    }

    //	对多个商品的取消收藏时收藏量减一
    @Override
    public boolean removemoreVisitor(ArrayList<Integer> gidList) {
        if (gidList.size() == 0) {
            return false;
        } else {
            for (int i = 0; i < gidList.size(); i++) {
                Goods goods = goodsDao.selectGoods(gidList.get(i));
                goodsDao.update(goods.getGoodsVisitor() - 1, gidList.get(i));
            }
            return true;
        }
    }

    //	通过类别获取商品集合
    /*
     * columnNumber: 1--通过id ,2--通过价格,3--通过收藏数量
     * orderNumber : 1--desc降序	,2--asc升序
     */
    private ArrayList<Goods> selectBySort(String sort, int columnNumber, int orderNumber) {
        String order;
        String columnName;
        String[] sorts = null;
        if (sort != null) {
            if (sort.contains(",")) {
                sorts = sort.split(",");
            } else {
                sorts = new String[]{sort};
            }
        }
        switch (columnNumber) {
            case 1:
                columnName = "g_id";    //	新品
                break;
            case 2:
                columnName = "g_price";    //	价格
                break;
            case 3:
                columnName = "g_visitor";    //	价格
                break;
            default:
                columnName = "g_freight";
                break;
        }
        switch (orderNumber) {
            case 1:
                order = "desc";//	降序
                break;

            default:
                order = "asc";
                break;
        }
        ArrayList<Goods> goodsList = goodsDao.selectBySorts(sorts, columnName, order);
        return goodsList;
    }

    //	获取生成订单的商品信息
    @Override
    public ArrayList<Goods> getOrderGoods(ArrayList<Integer> gidList) {
        ArrayList<Goods> orderGoods = goodsDao.selectEnumeration(gidList);
        return orderGoods;
    }

    //	获取类别筛选的商品信息
    @Override
    public ArrayList<Goods> screen(String sort, int columnNumber, int orderNumber) {
        ArrayList<Goods> goodsList = selectBySort(sort, columnNumber, orderNumber);
        return goodsList;
    }

    //	通过一种或多种类别获取商品 - - 默认id升序
    @Override
    public ArrayList<Goods> selectBySorts(String sort) {
        ArrayList<Goods> goodsList = selectBySort(sort, 1, 2);
        return goodsList;
    }


    //	指定类别商品价格升序
    @Override
    public ArrayList<Goods> selectPriceAsc(String sort) {
        ArrayList<Goods> goodsList = selectBySort(sort, 2, 2);
        return goodsList;
    }

    //	指定类别商品价格降序
    @Override
    public ArrayList<Goods> selectPriceDesc(String sort) {
        ArrayList<Goods> goodsList = selectBySort(sort, 2, 1);
        return goodsList;
    }


    //	获取所有新品
    @Override
    public ArrayList<Goods> selectNewGoods() {
        ArrayList<Goods> goodsList = selectBySort(null, 1, 1);
        return goodsList;
    }

    //	获取所有商品通过收藏数量降序
    @Override
    public ArrayList<Goods> hotSellingGoods() {
        ArrayList<Goods> goodsList = selectBySort(null, 3, 1);
        return goodsList;
    }

    //	猜你喜欢的所有商品
    @Override
    public ArrayList<Goods> guessLikeGoods() {
        ArrayList<Goods> goodsList = selectBySort(null, 2, 2);
        return goodsList;
    }

    //	获取某种类别的新品
    @Override
    public ArrayList<Goods> selectNewGoods(String sort) {
        ArrayList<Goods> goodsList = selectBySort(sort, 1, 1);
        return goodsList;
    }

    //	获取某种爆款的商品
    @Override
    public ArrayList<Goods> hotSellingGoods(String sort) {
        ArrayList<Goods> goodsList = selectBySort(sort, 3, 1);
        return goodsList;
    }

    //	获取某种类别的猜你喜欢
    @Override
    public ArrayList<Goods> guessLikeGoods(String sort) {
        ArrayList<Goods> goodsList = selectBySort(sort, 2, 2);
        return goodsList;
    }

    @Override
    public ArrayList<Goods> selectByName(String goodsName) {
        ArrayList<Goods> goodsByName = selectByName1(goodsName, 1, 2);
        if (goodsByName == null || goodsByName.size() == 0) {
            return null;
        } else {
            return goodsByName;
        }

    }

    @Override
    public ArrayList<Goods> screenByName(String goodsName, int columnNumber, int orderNumber) {
        ArrayList<Goods> goodsList = selectByName1(goodsName, columnNumber, orderNumber);
        return goodsList;
    }

    private ArrayList<Goods> selectByName1(String goodsName, int columnNumber, int orderNumber) {
        String order;
        String columnName;
        switch (columnNumber) {
            case 1:
                columnName = "g_id";    //	新品
                break;
            case 2:
                columnName = "g_price";    //	价格
                break;
            case 3:
                columnName = "g_visitor";    //	价格
                break;
            default:
                columnName = "g_freight";
                break;
        }
        switch (orderNumber) {
            case 1:
                order = "desc";//	降序
                break;

            default:
                order = "asc";
                break;
        }
        ArrayList<Goods> goodsList = goodsDao.selectByName(goodsName, columnName, order);
        return goodsList;
    }

    @Override
    public ArrayList<Goods> selectNewGoodsByName(String goodsName) {
        ArrayList<Goods> goodsList = selectByName1(goodsName, 1, 1);
        return goodsList;
    }

    @Override
    public ArrayList<Goods> hotSellingGoodsByName(String goodsName) {
        ArrayList<Goods> goodsList = selectByName1(goodsName, 3, 1);
        return goodsList;
    }

    @Override
    public ArrayList<Goods> guessLikeGoodsByName(String goodsName) {
        ArrayList<Goods> goodsList = selectByName1(goodsName, 2, 2);
        return goodsList;
    }
}
