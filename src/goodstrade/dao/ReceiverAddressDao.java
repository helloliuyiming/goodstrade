package goodstrade.dao;

import goodstrade.entity.ReceiverAddress;

import java.util.ArrayList;

public interface ReceiverAddressDao {
    int insert(ReceiverAddress receiverAddress);

    int delete(int receiverId);

    int update(ReceiverAddress receiverAddress);

    ReceiverAddress selectAddress(int receiverId);

    ArrayList<ReceiverAddress> selectAllAddress(int receiverId);
}
