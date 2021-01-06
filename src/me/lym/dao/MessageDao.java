package me.lym.dao;

import me.lym.entity.Message;

import java.util.List;

public interface MessageDao {
    int newMessage(Message message);

    int deleteMessage(Message message);

    int deleteMessageByMessageId(int messageId);

    int updateMessage(Message message);

    int updateMessageStatus(int messageId, String status);

    int markAsReaded(int messageId);

    int markUserAllReaded(int userId);

    Message queryMessageById(int messageId);

    List<Message> queryMessagesByUserId(int userId);

    List<Message> queryMessagesByUserIdAndLimit(int userId, int limit, int offset);

}
