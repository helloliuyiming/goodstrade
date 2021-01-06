package me.lym.controller;

import me.lym.dao.OrderDao;
import me.lym.entity.*;
import me.lym.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class ApplicationController {

    @Autowired
    private FavoritiesService favoritiesService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService UserService;

    @Autowired
    private OrderDao orderDao;

    @RequestMapping("tt")
    @ResponseBody
    public String tt() {
        System.out.println(orderDao.queryOrderDetailByCustomerUserId(1));
        return "success";
    }

    @RequestMapping("personal")
    public ModelAndView personal(@RequestParam(required = false) Integer id, @SessionAttribute(value = "user", required = false) goodstrade.entity.User currentUser) {
        if (id == null || id == 0) {
            if (currentUser == null) {
                return new ModelAndView("login");
            }
            id = currentUser.getUserId();
        }
        boolean isSelf = false;
        User queryUser = UserService.queryById(id);
        if (queryUser == null) {
            return new ModelAndView("link/404/index.html");
        }

        if (currentUser != null && currentUser.getUserId() == queryUser.getUserId()) {
            System.out.println("queryUser = " + queryUser);
            System.out.println("currentUser = " + currentUser);
            isSelf = true;
        }

        String defaultAddress = null;
        List<Address> allAddress = addressService.queryAllAddress(id);
        for (Address address : allAddress) {
            if (address.isDefault()) {
                defaultAddress = address.getName() + "  " + address.getPhone() + " : " + address.getProvince() + "" + address.getDistrict() + "...";
            }
        }

        List<Message> messages = messageService.queryMessagesByUserIdAndLimit(id, 8, 0);
        List<Map<String, Object>> collects = favoritiesService.getCollectsOutlineAndLimit(id, 6, 0);
        System.out.println("collects = " + collects);


//		下面这些是作为一个买家的角度需要的信息

        List<Map<String, Object>> allOrder = orderService.queryOrderDetailByCustomerUserId(id);
        List<Map<String, Object>> unpaidOrder = new LinkedList();
        List<Map<String, Object>> uncommentOrder = new LinkedList();
        List<Map<String, Object>> unreceiveOrder = new LinkedList();
        List<Map<String, Object>> suspendOrder = new LinkedList();

        for (Map<String, Object> order : allOrder) {
            String status = (String) order.get("status");
            if (status == null) {
                throw new NullPointerException("");
            }
            if (status.equals(Order.STATUS_UNPAID)) {
                unpaidOrder.add(order);
            } else if (status.equals(Order.STATUS_UNRECEIVE)||status.equals(Order.STATUS_UNSENT)) {
                unreceiveOrder.add(order);
            } else if (status.equals(Order.STATUS_UNCOMMENT)) {
                uncommentOrder.add(order);
            } else if (status.equals(Order.STATUS_SUSPEND)) {
                suspendOrder.add(order);
            }
        }

//		下面的是作为一个卖家的信息
        List<Goods> allGoods = goodsService.queryUserAllGoods(id);
        List<Goods> onSales = new LinkedList();
        for (Goods goods : allGoods) {
            if (goods.getGoodsStatus() == null || goods.getGoodsStatus().equals(Goods.STATUS_PUBLIC)) {
                onSales.add(goods);
            }
        }

        List<Map<String, Object>> customerUnpaidOrder = new ArrayList();
        List<Map<String, Object>> customerUnreceivedOrder = new ArrayList();
        List<Map<String, Object>> finishedOrder = new ArrayList();
        List<Map<String, Object>> unsentOrder = new ArrayList();


        List<Map<String, Object>> allOrderFromSeller = orderService.queryOrderDetailBySellerUserId(id);
        for (Map<String, Object> order : allOrderFromSeller) {
            String status = (String) order.get("status");
            if (status == null) {
                throw new NullPointerException("");
            }
            if (status.equals(Order.STATUS_UNPAID)) {
                customerUnpaidOrder.add(order);
            } else if (status.equals(Order.STATUS_UNRECEIVE)) {
                customerUnreceivedOrder.add(order);
            } else if (status.equals(Order.STATUS_UNSENT)) {
                unsentOrder.add(order);
            } else if (status.equals(Order.STATUS_SUSPEND)) {
                suspendOrder.add(order);
            } else if (status.equals(Order.STATUS_FINISHED)) {
                finishedOrder.add(order);
            }
        }

        ModelAndView modelAndView = new ModelAndView("personal");
        modelAndView.addObject("isSelf", isSelf);
        modelAndView.addObject("defaultAddress", defaultAddress);
        modelAndView.addObject("address", allAddress);
        modelAndView.addObject("user", queryUser);
        modelAndView.addObject("collects", collects);
        modelAndView.addObject("messages", messages);
        modelAndView.addObject("unpaidOrder", unpaidOrder);
        modelAndView.addObject("uncommentOrder", uncommentOrder);
        modelAndView.addObject("unreceiveOrder", unreceiveOrder);
        modelAndView.addObject("suspendOrder", suspendOrder);

        modelAndView.addObject("sold", finishedOrder);
        modelAndView.addObject("onSale", onSales);
        modelAndView.addObject("unsentOrder", unsentOrder);
        modelAndView.addObject("customerUnpaidOrder", customerUnpaidOrder);
        modelAndView.addObject("finishedOrder", finishedOrder);
        modelAndView.addObject("customerUnreceivedOrder", customerUnreceivedOrder);

        return modelAndView;
    }

    @RequestMapping("order")
    public ModelAndView lookOrderList(@RequestParam(value = "userId", required = false) Integer id) {
        if (id == null || id == 0) {
            return new ModelAndView("login");
        }
        ModelAndView modelAndView = new ModelAndView("order");
        List<Map<String, Object>> orders = orderService.queryOrderDetailByCustomerUserId(id);
        modelAndView.addObject("orders", orders);
        return modelAndView;
    }



    @RequestMapping("do-order")
    public ModelAndView doOrder(@RequestParam("goods") List<Integer> goodsList, Integer addrId, String paymentMethod) {

        return new ModelAndView();
    }


}
