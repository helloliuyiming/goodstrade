package goodstrade.entity;

import java.sql.Date;

public class Order {
    private int orderId;
    private String receiverName;
    private String sendName;
    private Date tradeTime;
    private String goodsName;
    private String goodsImage;
    private String orderNumber;
    private int status;

    public Order() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Order(String receiverName, String sendName, Date tradeTime, String goodsName, String goodsImage,
                 String orderNumber, int status) {
        super();
        this.receiverName = receiverName;
        this.sendName = sendName;
        this.tradeTime = tradeTime;
        this.goodsName = goodsName;
        this.goodsImage = goodsImage;
        this.orderNumber = orderNumber;
        this.status = status;
    }

    public Order(int orderId, String receiverName, String sendName, Date tradeTime, String goodsName, String goodsImage,
                 String orderNumber, int status) {
        super();
        this.orderId = orderId;
        this.receiverName = receiverName;
        this.sendName = sendName;
        this.tradeTime = tradeTime;
        this.goodsName = goodsName;
        this.goodsImage = goodsImage;
        this.orderNumber = orderNumber;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order [receiverName=" + receiverName + ", sendName=" + sendName + ", tradeTime=" + tradeTime
                + ", goodsName=" + goodsName + ", goodsImage=" + goodsImage + ", orderNumber=" + orderNumber
                + ", status=" + status + "]";
    }
}
