package goodstrade.dao.impl;

import goodstrade.dao.GoodsDao;
import goodstrade.entity.Goods;
import goodstrade.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GoodsDaoImpl implements GoodsDao {
    //	添加商品
    @Override
    public int insert(Goods goods) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "insert into goods (g_name,g_price,g_image,g_freight,"
                + "g_buytime,g_buyof,g_broken,g_details,g_uid,g_sort,g_visitor,g_status) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?);";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, goods.getGoodsName());
            ps.setFloat(2, goods.getGoodsPrice());
            ps.setString(3, goods.getGoodsImage());
            ps.setFloat(4, goods.getGoodsFreight());
            ps.setDate(5, goods.getGoodsBuytime());
            ps.setString(6, goods.getGoodsBuyof());
            ps.setString(7, goods.getGoodsBroken());
            ps.setString(8, goods.getGoodsDetails());
            ps.setInt(9, goods.getGoodsUid());
            ps.setString(10, goods.getGoodsSort());
            ps.setInt(11, goods.getGoodsVisitor());
            ps.setString(12, goods.getGoodsStatus());
            int result = ps.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //	删除商品
    @Override
    public int delete(int goodsId) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "delete from goods where g_id = ?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, goodsId);
            int result = ps.executeUpdate();
            return result;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return 0;
    }

    //	改浏览量
    @Override
    public int update(String status, int goodsId) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "update goods set g_status=? where g_id=?;";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, goodsId);
            int result = ps.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //	改浏览量
    @Override
    public int update(int goodsVisitor, int goodsId) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "update goods set g_visitor=? where g_id=?;";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, goodsId);
            ps.setInt(2, goodsId);
            int result = ps.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //	修改商品信息
    public int update(Goods goods, int goodsId) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "update goods set g_name=?,g_price=?,g_image=?,g_freight=?,"
                + "g_buytime=?,g_buyof=?,g_broken=?,g_details=?,g_uid=?,"
                + "g_sort=?,g_status=? where g_id=?";

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, goods.getGoodsName());
            ps.setFloat(2, goods.getGoodsPrice());
            ps.setString(3, goods.getGoodsImage());
            ps.setFloat(4, goods.getGoodsFreight());
            ps.setDate(5, goods.getGoodsBuytime());
            ps.setString(6, goods.getGoodsBuyof());
            ps.setString(7, goods.getGoodsBroken());
            ps.setString(8, goods.getGoodsDetails());
            ps.setInt(9, goods.getGoodsUid());
            ps.setString(10, goods.getGoodsSort());
            ps.setString(11, goods.getGoodsStatus());
            ps.setInt(12, goodsId);
            int result = ps.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //	查询单个商品
    @Override
    public Goods selectGoods(int goodsId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from goods where g_status='public' and g_id=?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, goodsId);
            rs = ps.executeQuery();
            Goods goods = null;
            if (rs.next()) {
                goods = new Goods();
                goods.setGoodsId(rs.getInt("g_id"));
                goods.setGoodsName(rs.getString("g_name"));
                goods.setGoodsPrice(rs.getFloat("g_price"));
                goods.setGoodsFreight(rs.getFloat("g_freight"));
                goods.setGoodsBuytime(rs.getDate("g_buytime"));
                goods.setGoodsBuyof(rs.getString("g_buyof"));
                goods.setGoodsBroken(rs.getString("g_broken"));
                goods.setGoodsBuyof(rs.getString("g_buyof"));
                goods.setGoodsDetails(rs.getString("g_details"));
                goods.setGoodsUid(rs.getInt("g_uid"));
                goods.setGoodsSort(rs.getString("g_sort"));
                goods.setGoodsVisitor(rs.getInt("g_visitor"));
                goods.setGoodsImage(rs.getString("g_image"));
                goods.setGoodsStatus(rs.getString("g_status"));
            }
            return goods;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }


    //	通过商品名称查询商品
    @Override
    public ArrayList<Goods> selectByName(String goodsName, String columnName, String order) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sortOrder = columnName + " " + order;
        String sql = "select * from goods where g_status='public' and g_name like ? ";
        ArrayList<Goods> goodsList = new ArrayList<Goods>();
        if (goodsName == null) {
            sql = "select * from goods order by " + sortOrder;
        } else {
            sql = sql + "order by " + sortOrder;
        }
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + goodsName + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Goods goods = new Goods();
                goods.setGoodsId(rs.getInt("g_id"));
                goods.setGoodsName(rs.getString("g_name"));
                goods.setGoodsPrice(rs.getFloat("g_price"));
                goods.setGoodsFreight(rs.getFloat("g_freight"));
                goods.setGoodsBuytime(rs.getDate("g_buytime"));
                goods.setGoodsBuyof(rs.getString("g_buyof"));
                goods.setGoodsBroken(rs.getString("g_broken"));
                goods.setGoodsBuyof(rs.getString("g_buyof"));
                goods.setGoodsDetails(rs.getString("g_details"));
                goods.setGoodsUid(rs.getInt("g_uid"));
                goods.setGoodsSort(rs.getString("g_sort"));
                goods.setGoodsVisitor(rs.getInt("g_visitor"));
                goods.setGoodsImage(rs.getString("g_image"));
                goods.setGoodsStatus(rs.getString("g_status"));
                goodsList.add(goods);
            }
            return goodsList;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


    //	通过多种类别查询商品
    @Override
    public ArrayList<Goods> selectBySorts(String[] sorts, String columnName, String order) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sortOrder = columnName + " " + order;
        String sql = "select * from goods where g_status='public' and g_sort like ? ";
        ArrayList<Goods> goodsList = new ArrayList<Goods>();
        if (sorts == null) {
            sql = "select * from goods order by " + sortOrder;
        } else {
            for (int i = 1; i < sorts.length; i++) {
                sql = sql + "or g_sort like ? ";
            }
            sql = sql + "order by " + sortOrder;
        }
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (sorts != null) {
                for (int j = 0; j < sorts.length; j++) {
                    ps.setString(j + 1, "%" + sorts[j] + "%");
                }
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Goods goods = new Goods();
                goods.setGoodsId(rs.getInt("g_id"));
                goods.setGoodsName(rs.getString("g_name"));
                goods.setGoodsPrice(rs.getFloat("g_price"));
                goods.setGoodsFreight(rs.getFloat("g_freight"));
                goods.setGoodsBuytime(rs.getDate("g_buytime"));
                goods.setGoodsBuyof(rs.getString("g_buyof"));
                goods.setGoodsBroken(rs.getString("g_broken"));
                goods.setGoodsBuyof(rs.getString("g_buyof"));
                goods.setGoodsDetails(rs.getString("g_details"));
                goods.setGoodsUid(rs.getInt("g_uid"));
                goods.setGoodsSort(rs.getString("g_sort"));
                goods.setGoodsVisitor(rs.getInt("g_visitor"));
                goods.setGoodsImage(rs.getString("g_image"));
                goods.setGoodsStatus(rs.getString("g_status"));
                goodsList.add(goods);
            }
            return goodsList;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    //	通过枚举查询获取收藏或者浏览历史中的商品
    @Override
    public ArrayList<Goods> selectEnumeration(ArrayList<Integer> gid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Goods> goodsList = new ArrayList<Goods>();
        String sql = "select * from goods where g_status='public' and g_id in (?";
        if (gid != null) {
            for (int i = 1; i < gid.size(); i++) {
                sql = sql + ",?";
            }
            sql += ")";
            try {
                conn = DBUtils.getConnection();
                ps = conn.prepareStatement(sql);
                for (int i = 0; i < gid.size(); i++) {
                    ps.setInt(i + 1, gid.get(i));
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    Goods goods = new Goods();
                    goods.setGoodsId(rs.getInt("g_id"));
                    goods.setGoodsName(rs.getString("g_name"));
                    goods.setGoodsPrice(rs.getFloat("g_price"));
                    goods.setGoodsFreight(rs.getFloat("g_freight"));
                    goods.setGoodsBuytime(rs.getDate("g_buytime"));
                    goods.setGoodsBuyof(rs.getString("g_buyof"));
                    goods.setGoodsBroken(rs.getString("g_broken"));
                    goods.setGoodsBuyof(rs.getString("g_buyof"));
                    goods.setGoodsDetails(rs.getString("g_details"));
                    goods.setGoodsImage(rs.getString("g_image"));
                    goods.setGoodsUid(rs.getInt("g_uid"));
                    goods.setGoodsSort(rs.getString("g_sort"));
                    goods.setGoodsVisitor(rs.getInt("g_visitor"));
                    goods.setGoodsStatus(rs.getString("g_status"));
                    goodsList.add(goods);
                }
                return goodsList;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            return null;
        }
        return null;
    }
}
