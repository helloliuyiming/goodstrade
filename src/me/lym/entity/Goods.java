package me.lym.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Goods {
    public static final String STATUS_UNPUBLIC = "unpublic";
    public static final String STATUS_PUBLIC = "public";
    public static final String STATUS_BAN = "ban";
    public static final String STATUS_PROCESSING = "processing";
    public static final String STATUS_SOLD = "sold";
    private int goodsId;
    private String goodsName;
    private float goodsPrice;
    private float goodsFreight;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date goodsBuytime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date goodsPosttime;
    private String goodsBuyof;
    private String goodsBroken;
    private String goodsDetails;
    private String goodsImage;
    private int goodsUid;
    private String goodsSort;
    private int goodsVisitor;
    private String goodsStatus;

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public float getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(float goodsPrice) {
//        goodsPrice = Math.round(goodsPrice*10)/10;

        this.goodsPrice = goodsPrice;
    }

    public float getGoodsFreight() {
        return goodsFreight;
    }

    public void setGoodsFreight(float goodsFreight) {
//        goodsFreight = Math.round(goodsFreight*10)/10;
        this.goodsFreight = goodsFreight;
    }

    public Date getGoodsBuytime() {
        return goodsBuytime;
    }

    public void setGoodsBuytime(Date goodsBuytime) {
        this.goodsBuytime = goodsBuytime;
    }

    public String getGoodsBuyof() {
        return goodsBuyof;
    }

    public void setGoodsBuyof(String goodsBuyof) {
        this.goodsBuyof = goodsBuyof;
    }

    public String getGoodsBroken() {
        return goodsBroken;
    }

    public void setGoodsBroken(String goodsBroken) {
        this.goodsBroken = goodsBroken;
    }

    public String getGoodsDetails() {
        return goodsDetails;
    }

    public void setGoodsDetails(String goodsDetails) {
        this.goodsDetails = goodsDetails;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public int getGoodsUid() {
        return goodsUid;
    }

    public void setGoodsUid(int goodsUid) {
        this.goodsUid = goodsUid;
    }

    public String getGoodsSort() {
        return goodsSort;
    }

    public void setGoodsSort(String goodsSort) {
        this.goodsSort = goodsSort;
    }

    public int getGoodsVisitor() {
        return goodsVisitor;
    }

    public void setGoodsVisitor(int goodsVisitor) {
        this.goodsVisitor = goodsVisitor;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public Date getGoodsPosttime() {
        return goodsPosttime;
    }

    public void setGoodsPosttime(Date goodsPosttime) {
        this.goodsPosttime = goodsPosttime;
    }

    @Override
    public String toString() {
        return "Goods [goodsId=" + goodsId + ", goodsName=" + goodsName + ", goodsPrice=" + goodsPrice
                + ", goodsFreight=" + goodsFreight + ", goodsBuytime=" + goodsBuytime + ", goodsPosttime="
                + goodsPosttime + ", goodsBuyof=" + goodsBuyof + ", goodsBroken=" + goodsBroken + ", goodsDetails="
                + goodsDetails + ", goodsImage=" + goodsImage + ", goodsUid=" + goodsUid + ", goodsSort=" + goodsSort
                + ", goodsVisitor=" + goodsVisitor + "]";
    }


}
