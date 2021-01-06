package goodstrade.servlet;

import goodstrade.dao.GoodsDao;
import goodstrade.dao.impl.GoodsDaoImpl;
import goodstrade.entity.Goods;
import goodstrade.service.GoodsService;
import goodstrade.service.impl.GoodsServiceImpl;

import java.util.ArrayList;
import java.util.UUID;

public class test {
    public static void main(String[] args) {
//        GoodsService goodsService = new GoodsServiceImpl();
//        GoodsDao goodsDao = new GoodsDaoImpl();
//        //	ArrayList<Goods> goodsList = goodsService.selectByName("11");
//        ArrayList<Goods> goodsList = goodsDao.selectByName("1", "g_id", "desc");
//        System.out.println(goodsList.size());
//        for (Goods goods : goodsList) {
//            System.out.println(goods);
//        }

        for (int i = 0; i < 100; i++) {
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);
            System.out.println(uuid.length());
        }
    }



}
