package me.lym.entity;

import java.util.Date;

public class Order {
    public static final String STATUS_UNPAID = "unpaid";
    public static final String STATUS_UNSENT = "unsent";
    public static final String STATUS_UNRECEIVE = "unreceive";
    public static final String STATUS_UNCOMMENT = "uncomment";
    public static final String STATUS_CLOSE = "close";
    public static final String STATUS_FINISHED = "finished";
    public static final String STATUS_SUSPEND = "suspend";
    private int id;
    private int userId;
    private int goodsId;
    private Date createTime;
    private Date closeTime;
    private float money;
    private int sellerId;
    private String paymentMethod;
    private String deliveryMethod;
    private String expressCompany;
    private String expressCode;
    private float freight;
    private String receiverAddress;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public float getFreight() {
        return freight;
    }

    public void setFreight(float freight) {
        this.freight = freight;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", goodsId=" + goodsId +
                ", createTime=" + createTime +
                ", closeTime=" + closeTime +
                ", money=" + money +
                ", sellerId=" + sellerId +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", deliveryMethod='" + deliveryMethod + '\'' +
                ", expressCompany='" + expressCompany + '\'' +
                ", expressCode='" + expressCode + '\'' +
                ", freight=" + freight +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
