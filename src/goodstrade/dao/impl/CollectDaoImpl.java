package goodstrade.dao.impl;

import goodstrade.dao.CollectDao;
import goodstrade.entity.Collect;
import goodstrade.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CollectDaoImpl implements CollectDao {

    @Override
    public int insert(Collect collect) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "insert into collect (uid,gid,collect,history) values (?,?,?,?);";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, collect.getUid());
            ps.setInt(2, collect.getGid());
            ps.setInt(3, collect.getCollect());
            ps.setInt(4, collect.getHistory());
            int result = ps.executeUpdate();
            return result;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int cid) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "delete from collect where cid=?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cid);
            int result = ps.executeUpdate();
            return result;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int modifyCollect(int cid, int collect) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "update collect set collect=? where cid=?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, collect);
            ps.setInt(2, cid);
            int result = ps.executeUpdate();
            return result;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int modifyHistory(int cid, int history) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "update collect set history=? where cid=?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, history);
            ps.setInt(2, cid);
            int result = ps.executeUpdate();
            return result;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    //	通过用户id和商品id判断用户是否收藏或浏览过
    @Override
    public Collect select(int uid, int gid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from collect where uid=? and gid=?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, uid);
            ps.setInt(2, gid);
            rs = ps.executeQuery();
            Collect collect = null;
            if (rs.next()) {
                collect = new Collect(rs.getInt("cid"), rs.getInt("uid"), rs.getInt("gid"), rs.getInt("collect"), rs.getInt("history"));

            }
            return collect;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


    //	获取用户收藏或浏览过的商品id
    @Override
    public ArrayList<Integer> gidBycorh(int uid, String column) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Integer> gidList = new ArrayList<Integer>();
        String sql = "select gid from collect where uid=? and " + column + "=?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, uid);
            ps.setInt(2, 1);
            rs = ps.executeQuery();
            while (rs.next()) {
                gidList.add(rs.getInt("gid"));
            }
            return gidList;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
