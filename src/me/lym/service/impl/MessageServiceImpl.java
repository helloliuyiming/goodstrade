package me.lym.service.impl;

import me.lym.dao.MessageDao;
import me.lym.entity.Message;
import me.lym.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public int newMessage(Message message) {
        return messageDao.newMessage(message);
    }

    @Override
    public int deleteMessage(Message message) {
        return messageDao.deleteMessage(message);
    }

    @Override
    public int deleteMessageByMessageId(int messageId) {
        return messageDao.deleteMessageByMessageId(messageId);
    }

    @Override
    public int updateMessage(Message message) {
        return messageDao.updateMessage(message);
    }

    @Override
    public int updateMessageStatus(int messageId, String status) {
        return messageDao.updateMessageStatus(messageId, status);
    }

    @Override
    public int markAsReaded(int messageId) {
        return messageDao.markAsReaded(messageId);
    }

    @Override
    public int markUserAllReaded(int userId) {
        return messageDao.markUserAllReaded(userId);
    }

    @Override
    public Message queryMessageById(int messageId) {
        return messageDao.queryMessageById(messageId);
    }

    @Override
    public List<Message> queryMessagesByUserId(int userId) {
        return messageDao.queryMessagesByUserId(userId);
    }

    @Override
    public List<Message> queryMessagesByUserIdAndLimit(int userId, int limit, int offset) {
        return messageDao.queryMessagesByUserIdAndLimit(userId, limit, offset);
    }

}
