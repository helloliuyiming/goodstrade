package me.lym.controller;

import com.sun.org.apache.xpath.internal.operations.Or;
import goodstrade.entity.User;
import me.lym.entity.Address;
import me.lym.entity.Goods;
import me.lym.entity.Message;
import me.lym.entity.Order;
import me.lym.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private FavoritiesService favoritiesService;

    @RequestMapping("testOrder")
    public String test() {
        Order order = new Order();
        order.setCreateTime(new Date());
        order.setCloseTime(new Date());
        order.setFreight(1.0f);
        order.setGoodsId(1);
        order.setMoney(15.0f);
        order.setStatus("unpaid");
        order.setReceiverAddress("xxʡxx��xxxxx");
        order.setUserId(1);
        orderService.newOrder(order);

        System.out.println(orderService.queryById(1));
        System.out.println(orderService.queryByUserId(1, 0, 0));
        System.out.println(orderService.queryByUserIdWithDeOrderByNum(1, 0, 0));
        System.out.println(orderService.queryByUserIdWithOrderByNum(1, 1, 1));
        return "success";
    }

    @RequestMapping("previewOrder")
    public ModelAndView previewOrder(@SessionAttribute(value = "user", required = false) User user,
                                     @RequestParam("goodsIds") List<Integer> goodsIds) {
        ModelAndView modelAndView = new ModelAndView();
        if (user == null) {
            modelAndView.setViewName("login");
            return modelAndView;
        }
        modelAndView.setViewName("do-order");
        List<Address> allAddress = addressService.queryAllAddress(user.getUserId());
        Address defaultAddr = null;
        List<Address> otherAddrs = new ArrayList();
        for (Address address : allAddress) {
            if (address.isDefault()) {
                defaultAddr = address;
            } else {
                otherAddrs.add(address);
            }
        }

        int goodsCount = goodsIds.size();
        float totalPrice = 0;
        float totalFreight = 0;
        List<Goods> goodsList = new ArrayList();
        for (Integer id : goodsIds) {
            Goods goods = goodsService.queryById(id);
            totalFreight += goods.getGoodsFreight();
            totalPrice += goods.getGoodsPrice();
            goodsList.add(goods);

        }

        modelAndView.addObject("defaultAddress", defaultAddr);
        modelAndView.addObject("otherAddress", otherAddrs);
        modelAndView.addObject("goodsList", goodsList);
        modelAndView.addObject("goodsCount", goodsCount);
        modelAndView.addObject("totalPrice", totalPrice);
        modelAndView.addObject("totalFreight", totalFreight);
        modelAndView.addObject("totalPay",totalFreight+totalPrice);
        System.out.println("goodsCount = " + goodsCount);
        System.out.println("totalPrice = " + totalPrice);
        System.out.println("totalFreight = " + totalFreight);
        System.out.println("totalPay = "+(totalPrice+totalPrice));


        return modelAndView;
    }

    @RequestMapping("doOrder")
    public ModelAndView doOrder(@SessionAttribute(value = "user", required = false) User user,
                                @RequestParam("addrId") Integer addrId,
                                @RequestParam("goodsIds") List<Integer> goodsIds,
                                @RequestParam("paymentMethod") String paymentMethod) {
        ModelAndView modelAndView = new ModelAndView();
        if (user == null) {
            modelAndView.setViewName("login");
            return modelAndView;
        }
        Address address = addressService.queryByID(addrId);

        StringBuilder orderIdBuilder = null;
        for (Integer goodsId : goodsIds) {
            List<Order> orderList = orderService.queryByUserIdAndGoodsId(user.getUserId(), goodsId);
            for (Order order : orderList) {
                if (!order.getStatus().equals(Order.STATUS_CLOSE)) {
                    modelAndView.setViewName("redirect:/personal");
                    return modelAndView;
                }
            }
            Goods goods = goodsService.queryById(goodsId);
            goods.setGoodsStatus(Goods.STATUS_PROCESSING);
            goodsService.updateGoods(goods);

            favoritiesService.deleteCollectsByGoodsId(goodsId);

            Order order = new Order();
            order.setCreateTime(new Date());
            order.setGoodsId(goodsId);
            order.setMoney(goods.getGoodsPrice());
            order.setFreight(goods.getGoodsFreight());
            order.setUserId(user.getUserId());
            order.setPaymentMethod(paymentMethod);
            order.setStatus(Order.STATUS_UNPAID);
            order.setReceiverAddress(address.getName() + "  " + address.getPhone() + ":" + address.getProvince() + address.getCity() + address.getDistrict() + address.getDetail());

            orderService.newOrder(order);
            int newOrderId = order.getId();
            if (orderIdBuilder == null) {
                orderIdBuilder = new StringBuilder();
                orderIdBuilder.append("ids="+newOrderId);
            }else {
                orderIdBuilder.append("&ids="+newOrderId);
            }
        }

        modelAndView.setViewName("redirect:/payment?"+orderIdBuilder);

        return modelAndView;
    }




    @RequestMapping("payment")
    public ModelAndView payment(@SessionAttribute(value = "user",required = false) User user,
                                @RequestParam("ids") List<Integer> ids) {
        ModelAndView modelAndView = new ModelAndView();
        if (user == null) {
            modelAndView.setViewName("login");
            return modelAndView;
        }
        modelAndView.setViewName("payment");
        List<Order> orderList = new ArrayList<>();
        float allpay = 0f;
        for (Integer orderId : ids) {
            Order order = orderService.queryById(orderId);
            if (order == null) {
                continue;
            }
            allpay+=order.getFreight();
            allpay+=order.getMoney();
            orderList.add(order);
        }

        modelAndView.addObject("paymentMethod","支付宝");
        modelAndView.addObject("allpay",allpay);
        modelAndView.addObject("orders",orderList);
        return modelAndView;
    }

    @RequestMapping("pay")
    public ModelAndView pay(@SessionAttribute(value = "user",required = false) User user,
                            @RequestParam("ids") List<Integer> ids,
                            @RequestParam("paymentMethod") String paymentMethod,
                            @RequestParam("allpay") float allpay) {
        ModelAndView modelAndView = new ModelAndView();
        if (user == null) {
            modelAndView.setViewName("login");
            return modelAndView;
        }
        for (Integer id : ids) {
            Order order = orderService.queryById(id);
            Goods goods = goodsService.queryById(order.getGoodsId());
            Map<String, Object> orderDetail = orderService.queryOrderDetail(id);
            order.setPaymentMethod(paymentMethod);
            order.setStatus(Order.STATUS_UNSENT);
            goods.setGoodsStatus(Goods.STATUS_PROCESSING);
            orderService.updateOrder(order);
            goodsService.updateGoods(goods);
            Message toSellerMessage = new Message();
            toSellerMessage.setCategory("system");
            toSellerMessage.setCreateTime(new Date());
            toSellerMessage.setForUserId(goods.getGoodsUid());
            toSellerMessage.setStatus(Message.STATUS_UNREAD);
            toSellerMessage.setTitle("买家已付款");
            toSellerMessage.setContent("你的商品" + goods.getGoodsName() + "'买家已付款，请尽快发货!!!");
            messageService.newMessage(toSellerMessage);
        }

        System.out.println("订单处理完成，跳转到订单详情");
        modelAndView.setViewName("redirect:/personal");
        return modelAndView;
    }

    @RequestMapping("send")
    public ModelAndView send(@SessionAttribute("user") User user,
                             Integer orderId,
                             Integer goodsId,
                             String deliveryMethod
            , Integer number) {
        ModelAndView modelAndView = new ModelAndView();
        if (user == null) {
            modelAndView.setViewName("login");
            return modelAndView;
        }


        return modelAndView;
    }


    @RequestMapping("orderdetail")
    public ModelAndView lookOrderDetail(@SessionAttribute(value = "user",required = false) User user,
                                        @RequestParam("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        if (user == null) {
            modelAndView.setViewName("login");
            return modelAndView;
        }
        modelAndView.setViewName("order-detail");

        Order order = orderService.queryById(id);

        modelAndView.addObject("isSeller",order.getSellerId()==user.getUserId());

        modelAndView.addObject("isCustomer",order.getUserId()==user.getUserId());

        Map<String, Object> orderDetail = orderService.queryOrderDetail(id);

        String receiverAddress = (String) orderDetail.get("receiver_address");
        System.out.println("receiverAddress = " + receiverAddress);
        String nameAndphone = receiverAddress.substring(0, receiverAddress.indexOf(":"));
        String[] split = nameAndphone.split("  ");
        String name = split[0];
        String phone = split[1];
        String addressDetail = receiverAddress.substring(receiverAddress.indexOf(":") + 1);

        Map<String,String> deliveryInfo = new HashMap<>();


        modelAndView.addObject("orderDetail",orderDetail);
        modelAndView.addObject("reveiverName",name);
        modelAndView.addObject("reveiverPhone",phone);
        modelAndView.addObject("reveiverDetail",addressDetail);
        modelAndView.addObject("deliveryInfo",deliveryInfo);


        return modelAndView;
    }


    @RequestMapping("orderlist")
    public ModelAndView orderList(@SessionAttribute(value = "user",required = false) User user){
        ModelAndView modelAndView = new ModelAndView();
        if (user == null) {
            modelAndView.setViewName("login");
            return modelAndView;
        }
        modelAndView.setViewName("order");
        List<Map<String, Object>> orderList = orderService.queryOrderDetailByCustomerUserId(user.getUserId());
        modelAndView.addObject("orders",orderList);
        return modelAndView;
    }
}
