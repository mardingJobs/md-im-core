package cn.md.im.core.common.mq.event;


import cn.md.im.core.common.model.TopicMessage;


public interface MessageEventSenderService {

    /**
     * 发送消息
     * @param message 发送的消息
     */
    boolean send(TopicMessage message);

}
