package goodstrade.dao.impl;

import goodstrade.dao.ReceiverAddressDao;
import goodstrade.entity.ReceiverAddress;
import goodstrade.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReceiverAddressDaoImpl implements ReceiverAddressDao {

    @Override
    public int insert(ReceiverAddress ra) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "insert into receiver_address (r_name,r_address," +
                "r_phonenumber) values (?,?,?);";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, ra.getReceiverName());
            ps.setString(2, ra.getReceiverAddress());
            ps.setString(3, ra.getReveiverPhoneNumber());
            int result = ps.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int receiverId) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "delete * from receiver_address where r_id=receiverId;";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, receiverId);
            int result = ps.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(ReceiverAddress ra) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "update from receiver_address set r_name=?,r_address=?" +
                ",r_phonenumber=? where r_id = ?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, ra.getReceiverName());
            ps.setString(2, ra.getReceiverAddress());
            ps.setString(3, ra.getReveiverPhoneNumber());
            ps.setInt(4, ra.getReceiverId());
            int result = ps.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ReceiverAddress selectAddress(int receiverId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from receiver_address where r_id = ?";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, receiverId);
            rs = ps.executeQuery();
            if (rs.next()) {
                ReceiverAddress ra = new ReceiverAddress(rs.getInt("r_id"), rs.getString("r_name"), rs.getString("r_address"), rs.getString("r_phonenumber"));
                return ra;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<ReceiverAddress> selectAllAddress(int receiverId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<ReceiverAddress> raList = new ArrayList<ReceiverAddress>();
        String sql = "select * from receiver_address";
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, receiverId);
            rs = ps.executeQuery();
            while (rs.next()) {
                ReceiverAddress ra = new ReceiverAddress(rs.getInt("r_id"), rs.getString("r_name"), rs.getString("r_address"), rs.getString("r_phonenumber"));
                raList.add(ra);
                return raList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
