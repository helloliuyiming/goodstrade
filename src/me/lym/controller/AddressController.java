package me.lym.controller;

import goodstrade.entity.User;
import me.lym.entity.Address;
import me.lym.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AddressController {

    @Autowired
    AddressService addressService;

    @RequestMapping("testAddr")
    public String test() {
        Address address = new Address();
        address.setCity("洛阳");
        address.setDefault(true);
        address.setDetail("xxx");
        address.setDistrict("xx");
        address.setName("xx");
        address.setPhone("138xxxx8965");
        address.setProvince("xx");
        address.setUserId(1);
        int result = addressService.addAddress(address);
        System.out.println("1 all address:" + addressService.queryAllAddress(1));
        System.out.println("xxx" + addressService.queryByID(1));
        address.setId(1);
        address.setCity("xx");
        addressService.updateAddress(address);
        System.out.println("id=1xxaddress:" + addressService.queryByID(1));
        addressService.deleteAddressByID(1);
        System.out.println("1 all address:" + addressService.queryAllAddress(1));
        return String.valueOf(result);
    }

    @RequestMapping("queryAllAddress")
    public ModelAndView queryAddress(@RequestParam("redirect") String redirect,
                                     @SessionAttribute(value = "user", required = false) User user) {
        ModelAndView modelAndView = new ModelAndView();
        if (user == null) {
            modelAndView.setViewName("login");
        }
        List<Address> allAddress = addressService.queryAllAddress(user.getUserId());
        modelAndView.setViewName(redirect);
        Address defaultAddr = null;
        for (Address address : allAddress) {
            if (address.isDefault()) {
                defaultAddr = address;
            }
        }
        modelAndView.addObject("defaultAddress", defaultAddr);
        modelAndView.addObject("address", allAddress);
        return modelAndView;
    }

    @RequestMapping("addAddress")
    public ModelAndView addAddress(HttpServletRequest request,
                                   Address address,
                                   @RequestParam("redirect") String redirect,
                                   @SessionAttribute(value = "user", required = false) User user) {
        System.out.println("addAddress controller");
        System.out.println(address);
        ModelAndView modelAndView = new ModelAndView();
        if (user == null) {
            modelAndView.setViewName("login");
        }

        System.out.println(address);
        address.setUserId(user.getUserId());
        addressService.addAddress(address);
        modelAndView.setViewName(redirect);
        return modelAndView;
    }

    @RequestMapping("changeDefault")
    public ModelAndView changeDefault(@RequestParam("redirect") String redirect,
                                      @SessionAttribute(value = "user", required = false) User user,
                                      @RequestParam("addrId") Integer addrId) {
        ModelAndView modelAndView = new ModelAndView();
        if (user == null) {
            modelAndView.setViewName("login");
            return modelAndView;
        }
        Address address = addressService.queryByID(addrId);
        address.setIsDefault(true);
        addressService.updateAddress(address);
        modelAndView.setViewName(redirect);
        return modelAndView;
    }

    @RequestMapping("updateAddress")
    public ModelAndView updateAddress(@RequestParam("redirect") String redirect,
                                      @SessionAttribute(value = "user", required = false) User user,
                                      Address address) {
        ModelAndView modelAndView = new ModelAndView();
        if (user == null) {
            modelAndView.setViewName("login");
        }
        addressService.updateAddress(address);
        modelAndView.setViewName(redirect);
        return modelAndView;
    }

    @RequestMapping("deleteAddress")
    public ModelAndView deleteAddress(@RequestParam("redirect") String redirect,
                                      @SessionAttribute(value = "user", required = false) User user,
                                      @RequestParam("addrId") Integer addrId) {
        ModelAndView modelAndView = new ModelAndView();
        if (user == null) {
            modelAndView.setViewName("login");
        }

        modelAndView.setViewName(redirect);
        addressService.deleteAddressByID(addrId);
        return modelAndView;
    }
}
