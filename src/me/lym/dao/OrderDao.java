package me.lym.dao;

import me.lym.entity.Order;

import java.util.List;
import java.util.Map;

public interface OrderDao {

    int newOrder(Order order);

    int updateOrder(Order order);

    int updateStatus(int id, String status);

    Order queryById(int orderId);
    List<Order> queryByUserIdAndGoodsId(int userId, int goodsId);
    Order queryByGoodsId(int goodsId);

    List<Order> queryByUserId(int userId);

    List<Order> queryByUserIdWithLimit(int userId, int limit, int offset);

    List<Order> queryByUserIdWithOrderByTime(int userId);

    List<Order> queryByUserIdWithDeOrderByTime(int userId);

    List<Order> queryByUserIdWithOrderByNum(int userId);

    List<Order> queryByUserIdWithDeOrderByNum(int userId);

    List<Order> queryByUserIdWithOrderByTimeAndLimit(int userId, int limit, int offset);

    List<Order> queryByUserIdWithOrderByNumAndLimit(int userId, int limit, int offset);

    List<Order> queryByUserIdWithDeOrderByTimeAndLimit(int userId, int limit, int offset);

    List<Order> queryByUserIdWithDeOrderByNumAndLimit(int userId, int limit, int offset);


    Map<String, Object> queryOrderDetail(int orderId);

    List<Map<String, Object>> queryOrderDetailByCustomerUserId(int userId);

    List<Map<String, Object>> queryOrderDetailBySellerUserId(int sellerUserId);
}
