package me.lym.service.impl;

import me.lym.dao.OrderDao;
import me.lym.entity.Order;
import me.lym.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Override
    public int newOrder(Order order) {
        return orderDao.newOrder(order);
    }

    @Override
    public int updateOrder(Order order) {
        return orderDao.updateOrder(order);
    }

    @Override
    public int updateStatus(int id, String status) {
        return orderDao.updateStatus(id, status);
    }

    @Override
    public Order queryById(int orderId) {
        return orderDao.queryById(orderId);
    }

    @Override
    public List<Order> queryByUserIdAndGoodsId(int userId, int goodsId) {
        return orderDao.queryByUserIdAndGoodsId(userId, goodsId);
    }

    @Override
    public List<Order> queryByUserId(int userId, int limit, int offset) {
        if (limit <= 0 && offset <= 0) {
            return orderDao.queryByUserId(userId);
        }
        return orderDao.queryByUserIdWithLimit(userId, limit, offset);
    }

    @Override
    public List<Order> queryByUserIdWithOrderByNum(int userId, int limit, int offset) {
        if (limit <= 0 && offset <= 0) {
            return orderDao.queryByUserIdWithOrderByNum(userId);
        }
        return orderDao.queryByUserIdWithOrderByTimeAndLimit(userId, limit, offset);
    }

    @Override
    public List<Order> queryByUserIdWithDeOrderByNum(int userId, int limit, int offset) {
        if (limit <= 0 && offset <= 0) {
            return orderDao.queryByUserIdWithDeOrderByNum(userId);
        }
        return orderDao.queryByUserIdWithDeOrderByNumAndLimit(userId, limit, offset);
    }

    @Override
    public Map<String, Object> queryOrderDetail(int orderId) {
        return orderDao.queryOrderDetail(orderId);
    }

    @Override
    public List<Map<String, Object>> queryOrderDetailByCustomerUserId(int userId) {
        return orderDao.queryOrderDetailByCustomerUserId(userId);
    }

    @Override
    public List<Map<String, Object>> queryOrderDetailBySellerUserId(int sellerUserId) {
        return orderDao.queryOrderDetailBySellerUserId(sellerUserId);
    }

    @Override
    public Order queryByGoodsId(int goodsId) {
        return orderDao.queryByGoodsId(goodsId);
    }

    @Override
    public List<Order> queryByUserIdWithOrderByTime(int userId, int limit, int offset) {
        if (limit <= 0 && offset <= 0) {
            return orderDao.queryByUserIdWithOrderByTime(userId);
        }
        return orderDao.queryByUserIdWithOrderByTimeAndLimit(userId, limit, offset);
    }


    @Override
    public List<Order> queryByUserIdWithDeOrderByTime(int userId, int limit, int offset) {
        if (limit <= 0 && offset <= 0) {
            return orderDao.queryByUserIdWithDeOrderByTime(userId);
        }
        return orderDao.queryByUserIdWithDeOrderByTimeAndLimit(userId, limit, offset);
    }


}
