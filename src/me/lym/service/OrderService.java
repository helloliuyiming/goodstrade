package me.lym.service;

import me.lym.entity.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
    int newOrder(Order order);

    int updateOrder(Order order);

    int updateStatus(int id, String status);

    Order queryById(int orderId);

    List<Order> queryByUserIdAndGoodsId(int userId, int goodsId);
    List<Order> queryByUserId(int userId, int limit, int offset);


    List<Order> queryByUserIdWithOrderByNum(int userId, int limit, int offset);

    List<Order> queryByUserIdWithOrderByTime(int userId, int limit, int offset);

    List<Order> queryByUserIdWithDeOrderByTime(int userId, int limit, int offset);

    List<Order> queryByUserIdWithDeOrderByNum(int userId, int limit, int offset);

    Map<String, Object> queryOrderDetail(int orderId);

    List<Map<String, Object>> queryOrderDetailByCustomerUserId(int customUserId);

    List<Map<String, Object>> queryOrderDetailBySellerUserId(int sellerUserId);

    Order queryByGoodsId(int goodsId);

}
