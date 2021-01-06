package goodstrade.entity;

public class ReceiverAddress {
    private int receiverId;
    private String receiverName;
    private String receiverAddress;
    private String reveiverPhoneNumber;

    public ReceiverAddress() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ReceiverAddress(String receiverName, String receiverAddress, String reveiverPhoneNumber) {
        super();
        this.receiverName = receiverName;
        this.receiverAddress = receiverAddress;
        this.reveiverPhoneNumber = reveiverPhoneNumber;
    }

    public ReceiverAddress(int receiverId, String receiverName, String receiverAddress, String reveiverPhoneNumber) {
        super();
        this.receiverId = receiverId;
        this.receiverName = receiverName;
        this.receiverAddress = receiverAddress;
        this.reveiverPhoneNumber = reveiverPhoneNumber;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReveiverPhoneNumber() {
        return reveiverPhoneNumber;
    }

    public void setReveiverPhoneNumber(String reveiverPhoneNumber) {
        this.reveiverPhoneNumber = reveiverPhoneNumber;
    }

    @Override
    public String toString() {
        return "ReceiverAddress [receiverName=" + receiverName + ", receiverAddress=" + receiverAddress
                + ", reveiverPhoneNumber=" + reveiverPhoneNumber + "]";
    }
}
