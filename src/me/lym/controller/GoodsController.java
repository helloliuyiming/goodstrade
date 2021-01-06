package me.lym.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import goodstrade.entity.User;
import me.lym.entity.Goods;
import me.lym.service.GoodsService;
import me.lym.service.ImageService;
import me.lym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    ImageService imageService;
    @Autowired
    UserService userService;


    @RequestMapping("testGoods")
    public String test() {

        Goods goods = new Goods();
        goods.setGoodsBroken("broken");
        goods.setGoodsBuyof("goodsBuyof");
        goods.setGoodsBuytime(new Date());
        goods.setGoodsDetails("goodsDetails");
        goods.setGoodsFreight(1.0f);
        goods.setGoodsId(15);
        goods.setGoodsImage("goodsImage");
        goods.setGoodsName("goodsName");
        goods.setGoodsPosttime(new Date());
        goods.setGoodsPrice(15.6f);
        goods.setGoodsSort("goodsSort");
        goods.setGoodsUid(1);
        goods.setGoodsVisitor(10);

        goodsService.addGoods(goods);
        List<Goods> list = goodsService.queryAll();
        System.out.println(list);
        List<Goods> list2 = goodsService.queryAllAndLimit(5, 2);
        System.out.println(list2);


        return "";
    }


    @RequestMapping("addGoods")
    public ModelAndView addGoods(@SessionAttribute(value = "user", required = false) User currentUser,
                                 @RequestParam(value = "goodsName",required = false)String goodsName,
                                 @RequestParam(value = "goodsDetails",required = false)String goodsDetails,
                                 @RequestParam(value = "goodsPrice",required = false)Float goodsPrice,
                                 @RequestParam(value = "goodsFreight",required = false)Float goodsFreight,
                                 @RequestParam(value = "goodsBroken",required = false)String goodsBroken,
                                 @RequestParam(value = "goodsSort",required = false)String goodsSort,
//                                 @RequestParam(value = "time",required = false)Date goodsBuytime,
//                                 @RequestParam(value = "source",required = false)String goodsBuyof,
                                 @RequestParam(value = "file1",required = false) MultipartFile file1,
                                 @RequestParam(value = "file2",required = false) MultipartFile file2,
                                 @RequestParam(value = "file3",required = false) MultipartFile file3,
                                 @RequestParam(value = "file4",required = false) MultipartFile file4,
                                 @RequestParam(value = "file5",required = false) MultipartFile file5) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        if (currentUser == null) {
            modelAndView.setViewName("login");
            return modelAndView;
        }
        modelAndView.setViewName("personal");
        Goods goods = new Goods();
        goods.setGoodsName(goodsName);
        goods.setGoodsBroken(goodsBroken);
//        goods.setGoodsBuyof(goodsBuyof);
//        goods.setGoodsBuytime(goodsBuytime);
        goods.setGoodsBuytime(new Date());
        goods.setGoodsBuyof("天猫");
        goods.setGoodsDetails(goodsDetails);
        goods.setGoodsFreight(goodsFreight);
        goods.setGoodsPrice(goodsPrice);
        goods.setGoodsDetails(goodsDetails);
        goods.setGoodsSort(goodsSort);
        goods.setGoodsStatus(Goods.STATUS_PUBLIC);
        goods.setGoodsUid(currentUser.getUserId());
        String goodsImageId = UUID.randomUUID().toString();
        goods.setGoodsImage(goodsImageId);
        imageService.save(goodsImageId+"1",file1);
        imageService.save(goodsImageId+"2",file2);
        imageService.save(goodsImageId+"3",file3);
        imageService.save(goodsImageId+"4",file4);
        imageService.save(goodsImageId+"5",file5);

        goodsService.addGoods(goods);

        return modelAndView;
    }

    @RequestMapping("goodsdetail")
    public ModelAndView getGoodsDetail(@RequestParam("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        if (id == null || id == 0) {
            modelAndView.setViewName("index");
            return modelAndView;
        }
        Goods goods = goodsService.queryById(id);
        modelAndView.addObject("goods",goods);
        List<String> imageIds = new ArrayList<>();
        String goodsImage = goods.getGoodsImage();
        for (int i = 0; i < 5; i++) {
            imageIds.add(goodsImage+(i+1));
        }

        me.lym.entity.User user = userService.queryById(goods.getGoodsUid());

        modelAndView.addObject("imageIds",imageIds);
        modelAndView.addObject("sellerInfo",user);
        modelAndView.setViewName("goodsdetail");

        return modelAndView;
    }

}
