package me.lym.test;

import me.lym.dao.GoodsDao;
import me.lym.dao.ImageDao;
import me.lym.entity.Goods;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.test.context.TestConstructor;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class Test {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config2.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ImageDao imageDao = sqlSession.getMapper(ImageDao.class);
        GoodsDao goodsDao = sqlSession.getMapper(GoodsDao.class);
        File file = new File("D:\\imagessss");
        if (file.isDirectory()) {

        }
        File[] images = file.listFiles();
        int index = 0;
        int userId = 1;
        String[] buyof = new String[]{"京东","淘宝","线下店","官网","其他"};
        String[] sort = new String[]{"家用电器","手机","运营商","数码","电脑","办公","家居","家具","家装","厨具","男装","女装","童装","内衣","美妆","个护清洁","宠物","女鞋","箱包","钟表","珠宝","男鞋","运动","户外","房产","汽车","汽车用品","母婴","玩具乐器","食品","酒类","生鲜","特产","艺术","礼品鲜花","农资绿植","医药保健","计生情趣","图书","文娱","教育","电子书","机票","酒店","旅游","生活","理财","众筹","白条","保险","安装","维修","清洗","二手","工业品"};
        String[] broken = new String[]{"全新","9.5成新","9成新","8成新","7成新","5成新","3成新","1成新","不能用"};
        Random random = new Random();
        for (File image : images) {
            index++;
            Goods goods = new Goods();
            goods.setGoodsUid(userId);
            goods.setGoodsStatus(Goods.STATUS_PUBLIC);
            goods.setGoodsName("这是商品"+index+"的标题");
            String buyoff = buyof[random.nextInt(5)];
            goods.setGoodsBuyof(buyoff);
            String brokenn = broken[random.nextInt(8)];
            goods.setGoodsBroken(brokenn);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                stringBuilder.append(sort[random.nextInt(54)]);
                stringBuilder.append(",");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            goods.setGoodsSort(stringBuilder.toString());
            goods.setGoodsPrice(random.nextFloat()*100);
            goods.setGoodsFreight(random.nextFloat()*10);
            goods.setGoodsDetails("这是商品"+index+"的介绍"+"这是商品"+index+"的介绍"+"这是商品"+index+"的介绍"+"这是商品"+index+"的介绍"+"这是商品"+index+"的介绍");
            LocalDateTime now = LocalDateTime.now();
            now = now.minusDays(random.nextInt(30));
            now = now.minusMonths(random.nextInt(20));
            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zdt = now.atZone(zoneId);//Combines this date-time with a time-zone to create a  ZonedDateTime.
            Date date = Date.from(zdt.toInstant());
            goods.setGoodsBuytime(date);
            goods.setGoodsVisitor(random.nextInt(100));
            String imageName = UUID.randomUUID().toString();
            goods.setGoodsImage(imageName);
            byte[] buffer = null;
            FileInputStream fis = new FileInputStream(image);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
            goodsDao.addGoods(goods);
            imageDao.save(imageName+"1",buffer);
            imageDao.save(imageName+"2",buffer);
            imageDao.save(imageName+"3",buffer);
            imageDao.save(imageName+"4",buffer);
            imageDao.save(imageName+"5",buffer);
            sqlSession.commit();
        }

        sqlSession.close();
    }


}
