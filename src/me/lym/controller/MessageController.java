package me.lym.controller;

import me.lym.entity.Message;
import me.lym.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("testMS")
    public String test() {
        Message message = new Message();
        message.setCategory("������Ϣ");
        message.setContent("���ǲ�����Ϣ������");
        message.setCreateTime(new Date());
        message.setForUserId(1);
        message.setStatus("unread");
        message.setTitle("����һ��������Ϣ");
        messageService.newMessage(message);
        System.out.println(messageService.queryMessagesByUserId(1));
        System.out.println(messageService.markUserAllReaded(1));
        System.out.println(messageService.queryMessagesByUserId(1));
        return "success";
    }

}
